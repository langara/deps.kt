@file:Suppress("unused", "SpellCheckingInspection", "MemberVisibilityCanBePrivate")

/**
 * Common dependencies for java/kotlin/android projects
 *
 * @see <a href="https://github.com/langara/deps.kt">https://github.com/langara/deps.kt</a>
 */
object Deps {
    val kotlinGradlePlugin = dep("org.jetbrains.kotlin", "kotlin-gradle-plugin", Vers.kotlin)
    val androidGradlePlugin = dep("com.android.tools.build", "gradle", Vers.androidGradlePlugin)
    val androidMavenGradlePlugin = dep("com.github.dcendents", "android-maven-gradle-plugin", Vers.androidMavenGradlePlugin)
    val kotlinStdlib7 = dep("org.jetbrains.kotlin", "kotlin-stdlib-jdk7", Vers.kotlin)
    val kotlinStdlib8 = dep("org.jetbrains.kotlin", "kotlin-stdlib-jdk8", Vers.kotlin)
    val kotlinReflect = dep("org.jetbrains.kotlin", "kotlin-reflect", Vers.kotlin)
    val kotlinTestCommon = dep("org.jetbrains.kotlin", "kotlin-test-common", Vers.kotlin)
    val kotlinTestAnnotationsCommon = dep("org.jetbrains.kotlin", "kotlin-test-annotations-common", Vers.kotlin)
    val kotlinTestJUnit = dep("org.jetbrains.kotlin", "kotlin-test-junit", Vers.kotlin)
    val kotlinTestJs = dep("org.jetbrains.kotlin", "kotlin-test-js", Vers.kotlin)

    val composeDesktopGradlePlugin = dep("org.jetbrains.compose", "compose-gradle-plugin", Vers.composeDesktop)
    val composeAndroidAnimation = dep("androidx.compose.animation", "animation", Vers.composeAndroid)
    val composeAndroidAnimationCore = dep("androidx.compose.animation", "animation-core", Vers.composeAndroid)
    val composeAndroidCompiler = dep("androidx.compose.compiler", "compiler", Vers.composeAndroid)
    val composeAndroidFoundation = dep("androidx.compose.foundation", "foundation", Vers.composeAndroid)
    val composeAndroidFoundationLayout = dep("androidx.compose.foundation", "foundation-layout", Vers.composeAndroid)
    val composeAndroidFoundationShape = dep("androidx.compose.foundation", "foundation-shape", Vers.composeAndroid)
    val composeAndroidFoundationText = dep("androidx.compose.foundation", "foundation-text", Vers.composeAndroid)
    val composeAndroidMaterial = dep("androidx.compose.material", "material", Vers.composeAndroid)
    val composeAndroidMaterialIcons = dep("androidx.compose.material", "material-icons", Vers.composeAndroid)
    val composeAndroidRuntime = dep("androidx.compose.runtime", "runtime", Vers.composeAndroid)
    val composeAndroidRuntimeDispatch = dep("androidx.compose.runtime", "runtime-dispatch", Vers.composeAndroid)
    val composeAndroidRuntimeFrames = dep("androidx.compose.runtime", "runtime-frames", Vers.composeAndroid)
    val composeAndroidUi = dep("androidx.compose.ui", "ui", Vers.composeAndroid)
    val composeAndroidUiGeometry = dep("androidx.compose.ui", "ui-geometry", Vers.composeAndroid)
    val composeAndroidUiGraphics = dep("androidx.compose.ui", "ui-graphics", Vers.composeAndroid)
    val composeAndroidUiPlatform = dep("androidx.compose.ui", "ui-platform", Vers.composeAndroid)
    val composeAndroidUiTest = dep("androidx.compose.ui", "ui-test", Vers.composeAndroid)
    val composeAndroidUiTooling = dep("androidx.compose.ui", "ui-tooling", Vers.composeAndroid)

    val kotlinxDateTime = dep("org.jetbrains.kotlinx", "kotlinx-datetime", Vers.kotlinxDateTime)

    val kotlinxCoroutinesCommon = dep("org.jetbrains.kotlinx", "kotlinx-coroutines-core-common", Vers.kotlinxCoroutines)
    val kotlinxCoroutinesCore = dep("org.jetbrains.kotlinx", "kotlinx-coroutines-core", Vers.kotlinxCoroutines)
    val kotlinxCoroutinesDebug = dep("org.jetbrains.kotlinx", "kotlinx-coroutines-debug", Vers.kotlinxCoroutines)
    val kotlinxCoroutinesTest = dep("org.jetbrains.kotlinx", "kotlinx-coroutines-test", Vers.kotlinxCoroutines)
    val kotlinxCoroutinesReactive = dep("org.jetbrains.kotlinx", "kotlinx-coroutines-reactive", Vers.kotlinxCoroutines)
    val kotlinxCoroutinesReactor = dep("org.jetbrains.kotlinx", "kotlinx-coroutines-reactor", Vers.kotlinxCoroutines)
    val kotlinxCoroutinesRx2 = dep("org.jetbrains.kotlinx", "kotlinx-coroutines-rx2", Vers.kotlinxCoroutines)
    val kotlinxCoroutinesAndroid = dep("org.jetbrains.kotlinx", "kotlinx-coroutines-android", Vers.kotlinxCoroutines)
    val kotlinxCoroutinesJavaFx = dep("org.jetbrains.kotlinx", "kotlinx-coroutines-javafx", Vers.kotlinxCoroutines)
    val kotlinxCoroutinesSwing = dep("org.jetbrains.kotlinx", "kotlinx-coroutines-swing", Vers.kotlinxCoroutines)
    val kotlinxCoroutinesJdk8 = dep("org.jetbrains.kotlinx", "kotlinx-coroutines-jdk8", Vers.kotlinxCoroutines)
    val kotlinxCoroutinesGuava = dep("org.jetbrains.kotlinx", "kotlinx-coroutines-quava", Vers.kotlinxCoroutines)
    val kotlinxCoroutinesSlf4j = dep("org.jetbrains.kotlinx", "kotlinx-coroutines-slf4j", Vers.kotlinxCoroutines)
    val kotlinxCoroutinesPlayServices = dep("org.jetbrains.kotlinx", "kotlinx-coroutines-play-services", Vers.kotlinxCoroutines)

    // just a reference - not useful in typical cases
    const val gradleBaseUrl = "https://services.gradle.org/distributions"
    const val gradleUrlBin = "$gradleBaseUrl/gradle-${Vers.gradle}-bin.zip"
    const val gradleUrlAll = "$gradleBaseUrl/gradle-${Vers.gradle}-all.zip"

    val androidxCore = dep("androidx.core", "core", Vers.androidxCore)
    val androidxCoreKtx = dep("androidx.core", "core-ktx", Vers.androidxCore)
    val androidxAppcompat = dep("androidx.appcompat", "appcompat", Vers.androidxAppcompat)
    val androidxRecyclerview = dep("androidx.recyclerview", "recyclerview", Vers.androidxRecyclerview)
    val androidxCardview = dep("androidx.cardview", "cardview", Vers.androidxCardview)
    val androidMaterial = dep("com.google.android.material", "material", Vers.androidMaterial)
    val androidxAnnotation = dep("androidx.annotation", "annotation", Vers.androidxAnnotation)
    val androidxPreference = dep("androidx.preference", "preference", Vers.androidxPreference)
    val androidxPreferenceKtx = androidxPreference + ("name" to "preference-ktx")
    val androidxBrowser = dep("androidx.browser", "browser", Vers.androidxBrowser)
    val androidxPercentLayout = dep("androidx.percentlayout", "percentlayout", Vers.androidxPercentLayout)
    val androidxFlexboxLayout = dep("com.google.android", "flexbox", Vers.androidxFlexboxLayout)
    val androidxConstraint1 = dep("androidx.constraintlayout", "constraintlayout", Vers.androidxConstraint1)
    val androidxConstraint2 = androidxConstraint1 + ("version" to Vers.androidxConstraint2)
    val androidxConstraint = androidxConstraint1
    val androidxConstraint1Solver = androidxConstraint1 + ("name" to "constraintlayout-solver")
    val androidxConstraint2Solver = androidxConstraint1Solver + ("version" to Vers.androidxConstraint2)
    val androidxConstraintSolver = androidxConstraint1Solver

    val androidxLifecycleCommon = dep("androidx.lifecycle", "lifecycle-common", Vers.androidxLifecycle)
    val androidxLifecycleCompiler = androidxLifecycleCommon + ("name" to "lifecycle-compiler")
    val androidxLifecycleExtensions = androidxLifecycleCommon + ("name" to "lifecycle-extensions")
    val androidxLifecycleLiveData = androidxLifecycleCommon + ("name" to "lifecycle-livedata")
    val androidxLifecycleLiveDataCore = androidxLifecycleCommon + ("name" to "lifecycle-livedata-core")
    val androidxLifecycleLiveDataCoreKtx = androidxLifecycleCommon + ("name" to "lifecycle-livedata-core-ktx")
    val androidxLifecycleViewModel = androidxLifecycleCommon + ("name" to "lifecycle-viewmodel")
    val androidxLifecycleViewModelKtx = androidxLifecycleCommon + ("name" to "lifecycle-viewmodel-ktx")

    val androidxRoomRuntime = dep("androidx.room", "room-runtime", Vers.androidxRoom)
    val androidxRoomCompiler = dep("androidx.room", "room-compiler", Vers.androidxRoom)
    val androidxRoomKtx = dep("androidx.room", "room-ktx", Vers.androidxRoom)
    val androidxRoomRxJava2 = dep("androidx.room", "room-rxjava2", Vers.androidxRoom)
    val androidxRoomTesting = dep("androidx.room", "room-testing", Vers.androidxRoom)

    val androidxEspressoAccessibility = dep("androidx.test.espresso", "espresso-accessibility", Vers.androidxEspresso)
    val androidxEspressoContrib = dep("androidx.test.espresso", "espresso-contrib", Vers.androidxEspresso)
    val androidxEspressoCore = dep("androidx.test.espresso", "espresso-core", Vers.androidxEspresso)
    val androidxEspressoIdlingResource = dep("androidx.test.espresso", "espresso-idling-resource", Vers.androidxEspresso)
    val androidxEspressoIntents = dep("androidx.test.espresso", "espresso-intents", Vers.androidxEspresso)
    val androidxEspressoRemote = dep("androidx.test.espresso", "espresso-remote", Vers.androidxEspresso)
    val androidxEspressoWeb = dep("androidx.test.espresso", "espresso-web", Vers.androidxEspresso)

    val androidCommonsEspresso = dep("com.github.elpassion.android-commons", "espresso", Vers.androidCommons)
    val androidCommonsRxJavaTest = dep("com.github.elpassion.android-commons", "rxjava-test", Vers.androidCommons)
    val androidCommonsSharedPrefs = dep("com.github.elpassion.android-commons", "shared-preferences", Vers.androidCommons)
    val androidCommonsSharedPrefsMoshi = dep("com.github.elpassion.android-commons", "shared-preferences-moshi-converter-adapter", Vers.androidCommons)
    val androidCommonsSharedPrefsGson = dep("com.github.elpassion.android-commons", "shared-preferences-gson-converter-adapter", Vers.androidCommons)
    val androidCommonsView = dep("com.github.elpassion.android-commons", "view", Vers.androidCommons)
    val androidCommonsPager = dep("com.github.elpassion.android-commons", "pager", Vers.androidCommons)
    val androidCommonsRecycler = dep("com.github.elpassion.android-commons", "recycler", Vers.androidCommons)

    val rxjava2 = dep("io.reactivex.rxjava2", "rxjava", Vers.rxjava2)
    val rxjava3 = dep("io.reactivex.rxjava3", "rxjava", Vers.rxjava3)
    val rxkotlin = dep("io.reactivex.rxjava2", "rxkotlin", Vers.rxkotlin)
    val rxandroid = dep("io.reactivex.rxjava2", "rxandroid", Vers.rxandroid)
    val rxrelay = dep("com.jakewharton.rxrelay2", "rxrelay", Vers.rxrelay)
    val rxbinding = dep("com.jakewharton.rxbinding3", "rxbinding", Vers.rxbinding)
    val rxbindingCore = rxbinding + ("name" to "rxbinding-core")
    val rxbindingAppCompat = rxbinding + ("name" to "rxbinding-appcompat")
    val rxbindingDrawerLayout = rxbinding + ("name" to "rxbinding-drawerlayout")
    val rxbindingLeanback = rxbinding + ("name" to "rxbinding-leanback")
    val rxbindingRecyclerView = rxbinding + ("name" to "rxbinding-recyclerview")
    val rxbindingSlidingPaneLayout = rxbinding + ("name" to "rxbinding-slidingpanelayout")
    val rxbindingSwipeRefreshLayout = rxbinding + ("name" to "rxbinding-swiperefreshlayout")
    val rxbindingViewPager = rxbinding + ("name" to "rxbinding-viewpager")
    val rxlifecycleComponents = dep("com.trello.rxlifecycle2", "rxlifecycle-components", Vers.rxlifecycle)
    val rxlifecycleKotlin = dep("com.trello.rxlifecycle2", "rxlifecycle-kotlin", Vers.rxlifecycle)
    val retrofit = dep("com.squareup.retrofit2", "retrofit", Vers.retrofit)
    val retrofitMoshi = dep("com.squareup.retrofit2", "converter-moshi", Vers.retrofit)
    val retrofitRxjava = dep("com.squareup.retrofit2", "adapter-rxjava2", Vers.retrofit)
    val okhttp = dep("com.squareup.okhttp3", "okhttp", Vers.okhttp)
    val okhttpLogging = dep("com.squareup.okhttp3", "logging-interceptor", Vers.okhttp)
    val dbusJava = dep("com.github.hypfvieh", "dbus-java", Vers.dbusJava)
    val dbusJavaOsgi = dep("com.github.hypfvieh", "dbus-java-osgi", Vers.dbusJava)
    val dbusJavaUtils = dep("com.github.hypfvieh", "dbus-java-utils", Vers.dbusJava)
    val javaWebsocket = dep("org.java-websocket", "java-websocket", Vers.javaWebsocket)
    val slf4jSimple = dep("org.slf4j", "slf4j-simple", Vers.slf4jSimple)
    val log4j2api = dep("org.apache.logging.log4j", "log4j-api", Vers.log4j2)
    val log4j2core = dep("org.apache.logging.log4j", "log4j-core", Vers.log4j2)

    val googleServicesPlugin = dep("com.google.gms", "google-services", Vers.googleServicesPlugin)
    val googlePlayServicesBase = dep("com.google.android.gms", "play-services-base", Vers.googlePlayServicesBase)

    val firebaseGitliveAuth = dep("dev.gitlive", "firebase-auth", Vers.firebaseGitlive)
    val firebaseGitliveDB = dep("dev.gitlive", "firebase-database", Vers.firebaseGitlive)
    val firebaseGitliveFirestore = dep("dev.gitlive", "firebase-firestore", Vers.firebaseGitlive)
    val firebaseGitliveFunctions = dep("dev.gitlive", "firebase-functions", Vers.firebaseGitlive)
    val firebaseGitliveMessaging = dep("dev.gitlive", "firebase-messaging", Vers.firebaseGitlive)
    val firebaseGitliveStorage = dep("dev.gitlive", "firebase-storage", Vers.firebaseGitlive)
    val firebaseCrashlyticsPlugin = dep("com.google.firebase", "firebase-crashlytics-gradle", Vers.firebaseCrashlyticsPlugin)

    val firebaseAdmin = dep("com.google.firebase", "firebase-admin", Vers.firebaseAdmin)

    val firebaseAndroidBoM = dep("com.google.firebase", "firebase-bom", Vers.firebaseAndroidBoM)
    val firebaseAnalyticsKtx = dep("com.google.firebase", "firebase-analytics-ktx")
    val firebaseCrashlyticsKtx = dep("com.google.firebase", "firebase-crashlytics-ktx")
    val firebaseAppIndexingKtx = dep("com.google.firebase", "firebase-appindexing-ktx")
    val firebaseFirestoreKtx = dep("com.google.firebase", "firebase-firestore-ktx")
    val firebaseFunctionsKtx = dep("com.google.firebase", "firebase-functions-ktx")
    val firebaseAuthKtx = dep("com.google.firebase", "firebase-auth-ktx")
    val firebaseMessagingKtx = dep("com.google.firebase", "firebase-messaging-ktx")
    val firebaseInAppMessagingKtx = dep("com.google.firebase", "firebase-inappmessaging-ktx")
    val firebasePerformanceKtx = dep("com.google.firebase", "firebase-perf-ktx")
    val firebaseStorageKtx = dep("com.google.firebase", "firebase-storage-ktx")
    val firebaseRealtimeDbKtx = dep("com.google.firebase", "firebase-database-ktx")
    val firebaseRemoteConfigKtx = dep("com.google.firebase", "firebase-config-ktx")
    val firebaseDynamicLinksKtx = dep("com.google.firebase", "firebase-dynamic-links-ktx")

    val firebaseUiAuth = dep("com.firebaseui", "firebase-ui-auth", Vers.firebaseUiAuth)

    val googleCloudBoM = dep("com.google.cloud", "libraries-bom", Vers.googleCloudBoM)
        // FIXME: some extension functions for BoM deps, so its easier to add it to multiplatform projects than:
        // implementation(project.dependencies.platform(Deps.googleCloudBoM))
        // and to make it impossible to mistakenly add it as normal dependency like:
        // implementation(Deps.googleCloudBoM)

    val googleCloudStorage = dep("com.google.cloud", "google-cloud-storage")
    val googleCloudFirestore = dep("com.google.cloud", "google-cloud-firestore")

    val googleAuthCredentials = dep("com.google.auth", "google-auth-library-credentials", Vers.googleAuth)
    val googleAuthOAuth2Http = dep("com.google.auth", "google-auth-library-oauth2-http", Vers.googleAuth)
    val googleAuthAppEngine = dep("com.google.auth", "google-auth-library-appengine", Vers.googleAuth)

    val googleGuava = dep("com.google.guava", "guava") // ver from googleCloudBoM

    val picasso = dep("com.squareup.picasso", "picasso", Vers.picasso)
    val materialDialogs = dep("com.afollestad.material-dialogs", "core", Vers.materialDialogs)
    val leakcanary = dep("com.squareup.leakcanary", "leakcanary-android", Vers.leakcanary)
    val paperwork = dep("hu.supercluster", "paperwork", Vers.paperwork)
    val paperworkPlugin = dep("hu.supercluster", "paperwork-plugin", Vers.paperwork)
    val junit4 = dep("junit", "junit", Vers.junit4)
    val junit5 = dep("org.junit.jupiter", "junit-jupiter-api", Vers.junit5)
    val junit5engine = dep("org.junit.jupiter", "junit-jupiter-engine", Vers.junit5)
    val tuplek = dep("com.github.langara", "tuplek", Vers.tuplek)
    val abcdk = dep("com.github.langara", "abcdk", Vers.abcdk)
    val rxmock = dep("com.github.langara", "rxmock", Vers.rxmock)
    val smokk = dep("com.github.langara", "smokk", Vers.smokk)
    val uspek = dep("com.github.langara.uspek", "uspek", Vers.uspek)
    val upue = dep("com.github.langara.upue", "upue", Vers.upue)
    val dbusKotlin = dep("com.github.langara", "dbus-kotlin", Vers.dbusKotlin)
    val sandboxui = dep("com.github.langara", "sandboxui", Vers.sandboxui)
    val recyclerui = dep("com.github.langara", "recyclerui", Vers.recyclerui)
    val googleTruth = dep("com.google.truth", "truth", Vers.googleTruth)
    val mockitoKotlin = dep("com.nhaarman.mockitokotlin2", "mockito-kotlin", Vers.mockitoKotlin)
    val androidTestRunner = dep("androidx.test", "runner", Vers.androidxTestRunner)
    val androidTestRules = dep("androidx.test", "rules", Vers.androidxTestRules)
    val realmGradlePlugin = dep("io.realm", "realm-gradle-plugin", Vers.realm)
    val ktorServerCore = dep("io.ktor", "ktor-server-core", Vers.ktor)
    val ktorServerCio = dep("io.ktor", "ktor-server-cio", Vers.ktor)
    val ktorServerNetty = dep("io.ktor", "ktor-server-netty", Vers.ktor)
    val ktorAuth = dep("io.ktor", "ktor-auth", Vers.ktor)
    val ktorClientCore = dep("io.ktor", "ktor-client-core", Vers.ktor)
    val ktorClientCio = dep("io.ktor", "ktor-client-cio", Vers.ktor)
    val ktorClientApache = dep("io.ktor", "ktor-client-apache", Vers.ktor)
    val rsocketCore = dep("io.rsocket.kotlin", "rsocket-core", Vers.rsocket)
    val rsocketKtor = dep("io.rsocket.kotlin", "rsocket-transport-ktor", Vers.rsocket)
    val rsocketKtorClient = dep("io.rsocket.kotlin", "rsocket-transport-ktor-client", Vers.rsocket)
    val rsocketKtorServer = dep("io.rsocket.kotlin", "rsocket-transport-ktor-server", Vers.rsocket)
    val splitties = dep("com.louiscad.splitties", "splitties-fun-pack-android-material-components-with-views-dsl", Vers.splitties)
    val docoptJava = dep("com.offbytwo", "docopt", Vers.docoptJava)

    private fun dep(group: String, name: String, version: String? = null) = mapOf(
        "group" to group,
        "name" to name,
        "version" to version
    )
}

