package com.androiddevelopersguide.ui.activities

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.androiddevelopersguide.broadcastreceivers.NetworkConnectivityReceiver
import com.androiddevelopersguide.ui.components.AppChrome
import com.androiddevelopersguide.ui.components.resolveDarkTheme
import com.androiddevelopersguide.ui.navigation.NavGraph
import com.androiddevelopersguide.ui.navigation.NavRouter
import com.androiddevelopersguide.ui.screens.theme.ThemeViewModel
import com.androiddevelopersguide.ui.theme.AndroidDevelopersGuideTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var receiver: NetworkConnectivityReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        receiver = NetworkConnectivityReceiver()
        registerReceiver(receiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))

        setContent {
            val themeViewModel: ThemeViewModel = hiltViewModel()
            val appTheme by themeViewModel.theme.collectAsState()
            val darkTheme = resolveDarkTheme(appTheme)

            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val scope = rememberCoroutineScope()
            val navController = rememberNavController()

            val openDrawer: () -> Unit = { scope.launch { drawerState.open() } }
            val toggleTheme: () -> Unit = { themeViewModel.toggle() }

            AndroidDevelopersGuideTheme(darkTheme = darkTheme) {
                CompositionLocalProvider(
                    AppChrome.OpenDrawer provides openDrawer,
                    AppChrome.IsDarkTheme provides darkTheme,
                    AppChrome.ToggleTheme provides toggleTheme
                ) {
                    ModalNavigationDrawer(
                        drawerState = drawerState,
                        drawerContent = {
                            AppDrawer(
                                onNavigate = { route ->
                                    scope.launch { drawerState.close() }
                                    navController.navigate(route)
                                }
                            )
                        }
                    ) {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colorScheme.background
                        ) {
                            NavGraph(navController = navController)
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Guard against IllegalArgumentException if registration failed (e.g. no
        // CONNECTIVITY_ACTION delivery on the device) — receiver may not be registered.
        try {
            unregisterReceiver(receiver)
        } catch (_: IllegalArgumentException) {
            // Receiver was not registered; nothing to do.
        }
    }
}

@Composable
private fun AppDrawer(onNavigate: (String) -> Unit) {
    ModalDrawerSheet {
        NavigationDrawerItem(
            label = { Text("About") },
            selected = false,
            onClick = { onNavigate(NavRouter.AboutScreen.route) }
        )
    }
}
