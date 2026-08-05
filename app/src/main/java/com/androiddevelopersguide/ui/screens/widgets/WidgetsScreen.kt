package com.androiddevelopersguide.ui.screens.widgets

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.components.Section

@Composable
fun WidgetsScreen(
    navController: NavController,
    viewModel: WidgetsViewModel = hiltViewModel(),
) {
    PageScaffold(
        navController = navController,
        title = "Widgets",
        mobileUI = {
            Introduction()
            Anatomy()
            Implementation()
        }
    )
}

@Composable
fun Introduction() {
    Section(
        title = "What is a home-screen widget",
        description = """
            An app widget is a small, lightweight view that the system renders on the home screen or lock screen, hosted by the launcher. It runs in your app's process but is displayed by another app, so it is built with RemoteViews — a restricted, serialisable view toolkit the launcher can inflate safely — rather than Compose or arbitrary View code. Widgets are how you surface glanceable information (a now-playing control, a countdown, a quick action) without the user opening the app.

            As of the development of this guide, widgets must be built with XML layouts and RemoteViews; Compose Glance is the modern path but is covered separately.
        """.trimIndent(),
    )
}

@Composable
fun Anatomy() {
    Section(
        title = "The three pieces",
        description = """
            A widget is the cooperation of three things:

            -AppWidgetProviderInfo (XML in res/xml): the metadata — update period, minimum size, resize mode, preview image, the layout to inflate.
            -AppWidgetProvider (a BroadcastReceiver subclass): the code that updates the widget's RemoteViews in response to system broadcasts (APPWIDGET_UPDATE, etc.).
            -The layout (XML in res/layout): the RemoteViews tree the launcher inflates.

            The provider is registered in AndroidManifest.xml with an intent filter for APPWIDGET_UPDATE and a <meta-data> link to the info XML, so the launcher knows the receiver is a widget.
        """.trimIndent(),
    )
}

@Composable
fun Implementation() {
    Section(
        title = "Implementation steps",
        description = """
            The build is mechanical once the three pieces exist: create the layout, create the info XML, write the provider that builds RemoteViews and wires click handling through a PendingIntent, then register the provider in the manifest. Always pass PendingIntent.FLAG_IMMUTABLE (or FLAG_MUTABLE deliberately) on Android 12+ — omitting the flag throws at runtime on newer APIs.
        """.trimIndent(),
        code = """
            // res/xml/example_widget_info.xml
            <appwidget-provider xmlns:android="http://schemas.android.com/apk/res/android"
                android:updatePeriodMillis="86400000"
                android:initialLayout="@layout/example_widget_layout"
                android:widgetCategory="home_screen"
                android:resizeMode="horizontal|vertical"
                android:minWidth="100dp"
                android:minHeight="100dp"
                android:previewImage="@drawable/my_image" />

            // ExampleWidgetProvider.kt
            class ExampleWidgetProvider : AppWidgetProvider() {
                override fun onUpdate(
                    context: Context,
                    appWidgetManager: AppWidgetManager,
                    appWidgetIds: IntArray
                ) {
                    for (appWidgetId in appWidgetIds) {
                        val pendingIntent = PendingIntent.getActivity(
                            context, 0,
                            Intent(context, MainActivity::class.java),
                            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                        )
                        val views = RemoteViews(context.packageName, R.layout.example_widget_layout)
                            .apply { setOnClickPendingIntent(R.id.widget_image, pendingIntent) }
                        appWidgetManager.updateAppWidget(appWidgetId, views)
                    }
                }
            }

            // AndroidManifest.xml
            <receiver
                android:name=".widgetproviders.ExampleWidgetProvider"
                android:exported="true"
                android:label="Example Widget">
                <intent-filter>
                    <action android:name="android.appwidget.action.APPWIDGET_UPDATE" />
                </intent-filter>
                <meta-data
                    android:name="android.appwidget.provider"
                    android:resource="@xml/example_widget_info" />
            </receiver>
        """.trimIndent()
    )
}

@Preview
@Composable
fun WidgetsScreenPreview() {
    WidgetsScreen(navController = rememberNavController())
}
