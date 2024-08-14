package com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.uimanipulation.subscreens.interactivebehaviours

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.exponentialDecay
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.components.Section
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun InteractiveBehavioursScreen(
    navController: NavController,
    viewModel: InteractiveBehavioursViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Interactive behaviours",
            )
        },
        mobileUI = {
            Tap()
            LongPress()
            Drag()
            Swipe()
            PinchZoom()
            Rotate()
            Fling()
        }
    )
}

@Composable
fun Tap() {
    Section(
        title = "Tap",
        description = """
            Handle a tap.
        """.trimIndent(),
        demo = {
            var clicked by remember { mutableStateOf(false) }

            Box(
                modifier = Modifier
                    .background(if (clicked) Color.Blue else Color.Black )
                    .clickable { clicked = !clicked }
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Tap this",
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        },
        code = """
            var clicked by remember { mutableStateOf(false) }

            Box(
                modifier = Modifier
                    .background(if (clicked) Color.Blue else Color.Black )
                    .clickable { clicked = !clicked }
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Tap this",
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        """.trimIndent()
    )
}

@Composable
fun LongPress() {
    Section(
        title = "Long press",
        description = """
            Handle a long press.
        """.trimIndent(),
        demo = {
            var longPressed by remember { mutableStateOf(false) }

            Box(
                modifier = Modifier
                    .background(if (longPressed) Color.Blue else Color.Black )
                    .pointerInput(Unit) {
                        detectTapGestures(onLongPress = { longPressed = !longPressed })
                    }
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Long press this",
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        },
        code = """
            var longPressed by remember { mutableStateOf(false) }

            Box(
                modifier = Modifier
                    .background(if (longPressed) Color.Blue else Color.Black )
                    .pointerInput(Unit) {
                        detectTapGestures(onLongPress = { longPressed = !longPressed })
                    }
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Long press this",
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        """.trimIndent()
    )
}

@Composable
fun Drag() {
    Section(
        title = "Drag",
        description = """
            Handle a drag.
            
            It did not worked in emulator and not tested on physical device.
        """.trimIndent(),
        demo = {
            var offsetX by remember { mutableStateOf(0f) }
            var offsetY by remember { mutableStateOf(0f) }

            Box(
                modifier = Modifier
                    .background(Color.Black)
                    .height(32.dp)
                    .width(64.dp)
                    .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDragStart = { },
                            onDragEnd = { },
                            onDragCancel = { },
                            onDrag = { change, dragAmount ->
                                change.consumeAllChanges()
                                offsetX += dragAmount.x
                                offsetY += dragAmount.y
                            }
                        )
                    }
            )
        },
        code = """
            var offsetX by remember { mutableStateOf(0f) }
            var offsetY by remember { mutableStateOf(0f) }

            Box(
                modifier = Modifier
                    .background(Color.Black)
                    .height(64.dp)
                    .width(128.dp)
                    .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDragStart = { },
                            onDragEnd = { },
                            onDragCancel = { },
                            onDrag = { change, dragAmount ->
                                change.consume()
                                offsetX += dragAmount.x
                                offsetY += dragAmount.y
                            }
                        )
                    }
            )
        """.trimIndent()
    )
}

@Composable
fun Swipe() {
    Section(
        title = "Swipe",
        description = """
            Handle a swipe.
        """.trimIndent(),
        demo = {
            var offsetX by remember { mutableStateOf(0f) }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .background(Color.Black)
                    .pointerInput(Unit) {
                        detectHorizontalDragGestures { change, dragAmount ->
                            offsetX += dragAmount
                            change.consume()
                        }
                    }
                    .offset(x = offsetX.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Swipe Me",
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        },
        code = """
            var offsetX by remember { mutableStateOf(0f) }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .background(Color.Black)
                    .pointerInput(Unit) {
                        detectHorizontalDragGestures { change, dragAmount ->
                            offsetX += dragAmount
                            change.consume()
                        }
                    }
                    .offset(x = offsetX.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Swipe Me",
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        """.trimIndent()
    )
}

@Composable
fun PinchZoom() {
    Section(
        title = "Zoom",
        description = """
            Handle a pinch zoom.
        """.trimIndent(),
        demo = {
            var scale by remember { mutableStateOf(1f) }

            Box(
                modifier = Modifier
                    .size(64.dp * scale)
                    .background(Color.Black)
                    .pointerInput(Unit) {
                        detectTransformGestures { _, _, zoom, _ ->
                            scale *= zoom
                            scale = scale.coerceIn(0.5f, 3f)
                        }
                    }
            )
        },
        code = """
            var scale by remember { mutableStateOf(1f) }

            Box(
                modifier = Modifier
                    .size(64.dp * scale)
                    .background(Color.Black)
                    .pointerInput(Unit) {
                        detectTransformGestures { _, _, zoom, _ ->
                            scale *= zoom
                            scale = scale.coerceIn(0.5f, 3f)
                        }
                    }
            )
        """.trimIndent()
    )
}

@Composable
fun Rotate() {
    Section(
        title = "Rotate",
        description = """
            Handle a rotate.
            
            Not tested on a real device.
        """.trimIndent(),
        demo = {
            var rotation by remember { mutableStateOf(0f) }

            Box(
                modifier = Modifier
                    .size(64.dp)
                    .background(Color.Black)
                    .pointerInput(Unit) {
                        detectTransformGestures { _, _, _, rotationChange ->
                            rotation += rotationChange
                        }
                    }
                    .graphicsLayer(rotationZ = rotation)
            )
        },
        code = """
            var rotation by remember { mutableStateOf(0f) }

            Box(
                modifier = Modifier
                    .size(64.dp)
                    .background(Color.Black)
                    .pointerInput(Unit) {
                        detectTransformGestures { _, _, _, rotationChange ->
                            rotation += rotationChange
                        }
                    }
                    .graphicsLayer(rotationZ = rotation)
            )
        """.trimIndent()
    )
}

@Composable
fun Fling() {
    Section(
        title = "Fling",
        description = """
            Handle a fling.
            
            It did not worked in emulator and not tested on physical device.
        """.trimIndent(),
        demo = {
            val offsetX = remember { Animatable(0f) }
            val offsetY = remember { Animatable(0f) }
            val coroutineScope = rememberCoroutineScope()
            val decay = exponentialDecay<Float>()

            Box(
                modifier = Modifier
                    .height(32.dp)
                    .width(64.dp)
                    .background(Color.Black)
                    .offset {
                        IntOffset(offsetX.value.roundToInt(), offsetY.value.roundToInt())
                    }
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDragEnd = {
                                coroutineScope.launch {
                                    offsetX.animateDecay(0f, decay)
                                    offsetY.animateDecay(0f, decay)
                                }
                            },
                            onDrag = { change, dragAmount ->
                                change.consumeAllChanges()
                                coroutineScope.launch {
                                    offsetX.snapTo(offsetX.value + dragAmount.x)
                                    offsetY.snapTo(offsetY.value + dragAmount.y)
                                }
                            }
                        )
                    }
            )
        },
        code = """
            val offsetX = remember { Animatable(0f) }
            val offsetY = remember { Animatable(0f) }
            val coroutineScope = rememberCoroutineScope()
            val decay = exponentialDecay<Float>()

            Box(
                modifier = Modifier
                    .height(32.dp)
                    .width(64.dp)
                    .background(Color.Black)
                    .offset {
                        IntOffset(offsetX.value.roundToInt(), offsetY.value.roundToInt())
                    }
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDragEnd = {
                                coroutineScope.launch {
                                    offsetX.animateDecay(0f, decay)
                                    offsetY.animateDecay(0f, decay)
                                }
                            },
                            onDrag = { change, dragAmount ->
                                change.consumeAllChanges()
                                coroutineScope.launch {
                                    offsetX.snapTo(offsetX.value + dragAmount.x)
                                    offsetY.snapTo(offsetY.value + dragAmount.y)
                                }
                            }
                        )
                    }
            )
        """.trimIndent()
    )
}


@Preview
@Composable
fun InteractiveBehavioursScreenPreview() {
    InteractiveBehavioursScreen(navController = rememberNavController())
}