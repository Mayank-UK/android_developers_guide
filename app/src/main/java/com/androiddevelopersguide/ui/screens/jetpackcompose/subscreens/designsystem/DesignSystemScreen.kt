package com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.designsystem

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.components.Section

@Composable
fun DesignSystemScreen(
    navController: NavController,
    viewModel: DesignSystemViewModel = hiltViewModel(),
) {
    PageScaffold(
        navController = navController,
        title = "Design system",
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
            Design system in android follows a standard provided by material design.
            
            The colors already predefined names like primary, secondary, surface, etc.
            
            Typography styles are also like bodySmall, bodyLarge, titleSmall, etc.
            
            The design system is used through MaterialTheme object.
        """.trimIndent(),
        code = """
            // dark scheme colors
            val PrimaryDark = Color(0xFF007aff)
            val PrimaryVariantDark = Color(0xFF005fb2)
            val SecondaryDark = Color(0xFF66BB6A)
            val BackgroundDark = Color(0xFF212121)
            val SurfaceDark = Color(0xFF333333)
            val ErrorDark = Color(0xFFB00020)

            val OnPrimaryDark = Color.White
            val OnSecondaryDark = Color.White
            val OnBackgroundDark = Color.White
            val OnSurfaceDark = Color.White
            val OnErrorDark = Color.White

            // light scheme colors
            val PrimaryLight = Color(0xFF007aff)
            val PrimaryVariantLight = Color(0xFF005fb2)
            val SecondaryLight = Color(0xFF9CCC65)
            val BackgroundLight = Color(0xFFFFFFFF)
            val SurfaceLight = Color(0xFFF7F7F7)
            val ErrorLight = Color(0xFFD50000)

            val OnPrimaryLight = Color.White
            val OnSecondaryLight = Color.Black
            val OnBackgroundLight = Color.Black
            val OnSurfaceLight = Color.Black
            val OnErrorLight = Color.Black
            
            // shapes
            val Shapes = Shapes(
                small = RoundedCornerShape(2.dp),
                medium = RoundedCornerShape(4.dp),
                large = RoundedCornerShape(8.dp)
            )
            
            // typography
            val Montserrat = FontFamily(
                Font(R.font.montserrat_regular),
                Font(R.font.montserrat_medium, FontWeight.Medium),
                Font(R.font.montserrat_bold, FontWeight.Bold)
            )

            val Typography = Typography(
                // Display styles
                displayLarge = TextStyle(
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Normal,
                    fontSize = 57.sp,
                    lineHeight = 64.sp,
                    letterSpacing = (-0.25).sp
                ),
                displayMedium = TextStyle(
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Normal,
                    fontSize = 45.sp,
                    lineHeight = 52.sp,
                    letterSpacing = 0.sp
                ),
                displaySmall = TextStyle(
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Normal,
                    fontSize = 36.sp,
                    lineHeight = 44.sp,
                    letterSpacing = 0.sp
                ),

                // Headline styles
                headlineLarge = TextStyle(
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Normal,
                    fontSize = 32.sp,
                    lineHeight = 40.sp,
                    letterSpacing = 0.sp
                ),
                headlineMedium = TextStyle(
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Normal,
                    fontSize = 28.sp,
                    lineHeight = 36.sp,
                    letterSpacing = 0.sp
                ),
                headlineSmall = TextStyle(
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Normal,
                    fontSize = 24.sp,
                    lineHeight = 32.sp,
                    letterSpacing = 0.sp
                ),

                // Title styles
                titleLarge = TextStyle(
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Normal,
                    fontSize = 22.sp,
                    lineHeight = 28.sp,
                    letterSpacing = 0.sp
                ),
                titleMedium = TextStyle(
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    letterSpacing = 0.15.sp
                ),
                titleSmall = TextStyle(
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    letterSpacing = 0.1.sp
                ),

                // Body styles
                bodyLarge = TextStyle(
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    letterSpacing = 0.5.sp
                ),
                bodyMedium = TextStyle(
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    letterSpacing = 0.25.sp
                ),
                bodySmall = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    letterSpacing = 0.4.sp
                ),

                // Label styles
                labelLarge = TextStyle(
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    letterSpacing = 0.1.sp
                ),
                labelMedium = TextStyle(
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    letterSpacing = 0.5.sp
                ),
                labelSmall = TextStyle(
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Medium,
                    fontSize = 11.sp,
                    lineHeight = 16.sp,
                    letterSpacing = 0.5.sp
                )
            )
            
            // sizes
            object Sizes {
                val xxs = 2.dp
                val xs = 4.dp
                val sm = 8.dp
                val md = 16.dp
                val lg = 32.dp
                val xl = 64.dp
                val xxl = 128.dp
            }
            
            // theme
            private val DarkColorScheme = darkColorScheme(
                primary = PrimaryDark,
                primaryContainer = PrimaryVariantDark,
                secondary = SecondaryDark,
                background = BackgroundDark,
                surface = SurfaceDark,
                error = ErrorDark,
                onPrimary = OnPrimaryDark,
                onSecondary = OnSecondaryDark,
                onBackground = OnBackgroundDark,
                onSurface = OnSurfaceDark,
                onError = OnErrorDark
            )

            private val LightColorScheme = lightColorScheme(
                primary = PrimaryLight,
                primaryContainer = PrimaryVariantLight,
                secondary = SecondaryLight,
                background = BackgroundLight,
                surface = SurfaceLight,
                error = ErrorLight,
                onPrimary = OnPrimaryLight,
                onSecondary = OnSecondaryLight,
                onBackground = OnBackgroundLight,
                onSurface = OnSurfaceLight,
                onError = OnErrorLight
            )


            @Composable
            fun AndroidDevelopersGuideTheme(
                darkTheme: Boolean = isSystemInDarkTheme(),
                // Dynamic color is available on Android 12+
                dynamicColor: Boolean = true,
                content: @Composable () -> Unit
            ) {
                val colorScheme = when {
                    dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
                        val context = LocalContext.current
                        if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
                    }

                    darkTheme -> DarkColorScheme
                    else -> LightColorScheme
                }

                MaterialTheme(
                    colorScheme = colorScheme,
                    typography = Typography,
                    content = content,
                    shapes = Shapes,
                )
            }
            
            // mainActivity
            @AndroidEntryPoint
            class MainActivity : ComponentActivity() {
                override fun onCreate(savedInstanceState: Bundle?) {
                    super.onCreate(savedInstanceState)
                    setContent {
                        AndroidDevelopersGuideTheme {
                            Surface(
                                modifier = Modifier.fillMaxSize(),
                                color = androidx.compose.ui.graphics.Color.White
                            ) {
                                val navController = rememberNavController()
                                NavGraph(navController = navController)
                            }
                        }
                    }
                }
            }
        """.trimIndent()
    )
}

@Preview
@Composable
fun TempScreenPreview() {
    DesignSystemScreen(navController = rememberNavController())
}