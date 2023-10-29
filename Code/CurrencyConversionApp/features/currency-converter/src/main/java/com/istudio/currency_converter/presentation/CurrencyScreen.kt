package com.istudio.currency_converter.presentation

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.istudio.core_ui.composables.DropDownField
import com.istudio.core_ui.composables.GridInput
import com.istudio.core_ui.composables.InputTextField
import com.istudio.core_ui.composables.ThemeSwitcher
import com.istudio.core_ui.theme.LocalSpacing
import com.istudio.currency_converter.R
import com.istudio.currency_converter.presentation.states.CurrencyScreenResponseEvent

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun CurrencyScreen(
    modifier: Modifier = Modifier,
    darkTheme: Boolean,
    onThemeUpdated: () -> Unit,
    onBackPress: () -> Unit
) {

    // <!------------ MAIN-COMPOSE-CONTROL-PARTS ----------------->
    // Context
    val cxt = LocalContext.current
    // View model reference
    val viewModel: CurrencyScreenVm = hiltViewModel()
    // View state reference from view model
    val state = viewModel.viewState
    // <!----------- MAIN-COMPOSE-CONTROL-PARTS ------------------->

    // <!--------------------- CONTROLLERS ------------------------>
    // Keyboard controller
    val keyboardController = LocalSoftwareKeyboardController.current
    // Focus Manager
    val focusManager = LocalFocusManager.current
    // SnackBar controller
    val snackBarController = remember { SnackbarHostState() }
    // Coroutine to handle the animation
    val coroutineScopeController = rememberCoroutineScope()
    // <!--------------------- CONTROLLERS ------------------------>

    // Scroll behaviour
    val scrollBehaviour = TopAppBarDefaults.pinnedScrollBehavior()


    //val titleStr = cxt.getString(R.string.str_currency_converter)
    val titleStr = "Currency Converter"


    LaunchedEffect(key1 = state.launchedEffectState) {

        // <***********> Event is observed from View-Model <************>
        viewModel.uiEvent.collect { event ->
            when (event) {
                is CurrencyScreenResponseEvent.CurrencyUserInputError -> {

                }

                is CurrencyScreenResponseEvent.ShowSnackBar -> {

                }
            }
        }
        // <***********> Event is observed from View-Model <************>

    }


    Scaffold(
        snackbarHost = { SnackbarHost(snackBarController) },
        modifier = Modifier.fillMaxSize()
            .nestedScroll(scrollBehaviour.nestedScrollConnection),
        topBar = {

            TopAppBar(
                title = { Text(text = titleStr) },
                scrollBehavior = scrollBehaviour,
                actions = {
                    ThemeSwitcher(
                        darkTheme = darkTheme,
                        size = 50.dp,
                        padding = 5.dp,
                        onClick = onThemeUpdated
                    )
                }
            )
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(LocalSpacing.current.spaceExtraSmall)
                .pointerInput(Unit){
                    detectTapGestures(onTap = {
                        focusManager.clearFocus()
                    })
                },
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center
        ) {

            InputTextField(modifier = Modifier.fillMaxWidth())

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "Output",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .weight(1.8f)
                        .padding(
                            horizontal = LocalSpacing.current.spaceExtraSmall,
                            vertical = 20.dp
                        ),
                    textAlign = TextAlign.Start,
                    maxLines = 1
                )

                Spacer(modifier = Modifier.height(LocalSpacing.current.spaceExtraSmall))

                Box(modifier = Modifier.weight(1f)) { DropDownField() }
            }

            Spacer(modifier = Modifier.height(LocalSpacing.current.spaceExtraSmall))

            GridInput()
        }
    }


}