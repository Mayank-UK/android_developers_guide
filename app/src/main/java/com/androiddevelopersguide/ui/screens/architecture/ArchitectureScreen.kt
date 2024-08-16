package com.androiddevelopersguide.ui.screens.architecture

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.components.Section

@Composable
fun ArchitectureScreen(
    navController: NavController,
    viewModel: ArchitectureViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Architecture",
            )
        },
        mobileUI = {
            CleanMvvm()
        }
    )
}

@Composable
fun CleanMvvm() {
    Section(
        title = "Clean MVVM",
        description = """
            Clean MVVM architecture in Android is a way of organizing your code to promote separation of concerns, maintainability, testability, and scalability. It builds upon the Model-View-ViewModel (MVVM) design pattern by incorporating principles from Clean Architecture, which was introduced by Robert C. Martin (Uncle Bob).
            
            Key Principles of Clean MVVM Architecture:
            -Separation of Concerns
            -Dependency Injection
            -Single Responsibility Principle (SRP)
            -Repository Pattern
            -Use Cases
            -Testability
            -UI/State Management
            -Reactive Streams
            
            Structure of Clean MVVM Architecture:
            
            exampleapp/
            |    data/
            |    |    local/
            |    |    |    daos/
            |    |    |    |    ExampleDao.kt
            |    |    |    entities/
            |    |    |    |    ExampleEntity.kt
            |    |    |    AppDatabase.kt
            |    |    mappers/
            |    |    |    ExampleMapper.kt
            |    |    remote/
            |    |    |    dtos/
            |    |    |    |    ExampleDto.kt
            |    |    |    ApiService.kt
            |    |    repositoryimplementations/
            |    |    |    ExampleRepositoryImpl.kt
            |    di/
            |    |    DatabaseModule.kt
            |    |    NetworkModule.kt
            |    |    RepositoryModule.kt
            |    domain/
            |    |    models/
            |    |    |    Example.kt
            |    |    repositoryinterfaces/
            |    |    |    ExampleRepository.kt
            |    |    usercases/
            |    |    |    ExampleUseCase.kt
            |    ui/
            |    |    activities/
            |    |    |    MainActivity.kt
            |    |    |    ExampleActivity.kt
            |    |    components/
            |    |    |    Example.kt
            |    |    navigation/
            |    |    |    Navgraph.kt
            |    |    |    NavRouter.kt
            |    |    screens/
            |    |    |    example/
            |    |    |     |   ExampleScreen.kt
            |    |    |     |   ExampleViewModel.kt
            |    |    theme/
            |    |    |    Color.kt
            |    |    |    Shapes.kt
            |    |    |    Sizes.kt
            |    |    |    Theme.kt
            |    |    |    Type.kt
            |    util/
            |    |    Constants.kt
        """.trimIndent(),
    )
}

@Preview
@Composable
fun ArchitectureScreenPreview() {
    ArchitectureScreen(navController = rememberNavController())
}