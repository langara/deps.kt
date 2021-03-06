import kotlin.text.RegexOption.CANON_EQ
import kotlin.text.RegexOption.COMMENTS
import kotlin.text.RegexOption.DOT_MATCHES_ALL
import kotlin.text.RegexOption.IGNORE_CASE
import kotlin.text.RegexOption.LITERAL
import kotlin.text.RegexOption.MULTILINE
import kotlin.text.RegexOption.UNIX_LINES

/**
 * Multiplatform Kotlin Frontend / DSL for regular expressions. Actual regular expressions are used like IR
 * (intermediate representation) just to compile it to standard kotlin.text.Regex,
 * but developer is using nice DSL to build regular expressions instead of writing them by hand.
 *
 * Reference links to RE engines/backends docs, etc:
 * https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-regex/
 * https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html
 * https://docs.oracle.com/javase/tutorial/essential/regex/quant.html
 * https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/RegExp
 * https://www.w3schools.com/jsref/jsref_obj_regexp.asp
 * https://regexr.com/
 */

/**
 * Here UreIR is the traditional regular expression - no human should read - kind of "intermediate representation"
 */
typealias UreIR = String
// TODO_later: change to value class (when we have new kotlin in gradle scripts)

fun ure(vararg opts: RegexOption, init: UreProduct.() -> Unit) = ure(enable = opts.toSet(), disable = emptySet(), init)

fun ure(enable: Set<RegexOption> = emptySet(), disable: Set<RegexOption> = emptySet(), init: UreProduct.() -> Unit) =
    if (enable.isEmpty() && disable.isEmpty()) UreProduct(init)
    else ureWithOptions(UreProduct(init), enable, disable)

fun ureWithOptions(content: Ure, enable: Set<RegexOption> = emptySet(), disable: Set<RegexOption> = emptySet()) =
    UreChangeOptionsGroup(content, enable, disable)

fun ure(name: String, vararg opts: RegexOption, init: UreProduct.() -> Unit) =
    if (opts.isEmpty()) ureWithName(name,UreProduct(init))
    else ureWithName(name, ureWithOptions(UreProduct(init), opts.toSet()))

fun ureWithName(name: String, content: Ure) = UreNamedGroup(content, name)

sealed class Ure {

    abstract fun toIR(): UreIR

    /**
     * Optionally wraps in non-capturing group before generating IR, so it's safe to use with quantifiers, unions, etc
     * Wrapping is done only when needed. For example concatenation(product) with more than one element is wrapped.
     * TODO: think more (and test!) cases when product list is empty! (and we apply some quantifiers etc)
     * UPDATE: I guess it's enough to obey invariant that ClosedIR can never be empty.
     */
    abstract fun toClosedIR(): UreIR

    fun compile(vararg options: RegexOption) = Regex(toIR(), options.toSet())

    // TODO_later: experiment with dropping U (Micro) prefix in classes nested in URegEx when I have some examples working.
    // I'm leaving it for now to have more unique names and less clashes, but design is not final.
}

infix fun Ure.or(that: Ure) = UreUnion(this, that)
infix fun Ure.then(that: Ure) = UreProduct(mutableListOf(this, that))
    // Do not rename "then" to "and". The "and" is more like special lookahead/lookbehind group

data class UreProduct(val product: MutableList<Ure> = mutableListOf()): Ure() {
    constructor(init: UreProduct.() -> Unit) : this() { init() }
    override fun toIR() = product.joinToString(separator = "") { it.toIR() }
    override fun toClosedIR() = when (product.size) {
        1 -> product[0].toClosedIR()
        else -> ncapt(this).toIR()
    }
    class UreX(val times: IntRange, val reluctant: Boolean, val possessive: Boolean)
    fun x(times: IntRange, reluctant: Boolean = false, possessive: Boolean = false) = UreX(times, reluctant, possessive)
    fun x(times: Int) = x(times..times)
    infix fun UreX.of(ure: Ure) { product.add(quantify(ure, times, reluctant, possessive)) }
    infix fun UreX.of(init: UreProduct.() -> Unit) { product.add(quantify(times, reluctant, possessive, init)) }
    infix fun IntRange.of(ure: Ure) = x(this) of ure
    infix fun Int.of(ure: Ure) = x(this) of ure
    infix fun IntRange.of(init: UreProduct.() -> Unit) = x(this) of init
    infix fun Int.of(init: UreProduct.() -> Unit) = x(this) of init
}

data class UreUnion(val first: Ure, val second: Ure): Ure() {
    override fun toIR() = first.toClosedIR() + "|" + second.toClosedIR()
    override fun toClosedIR() = ncapt(this).toIR()
}

abstract class UreGroup: Ure() {
    abstract val content: Ure
    private val contentIR get() = content.toIR()
    protected abstract val typeIR: String

    override fun toIR(): UreIR = "($typeIR$contentIR)"
        // looks like all possible typeIR prefixes can not be confused with first contentIR characters.
        // (meaning: RE designers thought about it, so I don't have to be extra careful here)

    override fun toClosedIR() = toIR() // group is always "closed" - has parentheses outside
}

data class UreNamedGroup(override val content: Ure, val name: String): UreGroup() {
    override val typeIR get() = "?<$name>"
}

data class UreNonCaptGroup(override val content: Ure): UreGroup() {
    override val typeIR get() = "?:"
}

data class UreCaptGroup(override val content: Ure): UreGroup() {
    override val typeIR get() = ""
}

// TODO: test it!
data class UreChangeOptionsGroup(
    override val content: Ure,
    val enable: Set<RegexOption> = emptySet(),
    val disable: Set<RegexOption> = emptySet()
): UreGroup() {
    init {
        require((enable intersect disable).isEmpty()) { "Can not enable and disable the same option at the same time" }
        require(enable.isNotEmpty() || disable.isNotEmpty()) { "No options provided" }
    }
    override val typeIR get() = "?${enable.ir}-${disable.ir}:" // TODO_later: check if either set can be empty

    // TODO_later: review flags we support - but probably want to be multiplatform??
    private val RegexOption.code get() = when (this) {
        IGNORE_CASE -> "i"
        MULTILINE -> "m"
        LITERAL -> TODO()
        UNIX_LINES -> "d"
        COMMENTS -> "x" // but not really supported... maybe in UreRawIR, but I wouldn't use it
        DOT_MATCHES_ALL -> "s" // s means - treat all as a single line (so dot matches terminators too)
        CANON_EQ -> TODO()
        // TODO_someday "u" is not supported (unicode case)
    }

    private val Set<RegexOption>.ir get() = joinToString("") { it.code }
}
// TODO_someday: there are also similar "groups" without content (see Pattern.java), add support for it (content nullable?)


data class UreLookGroup(override val content: Ure, val ahead: Boolean = true, val positive: Boolean = true): UreGroup() {
    override val typeIR get() = when (ahead to positive) {
        true to true -> "?="
        true to false -> "?!"
        false to true -> "?<="
        false to false -> "?<!"
        else -> error("Impossible case")
    }
}


// TODO_someday: "independent" non-capturing group - what is that? (see Pattern.java)

data class UreGroupRef(val nr: Int? = null, val name: String? = null): Ure() {
    init {
        nr == null || name == null || error("Can not reference capturing group by both nr ($nr) and name ($name)")
        nr == null && name == null && error("Either nr or name has to be provided for the group reference")
    }

    override fun toIR(): UreIR = if (nr != null) "\\$nr" else "\\k<$name>"
    override fun toClosedIR(): UreIR = toIR()
}

const val MAX = Int.MAX_VALUE

data class UreQuantifier(
    val content: Ure,
    val times: IntRange,
    val reluctant: Boolean = false,
    val possessive: Boolean = false,
): Ure() {
    init { reluctant && possessive && error("Quantifier can't be reluctant and possessive at the same time") }
    val greedy get() = !reluctant && !possessive
    override fun toIR(): UreIR {
        val timesIR = when(times) {
            1..1 -> return content.toIR()
            0..1 -> "?"
            0..MAX -> "*"
            1..MAX -> "+"
            else -> when(times.last) {
                times.first -> "{${times.first}}"
                MAX -> "{${times.first},}"
                else -> "{${times.first},${times.last}}"
            }
        }
        val suffixIR = when {
            reluctant -> "?"
            possessive -> "+"
            else -> ""
        }
        return content.toClosedIR() + timesIR + suffixIR
    }
    override fun toClosedIR(): UreIR = ncapt(this).toIR()
}

data class UreChar(val ir: UreIR) : Ure() {
    // TODO_later: separate sealed class for specials etc. We should never ask user to manually provide UreIR

    override fun toIR(): UreIR = ir
    override fun toClosedIR(): UreIR = ncapt(this).toIR()
    // Maybe grouping here is not strictly needed, but I'll leave it for now
    // TODO_someday: analyze carefully and maybe drop grouping if not needed.
}

// TODO_later: can I do something like: chars: Set<UChar> ??
data class UreCharSet(val chars: Set<UreIR>, val positive: Boolean = true) : Ure() {
    override fun toIR(): UreIR = chars.joinToString("", if (positive) "[" else "[^", "]")
    override fun toClosedIR(): UreIR = toIR()
}

// TODO_later: more complicated combinations of char classes
// TODO_later: analyze if some special kotlin progression/range would fit here better
data class UreCharRange(val from: UreIR, val to: UreIR) : Ure() {
    override fun toIR(): UreIR = "[$from-$to]"
    override fun toClosedIR(): UreIR = toIR()
}

data class UreRawIR(val ir: UreIR) : Ure() {
    // TODO_later: this is a dirty way to inject whole strings fast. TODO_later: think what would be better.
    // maybe still ask user for string, but validate and transform to actual UreProduct of UreChar's

    override fun toIR(): UreIR = ir
    override fun toClosedIR(): UreIR = ncapt(this).toIR()
}

data class UreQuote(val string: String): Ure() {
    override fun toIR() = "\\Q$string\\E"
    override fun toClosedIR(): UreIR = toIR()
}
// TODO: implement operator a.not() (!) with big pattern matching and negating more obvious stuff like
//  !nonDigit == digit; !digit == nonDigit; !word == nonWord; !UCharSet(.., true) == UCharSet(.., false), etc
// TODO_later: experiment more with different operators overloading (after impl some working examples)
//  especially indexed access operators and invoke operators..

fun ch(ir: UreIR) = UreChar(ir)
fun ir(ir: UreIR) = UreRawIR(ir)

val backslash = ch("\\\\")

fun unicode(name: String) = ch("\\N{$name}")
val tab = ch("\\t")
val lf = ch("\\n")
val cr = ch("\\r")
val ff = ch("\\f")
val alert = ch("\\a")
val esc = ch("\\e")

val any = ch(".")

val digit = ch("\\d")
val nonDigit = ch("\\D")
val space = ch("\\s")
val nonSpace = ch("\\S")
val word = ch("\\w")
val nonWord = ch("\\W")

val posixLower = ch("\\p{Lower}")
val posixUpper = ch("\\p{Upper}")
val posixAlpha = ch("\\p{Alpha}")
val posixDigit = ch("\\p{Digit}")
val posixAlnum = ch("\\p{Alnum}")
val posixPunct = ch("\\p{Punct}")
val posixPrint = ch("\\p{Print}")
val posixBlank = ch("\\p{Blank}")
val posixCntrl = ch("\\p{Cntrl}")
val posixXDigit = ch("\\p{XDigit}")
val posixSpace = ch("\\p{Space}")

val BOL = ch("^")
val EOL = ch("$")
val BOInput = ch("\\A")
val EOInput = ch("\\z")
val EOPreviousMatch = ch("\\G")

val wordBoundary = ch("\\b")
val nonWordBoundary = ch("\\B")

fun control(x: String) = ch("\\c$x") // FIXME_later: what exactly is this?? (see std Pattern.java)
fun oneCharOf(vararg chars: UreIR) = UreCharSet(chars.toSet()) // TODO_later: Use UreChar as vararg type
fun oneCharNotOf(vararg chars: UreIR) = UreCharSet(chars.toSet(), positive = false) // TODO_later: jw
fun oneCharOfRange(from: UreIR, to: UreIR) = UreCharRange(from, to)

fun capt(content: Ure) = UreCaptGroup(content)
fun capt(init: UreProduct.() -> Unit) = capt(UreProduct(init))
fun ncapt(content: Ure) = UreNonCaptGroup(content)
fun ncapt(init: UreProduct.() -> Unit) = ncapt(UreProduct(init))
fun lookAhead(content: Ure, positive: Boolean = true) = UreLookGroup(content, true, positive)
fun lookAhead(positive: Boolean = true, init: UreProduct.() -> Unit) = lookAhead(UreProduct(init), positive)
fun lookBehind(content: Ure, positive: Boolean = true) = UreLookGroup(content, false, positive)
fun lookBehind(positive: Boolean = true, init: UreProduct.() -> Unit) = lookBehind(UreProduct(init), positive)


fun quantify(
    content: Ure,
    times: IntRange,
    reluctant: Boolean = false,
    possessive: Boolean = false,
) = if (times == 1..1) content else UreQuantifier(content, times, reluctant, possessive)

fun quantify(
    times: IntRange,
    reluctant: Boolean = false,
    possessive: Boolean = false,
    init: UreProduct.() -> Unit,
) = quantify(UreProduct(init), times, reluctant, possessive)

fun ref(nr: Int? = null, name: String? = null) = UreGroupRef(nr, name)

fun quote(string: String) = UreQuote(string)


operator fun MatchResult.get(name: String) = groups[name]!!.value
