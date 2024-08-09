package com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.uimanipulation.subscreens.layout.subscreens.layoutelements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.components.Section

@Composable
fun LayoutElementsScreen(
    navController: NavController,
    viewModel: LayoutElementsViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Layout elements",
            )
        },
    ) {
        BoxElement()
        ColumnElement()
        LazyColumnElement()
        RowElement()
        LazyRowElement()
        ConstraintLayoutElement()
        LazyVerticalGridElement()
        LazyHorizontalGridElement()
    }
}

@Composable
fun BoxElement() {
    Section(
        title = "Box",
        description = """
            A Box is a basic layout element that can contain a single child element. It can be used to add padding, margin, or background color to a single element.
        """.trimIndent(),
        demo = {
            Box(
                modifier = Modifier
                    .background(Color.Black)
                    .padding(16.dp)
            ) {
                Text(
                    "Hello",
                    color = Color.White
                )
            }
        },
        code = """
            Box(
                modifier = Modifier
                    .background(Color.Black)
                    .padding(16.dp)
            ) {
                Text(
                    "Hello",
                    color = Color.White
                )
            }
        """.trimIndent()
    )
}

@Composable
fun ColumnElement() {
    Section(
        title = "Column",
        description = """
            A Column is a vertical layout element that can contain multiple child elements. It can be used to arrange elements in a vertical stack.
        """.trimIndent(),
        demo = {
            Column{
                Box(
                    modifier = Modifier
                        .background(Color.Black)
                        .height(32.dp)
                        .width(64.dp)
                )
                Spacer(Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .background(Color.Black)
                        .height(32.dp)
                        .width(64.dp)
                )
                Spacer(Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .background(Color.Black)
                        .height(32.dp)
                        .width(64.dp)
                )
            }
        },
        code = """
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(Color.Black)
                        .height(32.dp)
                        .width(64.dp)
                )
                Spacer(Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .background(Color.Black)
                        .height(32.dp)
                        .width(64.dp)
                )
                Spacer(Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .background(Color.Black)
                        .height(32.dp)
                        .width(64.dp)
                )
            }
        """.trimIndent()
    )
}

@Composable
fun LazyColumnElement() {
    Section(
        title = "LazyColumn",
        description = """
            A LazyColumn is a vertical layout element that can contain a large number of child elements. It is optimized for performance and can be used to display a list of items.
            
            At the time of writing this code, fixed height needs to be set for lazycolumn or it won't display.
        """.trimIndent(),
        demo = {
            LazyColumn(Modifier.height(256.dp) ) {
                items(4) { index ->
                    Box(
                        modifier = Modifier
                            .background(Color.Black)
                            .height(32.dp)
                            .width(64.dp)
                            .padding(8.dp)
                    ) {
                        Text(
                            "Item $index",
                            color = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        },
        code = """
            LazyColumn(Modifier.height(256.dp) ) {
                items(4) { index ->
                    Box(
                        modifier = Modifier
                            .background(Color.Black)
                            .height(32.dp)
                            .width(64.dp)
                            .padding(8.dp)
                    ) {
                        Text(
                            "Item $\index",
                            color = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        """.trimIndent()
    )
}

@Composable
fun RowElement() {
    Section(
        title = "Row",
        description = """
            A Row is a horizontal layout element that can contain multiple child elements. It can be used to arrange elements in a horizontal row.
        """.trimIndent(),
        demo = {
            Row{
                Box(
                    modifier = Modifier
                        .background(Color.Black)
                        .height(32.dp)
                        .width(64.dp)
                )
                Spacer(Modifier.width(16.dp))
                Box(
                    modifier = Modifier
                        .background(Color.Black)
                        .height(32.dp)
                        .width(64.dp)
                )
                Spacer(Modifier.width(16.dp))
                Box(
                    modifier = Modifier
                        .background(Color.Black)
                        .height(32.dp)
                        .width(64.dp)
                )
            }
        },
        code = """
            Row{
                Box(
                    modifier = Modifier
                        .background(Color.Black)
                        .height(32.dp)
                        .width(64.dp)
                )
                Spacer(Modifier.width(16.dp))
                Box(
                    modifier = Modifier
                        .background(Color.Black)
                        .height(32.dp)
                        .width(64.dp)
                )
                Spacer(Modifier.width(16.dp))
                Box(
                    modifier = Modifier
                        .background(Color.Black)
                        .height(32.dp)
                        .width(64.dp)
                )
            }
        """.trimIndent()
    )
}

@Composable
fun LazyRowElement() {
    Section(
        title = "LazyRow",
        description = """
            A LazyRow is a horizontal layout element that can contain a large number of child elements. It is optimized for performance and can be used to display a list of items.
        """.trimIndent(),
        demo = {
            LazyRow {
                items(4) { index ->
                    Box(
                        modifier = Modifier
                            .background(Color.Black)
                            .height(32.dp)
                            .width(64.dp)
                            .padding(8.dp)
                    ) {
                        Text(
                            "Item $index",
                            color = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                }
            }
        },
        code = """
            LazyRow {
                items(4) { index ->
                    Box(
                        modifier = Modifier
                            .background(Color.Black)
                            .height(32.dp)
                            .width(64.dp)
                            .padding(8.dp)
                    ) {
                        Text(
                            "Item $\index",
                            color = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                }
            }
        """.trimIndent()
    )
}

@Composable
fun ConstraintLayoutElement() {
    Section(
        title = "ConstraintLayout",
        description = """
            A ConstraintLayout is a powerful layout element that can be used to create complex layouts. It allows you to define constraints between elements to position them on the screen.
            
            This is not part of the default compose ui, it needs to be added as a separate dependency.
        """.trimIndent(),
    )
}

@Composable
fun LazyVerticalGridElement() {
    Section(
        title = "LazyVerticalGrid",
        description = """
          A Grid is a layout element that can be used to arrange elements in a grid. It can be used to create complex layouts with multiple rows and columns.  
        """.trimIndent(),
        demo = {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(128.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")) { item ->
                    Box(
                        modifier = Modifier
                            .background(Color.Black)
                            .padding(8.dp)
                    ) {
                        Text(
                            "Item $item",
                            color = Color.White
                        )
                    }
                }
            }
        },
        code = """
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(128.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")) { item ->
                    Box(
                        modifier = Modifier
                            .background(Color.Black)
                            .padding(8.dp)
                    ) {
                        Text(
                            "Item $\item",
                            color = Color.White
                        )
                    }
                }
            }
        """.trimIndent()
    )
}

@Composable
fun LazyHorizontalGridElement() {
    Section(
        title = "LazyHorizontalGrid",
        description = """
          A Grid is a layout element that can be used to arrange elements in a grid. It can be used to create complex layouts with multiple rows and columns.  
        """.trimIndent(),
        demo = {
            LazyHorizontalGrid(
                rows = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(96.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")) { item ->
                    Box(
                        modifier = Modifier
                            .background(Color.Black)
                            .padding(8.dp)
                    ) {
                        Text(
                            "Item $item",
                            color = Color.White
                        )
                    }
                }
            }
        },
        code = """
            LazyHorizontalGrid(
                rows = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(96.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")) { item ->
                    Box(
                        modifier = Modifier
                            .background(Color.Black)
                            .padding(8.dp)
                    ) {
                        Text(
                            "Item $\item",
                            color = Color.White
                        )
                    }
                }
            }
        """.trimIndent()
    )
}


@Preview
@Composable
fun LayoutElementsScreenPreview() {
    LayoutElementsScreen(navController = rememberNavController())
}