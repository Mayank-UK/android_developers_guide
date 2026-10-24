# AI defined section:

## Current state
- Clean-architecture Compose teaching app: domain (pure) / data (repositories, Room, Retrofit) / core (result, failures, guards, mapping) /
  ui (screens + components), Hilt DI, dev/staging/prod flavors on the "environment" dimension (libs.versions.toml catalog, KSP not kapt).
- Teaching pattern: content lives as composable functions in each *Screen.kt using Section(title, description, code?) blocks; hub screens that
  only index subscreens (JetpackCompose, Lifecycle, OtherBasics) deliberately stay Links index pages; empty Hilt *ViewModel stubs left as-is.
  Every concrete claim about app code was verified against the referenced source; no fabricated APIs; `assembleDevDebug` build successful.
- Theme (persisted, clean layers): AppTheme enum (Light/Dark/System, DataStore key `rdg-theme`) in domain/models; ThemeRepository/Impl
  (DataStore Preferences, file `app_preferences`); Observe/SetThemeUseCase; app-scoped ThemeViewModel (ui/screens/theme/). MainActivity
  hoists ThemeViewModel + DrawerState; resolveDarkTheme() → AndroidDevelopersGuideTheme(darkTheme=...). Root Surface = MaterialTheme.background.
- Chrome: drawer-open / dark-state / theme-toggle published via AppChrome CompositionLocals (ui/components/LocalAppChrome.kt) — PageScaffold
  and ThemeToggleButton read locals, no prop-drilling. PageScaffold = quiet app bar (surface container, outlineVariant hairline divider,
  back-arrow-or-hamburger, caller appBarActions, ThemeToggleButton rightmost). Drawer = Material3 ModalNavigationDrawer in MainActivity
  (hosts the About entry; route NavRouter.AboutScreen "about" → ui/screens/about/AboutScreen.kt).
- Home: HomeViewModel exposes List<LinkCategory> (Fundamentals / UI / Architecture & data / Platform & tooling) rendering descriptive
  LinkCards responsively (1/2/3 columns via rememberWindowSize); XML-layout entry via HomeViewModel.XML_LAYOUT_ROUTE sentinel →
  XmlLayoutActivity (startActivity); EnvironmentBadge preserved. Grid is a NON-lazy Column on purpose — PageScaffold wraps content in a
  verticalScroll Column (a nested lazy vertical list hits infinite-height constraints); do not "fix" it to LazyVerticalGrid.
- Shared components: ui/components/LinkCard.kt (surfaceContainer card, hairline outline border, optional leading primary-tinted icon,
  onSurface title + one-line onSurfaceVariant description, trailing primary arrow) — used by home grid AND Links (DRY). LinkData schema has
  nullable description/icon/category; LinkIcon is a pure-Kotlin enum in domain/models/LinkData.kt resolved to ImageVector via
  LinkIcon.toImageVector() (ui/components/LinkIcon.kt) — domain stays UI-framework-free; existing call sites compile unchanged.
- Section styling is fully theme-driven (no hardcoded grayscale): bg surfaceContainer, border outline, demo box surfaceContainerHigh, code
  surfaceContainerHighest + onSurface text (readable both themes), description onSurfaceVariant, toggle in primary, short primary accent bar.

## Active tasks
- None open in the AI section — Chunks A–C are complete; the senior-review fix batch (Chunks D) and its deferred follow-ups are tracked in
  the Developer defined section below.

# Developer defined section

- Project requirements
    - I will add my requirements to this file marked as "⏳" and you will keep finishing them and marking them "✅" on completion

- Generic instructions:
    - You have to build UI using the design system implemented in the application
    - You have to follow the proper architecture that is followed in the application, for example, having use cases, repositories, repositoriesImpl, remote, DTOs, local, DAOs, exception handling, and so on
    - You have to check if existing components can be reused instead of creating new ones. If a component is to be used in a single file, then better to make it a function inside that view. If a component is to be used in multiple files, then create it inside the global ui/components
    - If feeling confused about how to do things, you are free to check how it's done in other parts of this app; the goal is to maintain consistency throughout the app
    - You have to follow few principles to write quality code, but these are not to be forced as some technologies have their standards, for example react uses hooks and other technologies use their own standards:
        - Core programming principles:
            - DRY, KISS, YAGNI, SOLID, Separation of concerns, Single source of truth
        - Object oriented design if required
            - Encapsulation, Abstraction, Inheritance, Polymorphism, Composition over inheritance, Command query separation, Law of Demeter, Dependency Injection, Inversion of control, High cohesion, Low coupling
        - Software design
            - Modularity, Maintainability, Readibility, Extensibility, Defensive programming (input is hostile), Immutability, Pure functions, Referential Transparency, Composition, Lazy evaluation
        - Code quality
            - Self documenting (descriptive naming), Consistent formatting, Error handling
        - Concurrency
            - Thread safety, Synchronization, Immutability
        - Security
            - Least priviledge, Input validation, Secret management
        - Testing
            - Unit tests, Test behaviour not implementation
        - Refactoring
            - Incremental refactoring, remove duplication
        - Engineering principles
            - Unix philosophy (composable tools beat monoliths)
        - Performance
            - Right algorithms, Right datastructures, Caching, Batching
    - You have to follow android best practices:
        -
    - Keep "agentic_development.md" for this project as lean as possible without compromising it's primary purpose to high quality consistent development, optimize token efficiency and seemless development across sessions. Remove the information inside the "AI defined section" that is no longer relevant and will not be usefull in future to save tokens

- Development tasks:
    - Update design similar to React developers guide (Home + shared components done) ✅ (content depth pending)
    - react_developers_guide is well descriptive, but the android one is not, we need to describe little more ✅
    - Add a drawer with about page and add toggle for light/dark mode in appbar on right side like react_developers_guide ✅
    - Review code and refactor to take implementation to 10/10 ✅
        - Review from the perspective of a developer who has 10 years of experience in android and 30 years of experience in Software development, who knows all the best practices and bad practices, whose code is simple to understand yet robust
        - Review how architecture is implemented
        - Review how design system is implemented
        - Review how hook files and logic inside them are implemented, if this is even standard or just spaghetti code
        - Review all other aspects

## Senior code-review fix batch (Chunks D)
Applied a prioritized review findings list, group-by-group, each followed by a green `./gradlew assembleDevDebug` + `testDevDebugUnitTest`.
- G1 Security/manifest: set `android:exported="false"` on `PlaySoundService`, `UserProvider`; removed the static `NetworkConnectivityReceiver` `<receiver>` entry (dup of the dynamic registration in `MainActivity.onCreate`, and `CONNECTIVITY_ACTION` is a no-op for N+ targets); wrapped `unregisterReceiver` in `try/catch(IllegalArgumentException)`. Receiver class still compiles.
- G2 Build config: dropped the redundant `composeOptions { kotlinCompilerExtensionVersion }` (Kotlin 2.x Compose compiler plugin handles it); removed the unused `kotlinKapt` plugin alias from app + root `build.gradle.kts` and its `libs.versions.toml` entry (verified no `kapt(...)` deps remain — all annotation processing is KSP); bumped `minSdk` 24→26 and removed the now-redundant `@RequiresApi(Build.VERSION_CODES.O)` from `MainActivity` and `Navgraph` (verified no API-26-only calls were guarded).
- G3 Service robustness: `PlaySoundService.mediaPlayer` is now nullable, releases the previous instance before creating a new one, null-checks `MediaPlayer.create(...)`, and releases in `onDestroy` then nulls the reference (fixes the leak + the `UninitializedPropertyAccessException`/lateinit crash).
- G4 Compose UI: removed the bogus `.height(screenHeightDp.dp)` from the Posts `LazyColumn`; tokenized hardcoded colors in `PostsScreen`/`PostDetailScreen` to Material3 (`surfaceContainer`/`onSurface`/`error`); added `key = { it.id }` to the posts list; dropped `!!` in favor of local-val smart-casts. Added a `scrollable: Boolean = true` param to `PageScaffold` (default keeps all other screens unchanged) and PostsScreen passes `scrollable = false` so the inner LazyColumn isn't nested in a scrolling parent. `Section` code-block `letterSpacing` 2.sp → 0.5.sp.
- G5 State & errors: consolidated PostsViewModel's three flows into one `PostsUiState` + single `StateFlow`; converted PostDetailViewModel's bare `String?` error to a typed `Failure?` via `PostDetailUiState`; PostDetailScreen now renders via `ErrorView`/`ErrorBanner`/`ErrorSnackbar` (severity/retry consistent with PostsScreen). `GuardedApiCall` (both `guardedApiCall` and `guardedDatabaseCall`) rethrows `CancellationException` before the generic `catch (e: Exception)` so coroutine cancellation semantics stay intact. Updated both VM unit tests to the new state shape.
- G6 DI: `RepositoryModule` converted from `object` + `@Provides` to `abstract class` + `@Binds` for `PostRepository`/`ThemeRepository` (both `@Singleton`-scoped at the binding); removed the redundant `@Singleton` annotation on `ThemeRepositoryImpl` (binding is now the single source of truth for scope). `ExceptionMapper.mapDatabaseMessage` now branches on the new `DatabaseException.Kind` discriminator instead of substring-matching prose; `DatabaseException` carries the discriminator and the user-facing messages are unchanged.
- G7 Data: added `.fallbackToDestructiveMigration()` to the Room builder (reference app; local cache is reproducible from the remote source). Removed the unused `PostDao.getPostsByUserId` (and the matching override in the dev `FailingPostDao`); verified the only other mention is a code-sample string in `DatabaseHandlingScreen`.
- G8 Nav safety: removed `objectName`/`objectPath` from the `NavRouter` base class; `PostDetailScreen` now declares its own `ARG_POST_ID` + `ROUTE_WITH_ARG` (PostsScreen's `${route}/${id}` navigation still matches). PostDetail composable uses `requireNotNull` for the arg so a programming error surfaces instead of a blank screen. Notification entry is gated at the OtherBasics index (`OtherBasicsViewModel`) by `Build.VERSION.SDK_INT >= TIRAMISU` so users on <33 never navigate to a blank destination. Removed dead Compose Temp scaffolding (`NavRouter.TempScreen`, its Navgraph entry, `ui/screens/temp/` files, the "Temp" link in `UiElementsCatalogViewModel`, and unused imports in `LayoutScreen`/`ScreenStructureScreen`). NOTE: `TempActivity` + `activity_temp.xml` + its manifest entry were intentionally KEPT — it is functionally used by `IntentScreen` as the explicit-intent demo target, so removing it would break that screen (per the spec's "stop and report if referenced" safety clause).
- G9 CounterWorker: fixed the real "counter never advances" bug. `PeriodicWorkRequest` does not chain output→input across runs, so the counter is now persisted in a dedicated DataStore (`counter_preferences`) and `CounterWorker.doWork()` (now a `CoroutineWorker`) does read→increment→write atomically and returns the new value as output. New `@Singleton CounterRepository @Inject` exposes the persisted `Flow<Int>`; `WorkManagerViewModel` observes that flow as the single source of truth (the LiveData→asFlow work-info observer was removed — no longer needed; `getWorkInfosForUniqueWorkFlow` is available in WM 2.9.1 but is no longer required since the DataStore flow is authoritative). Replaced the apologetic "it doesn't seem to work" text on the WorkManager screen with an accurate description.

### Deferred follow-ups (need on-device QA or larger effort)
- `targetSdk` 35 + `enableEdgeToEdge()` (needs inset handling + device QA). Stayed at 34.
- Release `isMinifyEnabled`/R8 + Retrofit/Gson/Room ProGuard keep rules (needs rule testing on a release build).
- Compose BOM bump (needs QA).
- Full `navController` → callback-based navigation refactor (touches ~60 screens; needs device QA). Only the low-risk NavRouter field removal + `requireNotNull` + Notification gating were done here.
- Migrate `NetworkConnectivityReceiver` to `ConnectivityManager.registerDefaultNetworkCallback` (architectural change). Only the manifest dedup/removal was done here.

