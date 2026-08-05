package com.androiddevelopersguide.ui.screens.deployment

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.components.Section

@Composable
fun DeploymentScreen(
    navController: NavController,
    viewModel: DeploymentViewModel = hiltViewModel(),
) {
    PageScaffold(
        navController = navController,
        title = "Deployment",
        mobileUI = {
            Introduction()
            ApkVsAab()
            FlavorsAndVariants()
            Versioning()
            Signing()
            PlayConsole()
        }
    )
}

@Composable
fun Introduction() {
    Section(
        title = "What & why",
        description = """
            Deployment is everything between "the code compiles" and "a user installs the update". On Android that means producing a distributable artifact from a flavor and build type, signing it with a key the platform trusts, versioning it so the Play Store knows it is an upgrade, and pushing it through Play Console's testing tracks before it reaches everyone. The flavor dimension this app already defines (dev / staging / prod) is what makes the deployment story tractable: the same source compiles into clearly-labelled, side-by-side variants instead of one monolithic build.
        """.trimIndent(),
    )
}

@Composable
fun ApkVsAab() {
    Section(
        title = "APK vs AAB",
        description = """
            An APK is a complete, installable bundle — every device downloads the whole thing, including resources and ABIs it will never use. An AAB (Android App Bundle) is a publishing format: you upload it to Play, and Play generates and serves a stripped-down APK per device so a user on a small phone only downloads the resources and native code they need. Ship AABs to the Play Store; use APKs only for ad-hoc sharing or alternative stores.

            Both are produced by Gradle: ./gradlew assembleDevDebug makes an APK, ./gradlew bundleProdRelease makes an AAB.
        """.trimIndent(),
    )
}

@Composable
fun FlavorsAndVariants() {
    Section(
        title = "Flavors become deployable variants",
        description = """
            This app's "environment" flavor dimension produces three installable variants. dev and staging add an applicationIdSuffix and a versionNameSuffix, so they install next to the real app (no overwriting it) and are visibly tagged as non-release. prod carries the clean applicationId and is what end users receive.

            For local QA you typically share the dev or staging APK directly. For Play Store distribution you build the prod variant — bundleProdRelease for an AAB, assembleProdRelease for an APK.
        """.trimIndent(),
        code = """
            // Task names encode flavor x build type
            //   ./gradlew assembleDevDebug      -> dev debug APK (QA, manual sharing)
            //   ./gradlew assembleStagingDebug  -> staging debug APK
            //   ./gradlew bundleProdRelease     -> prod release AAB (Play Store upload)
        """.trimIndent()
    )
}

@Composable
fun Versioning() {
    Section(
        title = "versionCode & versionName",
        description = """
            versionCode is an integer the Play Store uses to decide what is an upgrade — every upload must have a higher versionCode than the last, or Play rejects it. versionName is the human-readable string users see ("1.0", "1.2.3"). They live together in defaultConfig.

            This app currently ships versionCode 1 and versionName "1.0". The dev/staging flavors add a versionNameSuffix (-dev, -staging) so a tester can read the variant off the about screen without opening the build settings.
        """.trimIndent(),
        code = """
            // app/build.gradle.kts
            defaultConfig {
                applicationId = "com.androiddevelopersguide"
                versionCode = 1
                versionName = "1.0"
            }

            create("dev") {
                versionNameSuffix = "-dev"     // displayed as "1.0-dev"
            }
        """.trimIndent()
    )
}

@Composable
fun Signing() {
    Section(
        title = "Signing the release",
        description = """
            Android requires every APK/AAB to be signed with a key before it can be installed outside a development device. Debug builds are signed automatically with a shared debug keystore. Release builds must be signed with your own upload key, which you generate once with keytool and keep secret — losing it means you can no longer update the app on the Play Store.

            The standard pattern is to keep the keystore out of version control and inject the credentials through a signingConfig that reads from a local properties file or environment variables. Enroll in Play App Signing and you sign uploads with your upload key while Google re-signs the distributed artifact with the app-signing key, which lets you rotate the upload key if it is lost.
        """.trimIndent(),
        code = """
            // release signing is wired via a signingConfig in android { }
            signingConfigs {
                create("release") {
                    storeFile = file(project.findProperty("RELEASE_STORE_FILE") ?: "")
                    storePassword = project.findProperty("RELEASE_STORE_PASSWORD") as String?
                    keyAlias = project.findProperty("RELEASE_KEY_ALIAS") as String?
                    keyPassword = project.findProperty("RELEASE_KEY_PASSWORD") as String?
                }
            }
            buildTypes {
                release {
                    signingConfig = signingConfigs.getByName("release")
                }
            }
        """.trimIndent()
    )
}

@Composable
fun PlayConsole() {
    Section(
        title = "Play Console & testing tracks",
        description = """
            Google Play Console is the distribution surface. After uploading a signed prod AAB you push it through progressively larger testing tracks before it reaches production: internal testing (fastest, a handful of testers), closed testing (a named group), open testing (anyone with a link), and finally production. Each track is gated by review, so a build that misbehaves can be caught at internal or closed before it reaches real users.

            What the prod flavor means here is concrete: prod has no applicationIdSuffix, no versionNameSuffix, and points at the production BASE_URL. That is the only variant that should ever be uploaded to a production track; dev and staging builds are for QA and emulators.
        """.trimIndent(),
    )
}

@Preview
@Composable
fun DeploymentScreenPreview() {
    DeploymentScreen(navController = rememberNavController())
}
