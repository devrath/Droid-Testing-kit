package com.istudio.code

import android.content.res.Configuration
import android.os.Bundle
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.istudio.core.navigation.Route
import com.istudio.core_ui.composables.ThemeSwitcher
import com.istudio.core_ui.theme.MaterialAppTheme
import com.istudio.currency_converter.presentation.CurrencyScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // View model reference
            val viewModel: MainVm = hiltViewModel()
            // <!--------------------- CONTROLLERS ------------------------>
            // SnackBar controller
            val snackBarController = remember { SnackbarHostState() }
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

            MaterialAppTheme(darkTheme = viewModel.currentTheme.value) {
                // A surface container using the 'background' color from the theme

                Scaffold(
                    snackbarHost = { SnackbarHost(snackBarController) },
                    modifier = Modifier
                        .fillMaxSize()
                        .nestedScroll(scrollBehaviour.nestedScrollConnection),
                    topBar = {

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
                ) {
                    Box(
                        modifier = Modifier.padding(it)
                    ) {
                        NavHost(
                            navController = controller,
                            startDestination = Route.CURRENCY_CONVERSION_SCREEN
                        ) {

                            composable(route = Route.CURRENCY_CONVERSION_SCREEN) {
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
                                        // Scenario : When user presses back button

                                    }
                                )
                            }
                        }
                    }
                }
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