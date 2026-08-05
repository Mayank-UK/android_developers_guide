package com.androiddevelopersguide.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

/**
 * Screen-level scaffold with the quiet global app bar: a surface-toned container with a hairline
 * bottom divider, the screen [title], a hamburger (on the home route, opens the drawer) or back
 * arrow (anywhere with a back stack), the caller's [appBarActions], and the theme toggle as the
 * rightmost action.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PageScaffold(
    navController: NavController,
    title: String,
    appBarActions: @Composable RowScope.() -> Unit = {},
    mobileUI: @Composable () -> Unit,
    tabletUI: (@Composable () -> Unit)? = null,
    desktopUI: (@Composable () -> Unit)? = null,
    scrollable: Boolean = true,
) {
    val openDrawer = AppChrome.OpenDrawer.current
    val isDarkTheme = AppChrome.IsDarkTheme.current
    val toggleTheme = AppChrome.ToggleTheme.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title) },
                navigationIcon = {
                    if (navController.previousBackStackEntry != null) {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back button"
                            )
                        }
                    } else {
                        IconButton(onClick = openDrawer) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Open navigation drawer"
                            )
                        }
                    }
                },
                actions = {
                    appBarActions()
                    ThemeToggleButton(darkTheme = isDarkTheme, onToggle = toggleTheme)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                    navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
                    actionIconContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
            HorizontalDivider(
                thickness = DividerDefaults.Thickness,
                color = MaterialTheme.colorScheme.outlineVariant
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .then(if (scrollable) Modifier.verticalScroll(rememberScrollState()) else Modifier),
        ) {
            ResponsiveLayout(
                mobileUI = { mobileUI() },
                tabletUI = { (tabletUI ?: mobileUI)() },
                desktopUI = { (desktopUI ?: mobileUI)() }
            )
        }
    }
}
