package com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.uimanipulation.subscreens.webandapps

import android.content.Intent
import android.net.Uri
import android.webkit.WebViewClient
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.components.Section
import android.webkit.WebView
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun WebAndAppsScreen(
    navController: NavController,
    viewModel: WebAndAppsViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Web",
            )
        },
    ) {
        Hyperlink()
        WebView()
        AppRedirect()
    }
}

@Composable
fun Hyperlink() {
    Section(
        title = "Hyperlink",
        description = """
            Open a web link.
        """.trimIndent(),
        demo = {
            val uriHandler = LocalUriHandler.current
            val url = "https://www.example.com"

            val annotatedString = buildAnnotatedString {
                append("Open a link")
                addStringAnnotation(
                    tag = "URL",
                    annotation = url,
                    start = 0,
                    end = url.length
                )
            }

            ClickableText(
                text = annotatedString,
                onClick = { uriHandler.openUri(url) },
            )
        },
        code = """
            val uriHandler = LocalUriHandler.current
            val url = "https://www.example.com"

            val annotatedString = buildAnnotatedString {
                append("Click me!")
                addStringAnnotation(
                    tag = "URL",
                    annotation = url,
                    start = 0,
                    end = url.length
                )
            }

            ClickableText(
                text = annotatedString,
                onClick = { uriHandler.openUri(url) },
            )
        """.trimIndent()
    )
}

@Composable
fun WebView() {
    Section(
        title = "WebView",
        description = """
            Open web view to show web content inside mobile app.
        """.trimIndent(),
        demo = {
            AndroidView(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(256.dp),
                factory = { context ->
                    WebView(context).apply {
                        webViewClient = WebViewClient()
                        loadUrl("https://www.google.com")
                    }
                }
            )
        },
        code = """
            AndroidView(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(256.dp),
                factory = { context ->
                    WebView(context).apply {
                        webViewClient = WebViewClient()
                        loadUrl("https://www.google.com")
                    }
                }
            )
        """.trimIndent()
    )
}

@Composable
fun AppRedirect() {
    Section(
        title = "App redirect",
        description = """
            Open another app from current app.
        """.trimIndent(),
        demo = {
            val context = LocalContext.current

            Button(
                onClick = {
                    val packageName: String = "com.google.android.apps.maps"
                    val intent = context.packageManager.getLaunchIntentForPackage("packageName")

                    if (intent != null) {
                        context.startActivity(intent)
                    } else {
                        // App not installed, you can handle this case or provide a fallback
                        val appStoreIntent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName"))
                        context.startActivity(appStoreIntent)
                    }
                }
            ) {
                Text(text = "Open Google Maps")
            }
        },
        code = """
            val context = LocalContext.current

            Button(
                onClick = {
                    val packageName: String = "com.google.android.apps.maps"
                    val intent = context.packageManager.getLaunchIntentForPackage("packageName")

                    if (intent != null) {
                        context.startActivity(intent)
                    } else {
                        // App not installed, you can handle this case or provide a fallback
                        val appStoreIntent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$\packageName"))
                        context.startActivity(appStoreIntent)
                    }
                }
        """.trimIndent()
    )
}

@Preview
@Composable
fun HyperlinkScreenPreview() {
    WebAndAppsScreen(navController = rememberNavController())
}