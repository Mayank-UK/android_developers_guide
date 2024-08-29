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
        title = "Widgets Screen",
        mobileUI = {
            Basics()
        }
    )
}

@Composable
fun Basics() {
    Section(
        title = "Basics",
        description = """
            Functionality to add widgets on home screen.
            
            As per the development of this app, widgets can only be created using XML layout.
            
            The main steps involve:
            
            1) Create the widget layout xml and related logic
            2) Create the widget info xml
            3) Create the widget provider
            4) Register the widget in manifest file
        """.trimIndent(),
        code = """
            // example_widget_layout.xml
            <?xml version="1.0" encoding="utf-8"?>
            <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:orientation="vertical"
                android:padding="16dp">

                <ImageView
                    android:id="@+id/widget_image"
                    android:layout_width="296dp"
                    android:layout_height="241dp"
                    android:layout_gravity="center_horizontal"
                    android:layout_marginBottom="8dp"
                    android:contentDescription="Example widget"
                    android:scaleType="centerCrop"
                    android:src="@drawable/my_image"
                    />

            </LinearLayout>
            
            // example_widget_info.xml
            <?xml version="1.0" encoding="utf-8"?>
            <appwidget-provider xmlns:android="http://schemas.android.com/apk/res/android"
            android:updatePeriodMillis="86400000"
            android:initialLayout="@layout/example_widget_layout"
            android:widgetCategory="home_screen"
            android:resizeMode="horizontal|vertical"
            android:minWidth="100dp"
                android:minHeight="100dp"
                android:previewImage="@drawable/my_image" />

            // 
            
            // ExampleWidgetProvider
            class ExampleWidgetProvider : AppWidgetProvider() {
                override fun onUpdate(
                    context: Context,
                    appWidgetManager: AppWidgetManager,
                    appWidgetIds: IntArray
                ) {
                    super.onUpdate(context, appWidgetManager, appWidgetIds)

                    for (appWidgetId in appWidgetIds) {
                        val intent = Intent(context, MainActivity::class.java)
                        val pendingIntent = PendingIntent.getActivity(
                            context,
                            0,
                            intent,
                            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE // Add FLAG_IMMUTABLE
                        )

                        val views = RemoteViews(context.packageName, R.layout.example_widget_layout).apply {
                            setOnClickPendingIntent(R.id.widget_image, pendingIntent)
                        }

                        appWidgetManager.updateAppWidget(appWidgetId, views)
                    }
                }
            }
            
            // register in manifest file
            ...
            <application>
                <receiver
                    android:name=".widgetproviders.ExampleWidgetProvider"
                    android:exported="true"
                    android:label="Example Widget"
                    android:icon="@mipmap/ic_launcher">
                <intent-filter>
                    <action android:name="android.appwidget.action.APPWIDGET_UPDATE" />
                </intent-filter>

                <meta-data
                    android:name="android.appwidget.provider"
                    android:resource="@xml/example_widget_info" />
                </receiver>
            </application>
            ...
        """.trimIndent()
    )
}

@Preview
@Composable
fun WidgetsScreenPreview() {
    WidgetsScreen(navController = rememberNavController())
}