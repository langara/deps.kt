@file:OptIn(ExperimentalFileSystem::class)

import okio.ExperimentalFileSystem
import okio.FileSystem
import okio.IOException
import okio.Path
import okio.Path.Companion.toPath
import okio.buffer
import org.gradle.api.Project
import kotlin.io.println
import kotlin.io.use
import kotlin.text.RegexOption.MULTILINE

@Throws(IOException::class)
fun FileSystem.findAllFiles(path: Path, maxDepth: Int = Int.MAX_VALUE): Sequence<Path> {
    require(maxDepth >= 0)
    val md = metadata(path)
    return when {
        md.isRegularFile -> sequenceOf(path)
        maxDepth < 1 || !md.isDirectory -> emptySequence()
        else -> list(path).asSequence().flatMap { findAllFiles(it, maxDepth - 1) }
    }
}


fun FileSystem.findAllKtFiles(root: Path, maxDepth: Int = Int.MAX_VALUE) =
    findAllFiles(root, maxDepth).filter { it.name.endsWith(".kt") }

fun FileSystem.commentOutMultiplatformFunInEachKtFile(root: Path) =
    findAllKtFiles(root).forEach { commentOutMultiplatformFunInFile(it) }

fun FileSystem.undoCommentOutMultiplatformFunInEachKtFile(root: Path) =
    findAllKtFiles(root).forEach { undoCommentOutMultiplatformFunInFile(it) }


/**
 * @param inputRootDir path of input root dir
 * @param outputRootDir path of output root dir - can be the same as inputRootDir;
 * nothing is written to file system if it's null;
 * @param process file content transformation; if it returns null - output file is not even touched
 */
fun FileSystem.processEachKtFile(
    inputRootDir: Path,
    outputRootDir: Path? = null,
    process: (input: Path, output: Path?, content: String) -> String?
) {
    require(inputRootDir.isAbsolute)
    require(outputRootDir?.isAbsolute ?: true)
    findAllKtFiles(inputRootDir).forEach { inputPath ->
        val outputPath = if (outputRootDir == null) null else outputRootDir / inputPath.asRelativeTo(inputRootDir)
        processFile(inputPath, outputPath) { content -> process(inputPath, outputPath, content) }
    }
}

fun Path.asRelativeTo(dir: Path): Path {
    require(this.isAbsolute)
    require(dir.isAbsolute)
    return when {
        this == dir -> ".".toPath()
        parent == dir -> this.name.toPath()
        parent == null -> error("Can not find $dir in $this")
        else -> parent!!.asRelativeTo(dir) / name
    }
}

val Project.rootOkioPath get(): Path = rootDir.toString().toPath()

/**
 * @param inputPath path of input file
 * @param outputPath path of output file - can be the same as inputPath;
 * nothing is written to file system if it's null;
 * @param process file content transformation; if it returns null - outputPath is not even touched
 */
fun FileSystem.processFile(inputPath: Path, outputPath: Path? = null, process: (String) -> String?) {

    val input = source(inputPath).buffer().use { it.readUtf8() }

    val output = process(input)

    if (outputPath == null) {
        if (output != null) println("Warning: ignoring non-null output because outputPath is null")
        return
    }

    if (output == null) {
        println("Ignoring outputPath because output to write is null")
        return
    }

    createDirectories(outputPath.parent!!)
    sink(outputPath).buffer().use { it.writeUtf8(output) }
}

fun FileSystem.commentOutMultiplatformFunInFile(file: Path) {
    println("\ncommenting: $file") // FIXME:remove/ulog

    processFile(file, file) { input ->

        val output1 = ureNotCommentedOutArea(ureExpectFun)
            .compile(MULTILINE)
            .replace(input) { "/*\n${it.value}\n*/" }

        val output2 = ir("actual fun").compile().replace(output1) { "/*actual*/ fun" }

        ir("actual suspend fun").compile().replace(output2) { "/*actual*/ suspend fun" }
        // FIXME: merge this two replaces to one using better URE
    }
}

fun FileSystem.undoCommentOutMultiplatformFunInFile(file: Path) {
    println("\nundo comments: $file") // FIXME:remove/ulog

    processFile(file, file) { input ->

        val myFun = ure("myFun") { 1 of ureExpectFun }

        val output1 = ureCommentedOutArea(myFun)
            .compile(MULTILINE)
            .replace(input) { it.groups["myFun"]!!.value }

        ir("/\\*actual\\*/")
            .compile()
            .replace(output1) { "actual" }
    }
}
