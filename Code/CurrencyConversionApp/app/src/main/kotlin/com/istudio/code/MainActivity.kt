package com.istudio.code

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalConfiguration
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
import com.istudio.core_ui.composables.FloatingActionButton
import com.istudio.core_ui.composables.NoConnectivity
import com.istudio.core_ui.composables.ShimmerHomeLoadingComposable
import com.istudio.core_ui.composables.ThemeSwitcher
import com.istudio.core_ui.theme.MaterialAppTheme
import com.istudio.currency_converter.presentation.CurrencyScreen
import com.istudio.currency_result.presentation.CurrencyResultScreen
import com.istudio.models.custom.CurrencyResultInput
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
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
            // Keyboard controller
            val keyboardController = LocalSoftwareKeyboardController.current
            // Focus Manager
            val focusManager = LocalFocusManager.current
            // Configuration Manager
            val configuration = LocalConfiguration.current
            // <!--------------------- CONTROLLERS ------------------------>
            // Title
            val titleStr = "Currency Converter"

            // Orientation
            var orientation by remember { mutableIntStateOf(Configuration.ORIENTATION_PORTRAIT) }

            LaunchedEffect(configuration) {
                // Save any changes to the orientation value on the configuration object
                snapshotFlow {
                    configuration.orientation
                }.collect {
                    orientation = it
                }
            }

            LaunchedEffect(key1 = state.launchedEffectState) {
                viewModel.onEvent(AppScreenViewEvent.LoadingState)
                // Check connectivity: once when the effect is launched
                viewModel.onEvent(AppScreenViewEvent.ToggleDataSource)

                // <***********> Event is observed from View-Model <************>
                viewModel.uiEvent.collect { event ->
                    when (event) {
                        is AppScreenResponseEvent.ShowSnackBar -> {
                            coroutineScope.launch {
                                snackBarController.showSnackbar(message = event.message)
                            }
                        }

                        is AppScreenResponseEvent.ToggleData -> {
                            if(event.isFetchFromServer){
                                viewModel.onEvent(AppScreenViewEvent.CheckConnectivity)
                            }else{
                                viewModel.onEvent(AppScreenViewEvent.LoadFromDatabase)
                            }
                        }
                    }
                }
                // <***********> Event is observed from View-Model <************>
            }

            MaterialAppTheme(darkTheme = viewModel.currentTheme.value) {
                // A surface container using the 'background' color from the theme
                ShimmerHomeLoadingComposable(
                    isLoading = state.loadingState,
                    contentAfterLoading = {
                        MainContent(
                            state = state,
                            snackBarController = snackBarController,
                            scrollBehaviour= scrollBehaviour,
                            titleStr = titleStr,
                            controller = controller,
                            orientation = orientation,
                            focusManager = focusManager,
                            displaySnackBar = { message ->
                                coroutineScope.launch {
                                    snackBarController.showSnackbar(message = message)
                                }
                            }
                        )
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    private fun MainContent(
        state: AppScreenUiState,
        snackBarController: SnackbarHostState,
        scrollBehaviour: TopAppBarScrollBehavior,
        titleStr: String,
        controller: NavHostController,
        orientation: Int,
        focusManager: FocusManager,
        displaySnackBar : (String) -> Unit
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
                    if(viewModel.viewState.isToolbarVisible){
                        TopAppBar(
                            title = { Text(text = titleStr) },
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
                    FloatingActionButton{
                        onClickOfCalculatePlay()
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

                        composable(route = Screen.CurrencyConverter.route) {
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
                                    val userEnteredCurrencyValue = resultInput.userFromEnteredCurrency
                                    val userFromEnteredCurrencyType = resultInput.userFromEnteredCurrencyType
                                    val userFromEnteredCurrencyKey = resultInput.userFromEnteredCurrencyKey?:""
                                    val userFromEnteredCurrencyName = resultInput.userFromEnteredCurrencyName?:""
                                    val currencyToRateKey = resultInput.currencyToRateKey
                                    val currencyToRateValue = resultInput.userFromEnteredCurrency.toString()
                                    controller.navigate(
                                        route = Screen.CurrencyResult.passParameters(
                                            userFromEnteredCurrency = userEnteredCurrencyValue,
                                            userFromEnteredCurrencyType = userFromEnteredCurrencyType,
                                            userFromEnteredCurrencyKey = userFromEnteredCurrencyKey,
                                            userFromEnteredCurrencyName = userFromEnteredCurrencyName,
                                            currencyToRateKey = currencyToRateKey,
                                            currencyToRateValue =currencyToRateValue,
                                        )
                                    )
                                }
                            )
                        }

                        composable(
                            route = Screen.CurrencyResult.route,
                            arguments = listOf(
                                navArgument(userFromEnteredCurrency_key){ type = NavType.StringType },
                                navArgument(userFromEnteredCurrencyType_key){ type = NavType.StringType },
                                navArgument(userFromEnteredCurrencyKey_key){ type = NavType.StringType },
                                navArgument(userFromEnteredCurrencyName_key){ type = NavType.StringType },
                                navArgument(currencyToRateKey_key){ type = NavType.StringType },
                                navArgument(currencyToRateValue_key){ type = NavType.StringType }
                            )

                        ) { navBackStackEntry ->

                            navBackStackEntry.arguments?.let { bundle ->

                                val userFromEnteredCurrency = bundle.getString(userFromEnteredCurrency_key).toString()
                                val userFromEnteredCurrencyType = bundle.getString(userFromEnteredCurrencyType_key).toString()
                                val userFromEnteredCurrencyKey = bundle.getString(userFromEnteredCurrencyKey_key).toString()
                                val userFromEnteredCurrencyName = bundle.getString(userFromEnteredCurrencyName_key).toString()
                                val currencyToRateKey = bundle.getString(currencyToRateKey_key).toString()
                                val currencyToRateValue = bundle.getString(currencyToRateValue_key).toString()

                                val currencyResultInput = CurrencyResultInput(
                                    userFromEnteredCurrency = userFromEnteredCurrency,
                                    userFromEnteredCurrencyType = userFromEnteredCurrencyType,
                                    userFromEnteredCurrencyKey = userFromEnteredCurrencyKey,
                                    userFromEnteredCurrencyName = userFromEnteredCurrencyName,
                                    currencyToRateKey = currencyToRateKey,
                                    currencyToRateValue = currencyToRateValue.toDouble(),
                                )
                                Log.d("Args",currencyResultInput.toString())
                                CurrencyResultScreen(
                                    orientation = orientation,
                                    input = currencyResultInput
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