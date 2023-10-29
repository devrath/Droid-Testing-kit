package com.istudio.code

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
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
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
            // Theme switcher
            var darkTheme by remember { mutableStateOf(false) }
            // Title
            val titleStr = "Currency Converter"
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
            // <!--------------------- CONTROLLERS ------------------------>

            MaterialAppTheme(darkTheme = darkTheme) {
                // A surface container using the 'background' color from the theme
                RootComposable(
                    snackBarController = snackBarController,
                    scrollBehaviour = scrollBehaviour,
                    titleStr = titleStr,
                    darkTheme = darkTheme,
                    controller = controller,
                    focusManager = focusManager
                )
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun RootComposable(
    snackBarController: SnackbarHostState,
    scrollBehaviour: TopAppBarScrollBehavior,
    titleStr: String,
    darkTheme: Boolean,
    controller: NavHostController,
    focusManager: FocusManager
) {
    var darkTheme1 = darkTheme
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
                        darkTheme = darkTheme1,
                        size = 50.dp,
                        padding = 5.dp,
                        onClick = {
                            // Update theme on-click
                            darkTheme1 = !darkTheme1
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

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    var darkTheme by remember { mutableStateOf(false) }
    // Title
    val titleStr = "Currency Converter"
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
    // <!--------------------- CONTROLLERS ------------------------>
    MaterialAppTheme {
        RootComposable(
            snackBarController = snackBarController,
            scrollBehaviour = scrollBehaviour,
            titleStr = titleStr,
            darkTheme = darkTheme,
            controller = controller,
            focusManager = focusManager
        )
    }
}