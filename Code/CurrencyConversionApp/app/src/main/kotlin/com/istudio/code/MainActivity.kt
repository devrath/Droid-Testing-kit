package com.istudio.code

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.istudio.code.states.AppScreenResponseEvent
import com.istudio.code.states.AppScreenUiState
import com.istudio.code.states.AppScreenViewEvent
import com.istudio.common.navigation.Screen
import com.istudio.common.navigation.Screen.Companion.currencyToRateKey_key
import com.istudio.common.navigation.Screen.Companion.currencyToRateValue_key
import com.istudio.common.navigation.Screen.Companion.userFromEnteredCurrencyKey_key
import com.istudio.common.navigation.Screen.Companion.userFromEnteredCurrencyName_key
import com.istudio.common.navigation.Screen.Companion.userFromEnteredCurrencyType_key
import com.istudio.common.navigation.Screen.Companion.userFromEnteredCurrency_key
import com.istudio.core_ui.composables.ErrorAlert
import com.istudio.core_ui.composables.ExitAlert
import com.istudio.core_ui.composables.FloatingActionButton
import com.istudio.core_ui.composables.NoConnectivity
import com.istudio.core_ui.composables.ShimmerHomeLoadingComposable
import com.istudio.core_ui.composables.ThemeSwitcher
import com.istudio.core_ui.theme.MaterialAppTheme
import com.istudio.core_ui.theme.fontFamily
import com.istudio.currency_converter.presentation.CurrencyScreen
import com.istudio.currency_result.presentation.CurrencyResultScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    /** ******************************** Life-cycle Methods  ***************************************/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent { initOnCreate() }
    }
    /** ******************************** Life-cycle Methods  ***************************************/

    /** ************************************* Init Methods  ***************************************/
    /** <*******> Init OnCreate <******> **/
    @Composable
    private fun initOnCreate() {
        val viewModel: MainVm = hiltViewModel()
        // --> This is used to handle the device back button
        BackButtonHandler()
        // --> This is used to handle the exit alert dialog
        ExitAlertHandler()
        // --> Error alert handler
        ErrorAlertHandler()
        // --> This is used to update the Orientation
        handleConfigurationEffect()
        // --> Launch only once per session
        LaunchOncePerSession()
        // Material theme content
        MaterialAppTheme(darkTheme = viewModel.currentTheme.value) { ThemeContent() }
    }
    /** ************************************* Init Methods  ***************************************/

    /** ********************************* Screen utility composables  *****************************/
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun ThemeContent() {
        // View model reference
        val viewModel: MainVm = hiltViewModel()
        // View state reference from view model
        val state = viewModel.viewState

        // <!--------------------- CONTROLLERS ------------------------>
        // SnackBar controller
        val snackBarController = remember { SnackbarHostState() }
        // coroutine scope to handle co-routines
        val coroutineScope = rememberCoroutineScope()
        // Scroll behaviour
        val scrollBehaviour = TopAppBarDefaults.pinnedScrollBehavior()
        // Nav controller
        val controller = rememberNavController()
        // Focus Manager
        val focusManager = LocalFocusManager.current
        // Keyboard Manager
        val keyboardController = LocalSoftwareKeyboardController.current
        // <!--------------------- CONTROLLERS ------------------------>


        ShimmerHomeLoadingComposable(
            isLoading = state.loadingState,
            contentAfterLoading = {
                // Main content of the screen
                MainContent(
                    state = state,
                    snackBarController = snackBarController,
                    scrollBehaviour = scrollBehaviour,
                    controller = controller,
                    orientation = state.orientation,
                    focusManager = focusManager,
                    displaySnackBar = { message ->
                        coroutineScope.launch {
                            //snackBarController.showSnackbar(message = message)
                            // Show error dialog
                            viewModel.onEvent(AppScreenViewEvent.SetMessageForError(message=message))
                            viewModel.onEvent(
                                AppScreenViewEvent.HandleErrorAlertDisplay(mutableStateOf(value = true))
                            )
                        }
                    },
                    keyBoardDoneAction = {
                        keyboardController?.hide()
                    }
                )
            },
            modifier = Modifier.fillMaxSize()
        )
    }

    @Composable
    private fun LaunchOncePerSession() {

        // View model reference
        val viewModel: MainVm = hiltViewModel()
        // View state reference from view model
        val state = viewModel.viewState
        // <!--------------------- CONTROLLERS ------------------------>
        // SnackBar controller
        val snackBarController = remember { SnackbarHostState() }
        // coroutine scope to handle co-routines
        val coroutineScope = rememberCoroutineScope()

        LaunchedEffect(key1 = state.launchedEffectState) {

            // <-------------> once when the effect is launched  <------------->
            // Notify the loading state to be displayed in the screen
            viewModel.onEvent(AppScreenViewEvent.LoadingState)
            // Initially action button is invisible since loader is currently displayed
            viewModel.onEvent(AppScreenViewEvent.IsActionButtonVisible(isVisible = false))
            // Either get the data from the server / or / ge the data from the local database
            viewModel.onEvent(AppScreenViewEvent.ToggleDataSource)
            // <-------------> once when the effect is launched  <------------->

            // <***********> Event is observed from View-Model <************>
            viewModel.uiEvent.collect { event ->
                when (event) {
                    // ---> Display messages in snack-bar
                    is AppScreenResponseEvent.ShowSnackBar -> {
                        coroutineScope.launch {
                            snackBarController.showSnackbar(message = event.message)
                        }
                    }

                    // ---> Get the data from server/database
                    is AppScreenResponseEvent.ToggleData -> {
                        if (event.isFetchFromServer) {
                            // --> Get the data from server
                            viewModel.onEvent(AppScreenViewEvent.CheckConnectivity)
                        } else {
                            // --> Get the data from database
                            viewModel.onEvent(AppScreenViewEvent.LoadFromDatabase)
                        }
                    }
                }
            }
            // <***********> Event is observed from View-Model <************>
        }
    }

    @Composable
    private fun BackButtonHandler() {

        // View model reference
        val viewModel: MainVm = hiltViewModel()
        // View state reference from view model
        val state = viewModel.viewState

        BackHandler {
            // ---> Handle Alert dialog for closing application
            viewModel.onEvent(
                AppScreenViewEvent.HandleExitAlertDisplay(mutableStateOf(value = true))
            )
        }
    }

    @Composable
    private fun ExitAlertHandler() {

        // View model reference
        val viewModel: MainVm = hiltViewModel()
        // View state reference from view model
        val state = viewModel.viewState

        ExitAlert(
            currentExitAlertVisibility = state.isExitAlertDisplayed,
            closeApplication = { closeApplication ->
                // Close the dialog
                viewModel.onEvent(
                    AppScreenViewEvent.HandleExitAlertDisplay(mutableStateOf(value = false))
                )
                if (closeApplication) {
                    // Close the application
                    Intent().apply {
                        action = Intent.ACTION_MAIN
                        addCategory(Intent.CATEGORY_HOME)
                    }.run { startActivity(this) }
                }
            }
        )
    }

    @Composable
    private fun ErrorAlertHandler() {
        // View model reference
        val viewModel: MainVm = hiltViewModel()
        // View state reference from view model
        val state = viewModel.viewState

        ErrorAlert(
            currentExitAlertVisibility = state.isErrorAlertDisplayed,
            message = state.errorMessage,
            closeApplication = {
                // Close the dialog
                viewModel.onEvent(
                    AppScreenViewEvent.HandleErrorAlertDisplay(mutableStateOf(value = false))
                )
            }
        )
    }

    @Composable
    private fun handleConfigurationEffect() {

        // Configuration Manager
        val configuration = LocalConfiguration.current
        // View model reference
        val viewModel: MainVm = hiltViewModel()

        LaunchedEffect(configuration) {
            // Save any changes to the orientation value on the configuration object
            snapshotFlow {
                configuration.orientation
            }.collect {
                // Update configuration to mutable view state so that composable can display appropriate screen modes
                viewModel.onEvent(AppScreenViewEvent.SetScreenOrientation(it))
            }
        }
    }

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    private fun MainContent(
        state: AppScreenUiState,
        snackBarController: SnackbarHostState,
        scrollBehaviour: TopAppBarScrollBehavior,
        controller: NavHostController,
        orientation: Int,
        focusManager: FocusManager,
        displaySnackBar: (String) -> Unit,
        // -----> KeyBoard Action
        keyBoardDoneAction: () -> Unit
    ) {
        // View model reference
        val viewModel: MainVm = hiltViewModel()
        var onClickOfCalculatePlay by remember { mutableStateOf({}) }

        if (state.isConnectedToInternet) {
            // Connected to internet
            Scaffold(
                snackbarHost = { SnackbarHost(snackBarController) },
                modifier = Modifier
                    .fillMaxSize()
                    .nestedScroll(scrollBehaviour.nestedScrollConnection),
                topBar = {
                    if (viewModel.viewState.isToolbarVisible) {
                        TopAppBar(
                            title = {
                                Text(
                                    text = state.toolBarTitle,
                                    fontFamily = fontFamily,
                                )
                            },
                            scrollBehavior = scrollBehaviour,
                            actions = {
                                ThemeSwitcher(
                                    darkTheme = viewModel.currentTheme.value,
                                    size = 50.dp,
                                    padding = 5.dp,
                                    onClick = {
                                        // Update theme on-click
                                        viewModel.currentTheme.value = !viewModel.currentTheme.value
                                    }
                                )
                            }
                        )
                    }
                },
                floatingActionButton = {
                    if (state.isActionButtonVisible) {
                        FloatingActionButton {
                            onClickOfCalculatePlay()
                        }
                    }
                }
            ) {
                Box(
                    modifier = Modifier.padding(it)
                ) {
                    NavHost(
                        navController = controller,
                        startDestination = Screen.CurrencyConverter.route
                    ) {

                        // -------> COMPOSABLE:-> Currency Calculation
                        composable(
                            route = Screen.CurrencyConverter.route,
                            enterTransition = {
                                fadeIn(
                                    animationSpec = tween(
                                        300, easing = LinearEasing
                                    )
                                ) + slideIntoContainer(
                                    animationSpec = tween(300, easing = EaseIn),
                                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                                )
                            },
                            exitTransition = {
                                fadeOut(
                                    animationSpec = tween(
                                        300, easing = LinearEasing
                                    )
                                ) + slideOutOfContainer(
                                    animationSpec = tween(300, easing = EaseOut),
                                    towards = AnimatedContentTransitionScope.SlideDirection.End
                                )
                            }
                        ) {

                            // <------------------------- Screen Prerequisites -------------------->
                            // Set the Action button visible
                            viewModel.onEvent(AppScreenViewEvent.IsActionButtonVisible(isVisible = true))
                            // Change the screen title
                            viewModel.onEvent(
                                AppScreenViewEvent.SetToolBarTitle(
                                    title = LocalContext.current.getString(R.string.str_currency_converter)
                                )
                            )
                            // <------------------------- Screen Prerequisites -------------------->

                            CurrencyScreen(
                                orientation = orientation,
                                onErrorAction = { message ->
                                    // Scenario : When error occurs

                                },
                                onKeyBoardOutsideClick = {
                                    // Scenario : When user clicks outside the keyboard
                                    focusManager.clearFocus()
                                },
                                onBackPress = {
                                    // Scenario : When user presses back button: Using Ui widget Action if present
                                    controller.popBackStack()
                                },
                                onLoading = { isVisible ->
                                    // Toggle toolbar visibility
                                    viewModel.onEvent(AppScreenViewEvent.ToolbarVisibility(isVisible = isVisible))
                                },
                                onClickOfCalculatePlay = {
                                    onClickOfCalculatePlay = it
                                },
                                displaySnackBar = { message ->
                                    displaySnackBar.invoke(message)
                                },
                                navigateToResultScreen = { resultInput ->
                                    // Navigate to result screen composable
                                    controller.navigate(
                                        viewModel.prepareScreenResultRoute(resultInput)
                                    )
                                },
                                keyBoardDoneAction = { keyBoardDoneAction }
                            )
                        }

                        // -------> COMPOSABLE:-> Currency Result
                        composable(
                            route = Screen.CurrencyResult.route,
                            enterTransition = {
                                fadeIn(
                                    animationSpec = tween(
                                        300, easing = LinearEasing
                                    )
                                ) + slideIntoContainer(
                                    animationSpec = tween(300, easing = EaseIn),
                                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                                )
                            },
                            exitTransition = {
                                fadeOut(
                                    animationSpec = tween(
                                        300, easing = LinearEasing
                                    )
                                ) + slideOutOfContainer(
                                    animationSpec = tween(300, easing = EaseOut),
                                    towards = AnimatedContentTransitionScope.SlideDirection.End
                                )
                            },
                            arguments = listOf(
                                navArgument(userFromEnteredCurrency_key) {
                                    type = NavType.StringType
                                },
                                navArgument(userFromEnteredCurrencyType_key) {
                                    type = NavType.StringType
                                },
                                navArgument(userFromEnteredCurrencyKey_key) {
                                    type = NavType.StringType
                                },
                                navArgument(userFromEnteredCurrencyName_key) {
                                    type = NavType.StringType
                                },
                                navArgument(currencyToRateKey_key) { type = NavType.StringType },
                                navArgument(currencyToRateValue_key) { type = NavType.StringType }
                            )
                        ) { navBackStackEntry ->

                            // <------------------------- Screen Prerequisites -------------------->
                            // Set the Action button invisible
                            viewModel.onEvent(AppScreenViewEvent.IsActionButtonVisible(isVisible = false))
                            val cxt = LocalContext.current
                            // Set the screen title
                            viewModel.onEvent(AppScreenViewEvent.SetToolBarTitle(
                                title = cxt.getString(R.string.str_currency_result))
                            )
                            // <------------------------- Screen Prerequisites -------------------->

                            navBackStackEntry.arguments?.let { bundle ->
                                val inputFromBundle =
                                    viewModel.getDataFromBundleForResultScreen(bundle)
                                Log.d("Args", inputFromBundle.toString())
                                CurrencyResultScreen(
                                    orientation = orientation, input = inputFromBundle
                                )
                            }
                        }
                    }
                }
            }
        } else {
            // Not connected to internet
            NoConnectivity {
                // Retry connectivity
                viewModel.onEvent(AppScreenViewEvent.CheckConnectivity)
            }
        }
    }
    /** ********************************* Screen utility composables  *****************************/
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MaterialAppTheme {
        Greeting("Android")
    }
}