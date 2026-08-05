package com.androiddevelopersguide.ui.screens.bestpractices

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.components.Section

@Composable
fun BestPracticesScreen(
    navController: NavController,
    viewModel: BestPracticesViewModel = hiltViewModel(),
) {
    PageScaffold(
        navController = navController,
        title = "Best practices",
        mobileUI = {
            Introduction()
            SOLID()
            DryKissYagni()
            SingleSourceOfTruth()
            Immutability()
            StateInViewModels()
            SeparationOfConcerns()
            DefensiveProgramming()
            UiBestPractices()
        }
    )
}

@Composable
fun Introduction() {
    Section(
        title = "What & why",
        description = """
            Best practices are not a checklist to satisfy for their own sake — each one buys something specific: a class that is easier to test, a bug that becomes impossible to write, a feature that is cheaper to add. This screen restates the handful that matter most on Android and points at where this app applies them, so the principle has a concrete address instead of staying abstract.
        """.trimIndent(),
    )
}

@Composable
fun SOLID() {
    Section(
        title = "SOLID, mapped to this app",
        description = """
            -Single Responsibility: each class has one job. GetPostsUseCase fetches posts; PostRepositoryImpl coordinates remote and cache; ExceptionMapper decides user-facing messages. None of them reach into another's concern.
            -Open/Closed: add a new Failure subtype or a new ApiScenario without editing the existing branches' behaviour — they are sealed/when-checked, but extension still happens by adding a case rather than rewriting logic.
            -Liskov Substitution: PostRepositoryImpl honours the PostRepository contract exactly, so any code depending on the interface works against the impl or a fake interchangeably. The dev flavor's FailingPostDao proves this — it is substitutable for PostDao.
            -Interface Segregation: repository interfaces are small and role-specific (PostRepository, ThemeRepository), not one fat data interface.
            -Dependency Inversion: the UI depends on GetPostsUseCase which depends on the PostRepository interface, not on PostRepositoryImpl. Hilt injects the concrete impl at construction time.
        """.trimIndent(),
    )
}

@Composable
fun DryKissYagni() {
    Section(
        title = "DRY, KISS, YAGNI",
        description = """
            -DRY (Don't Repeat Yourself): PostMapper centralises every DTO/entity/domain conversion so the rule lives in one file. ExceptionMapper centralises every error message and severity. Section/LinkCard centralise UI card treatment so the home grid and link pages do not re-implement it.
            -KISS (Keep It Simple): use cases here are thin — they delegate to a repository — rather than inventing an abstraction layer that adds nothing. Resist elaborate hierarchies until a second use case actually needs them.
            -YAGNI (You Aren't Gonna Need It): Room ships one entity and version 1, not a speculative five-table schema. Add the table when a feature needs it.
        """.trimIndent(),
    )
}

@Composable
fun SingleSourceOfTruth() {
    Section(
        title = "Single source of truth",
        description = """
            The UI should read one authoritative version of a piece of data, not reconcile two. In this app the cache is that authority for posts: a successful network fetch writes through to the cache, and the cache is what flows back to the UI. The UI never holds "the remote list" and "the local list" and tries to merge them — it holds whatever the repository returned, and the repository is the only thing that knows how the two sources relate.
        """.trimIndent(),
        code = """
            // PostRepositoryImpl.getPosts — remote writes into the cache, cache is returned
            val remotePosts = guardedApiCall { api.getPosts() }
            guardedDatabaseCall { dao.insertPosts(remotePosts.map { it.toDomain().toEntity() }) }
            // ... cache is the single value the UI sees
        """.trimIndent()
    )
}

@Composable
fun Immutability() {
    Section(
        title = "Immutability & data classes",
        description = """
            Domain models are data classes with only val properties (Post, AppTheme, LinkData). Once constructed they cannot be mutated, which removes a whole class of "who changed this?" bugs and makes them safe to expose from a ViewModel's StateFlow. The mutable state lives exactly where it should — in private MutableStateFlow fields inside the VM — and only the read-only StateFlow is exposed to the UI.
        """.trimIndent(),
        code = """
            // domain model — fully immutable
            data class Post(val userId: Int, val id: Int, val title: String, val body: String)

            // ViewModel exposes read-only state; mutation is internal only
            private val _posts = MutableStateFlow<List<Post>>(emptyList())
            val posts: StateFlow<List<Post>> = _posts.asStateFlow()
        """.trimIndent()
    )
}

@Composable
fun StateInViewModels() {
    Section(
        title = "State lives in ViewModels",
        description = """
            UI state survives configuration changes (rotation, dark-mode toggle, process death within the saved-state window) only if it lives in a ViewModel, not in a composable. Composables render state; they do not own it. The VM exposes StateFlows; the composable collects them and turns them into UI. This separation is also what makes a VM unit-testable — you assert on its state flows with turbine instead of driving a Compose tree.
        """.trimIndent(),
        code = """
            @HiltViewModel
            class PostsViewModel @Inject constructor(
                private val getPostsUseCase: GetPostsUseCase
            ) : ViewModel() {
                private val _uiState = MutableStateFlow(PostsUiState(isLoading = true))
                val uiState: StateFlow<PostsUiState> = _uiState.asStateFlow()

                fun load() = viewModelScope.launch { /* update _uiState */ }
            }
        """.trimIndent()
    )
}

@Composable
fun SeparationOfConcerns() {
    Section(
        title = "Separation of concerns & the dependency rule",
        description = """
            Each layer owns a concern and no more: UI renders and captures input; ViewModels turn use-case results into UI state; use cases express intent; repositories decide remote-vs-local; core provides Result/Failure/guards. Source dependencies point inward only (UI → domain ← data), which is what makes any one layer replaceable — the dev flavor swaps the data layer's fakes in and out without the UI or domain changing.
        """.trimIndent(),
    )
}

@Composable
fun DefensiveProgramming() {
    Section(
        title = "Defensive programming: failures are values",
        description = """
            Inputs are hostile — the network will time out, the server will return 500, the cache will be empty. Rather than letting exceptions propagate, this app converts them into typed Failures at the data boundary (guardedApiCall, safeRepositoryCall, ExceptionMapper) and returns them as Result.Failed. The UI then renders a failure as a first-class state with a severity-driven tone, not as a crash. Treat every external interaction as something that can fail, and make "it failed" a value your UI knows how to draw.
        """.trimIndent(),
        code = """
            // every repository method returns Result, so the UI handles failure explicitly
            when (val result = getPostsUseCase()) {
                is Result.Success  -> showPosts(result.value, result.warning)
                is Result.Failed   -> showError(result.failure)
            }
        """.trimIndent()
    )
}

@Composable
fun UiBestPractices() {
    Section(
        title = "UI best practices",
        description = """
            -Design system, not literals: every colour, shape, and size comes from MaterialTheme.colorScheme / shapes / typography or the Sizes object. No hardcoded hex, no magic dp scattered through composables — that is what lets the light/dark themes and the Section/LinkCard components stay consistent.
            -Reuse over duplicate: Section and LinkCard are shared components; if a card treatment is needed in two screens it lives in ui/components, not copied.
            -Composables render, they do not fetch: data loading is kicked off from the ViewModel, never from a composable's body.
            -Previews for fast iteration: each screen ships a @Preview composable so the design can be checked without running the app.
        """.trimIndent(),
    )
}

@Preview
@Composable
fun BestPracticesScreenPreview() {
    BestPracticesScreen(navController = rememberNavController())
}
