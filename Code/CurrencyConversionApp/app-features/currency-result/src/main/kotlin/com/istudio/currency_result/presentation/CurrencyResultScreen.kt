package com.istudio.currency_result.presentation

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.istudio.currency_result.presentation.states.CurrencyResultScreenViewEvent
import com.istudio.models.custom.CurrencyResultInput

@Composable
fun CurrencyResultScreen(
    input: CurrencyResultInput,
    orientation: Int = Configuration.ORIENTATION_PORTRAIT,
    modifier: Modifier = Modifier,
) {
    // <!------------ MAIN-COMPOSE-CONTROL-PARTS ----------------->
    // Context
    val cxt = LocalContext.current
    // View model reference
    val viewModel: CurrencyResultScreenVm = hiltViewModel()
    // <!------------ MAIN-COMPOSE-CONTROL-PARTS ----------------->
    // View state reference from view model
    val state = viewModel.viewState
    // Composable orientation
    val configuration = LocalConfiguration.current

    LaunchedEffect(key1 = state.value.launchedEffectState) {
        // Set the data in view model
        viewModel.onEvent(
            CurrencyResultScreenViewEvent.SetResultDataInVm(input)
        )
        // <***********> Event is observed from View-Model <************>
        viewModel.uiEvent.collect { event ->

        }
        // <***********> Event is observed from View-Model <************>
    }


    viewModel.viewState.value.inputData?.let {
        CurrentScreen(it)
    }

}

@Composable
private fun CurrentScreen(
    viewState: CurrencyResultInput
) {

    val userEnteredInput : String = viewState.userFromEnteredCurrency
    val calculatedCurrency = (viewState.currencyToRateValue) * (userEnteredInput.toDouble())

    val userEnteredInputResult = userEnteredInput.plus("--").plus(viewState.userFromEnteredCurrencyName)
    val calculatedCurrencyResult = calculatedCurrency.toString().plus("--").plus(viewState.currencyToRateKey)


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val paddingModifier = Modifier.padding(10.dp)
        val cardPadding = 16.dp

        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .padding(cardPadding).fillMaxWidth(),
        ) {
            Box(modifier = Modifier
                .height(200.dp).padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                )  {

                    Text(
                        text = "User entered currency",
                        modifier = paddingModifier,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = userEnteredInputResult,
                        modifier = paddingModifier,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )

                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp).fillMaxWidth())

        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        ) {
            Box(modifier = Modifier
                .height(200.dp)
                .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                )  {

                    Text(
                        text = "Converted currency",
                        modifier = paddingModifier,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = calculatedCurrencyResult,
                        modifier = paddingModifier,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )

                }
            }
        }


    }
}


@Preview
@Composable
private fun ScreenPreview() {
    CurrentScreen(
        viewState = CurrencyResultInput(
            userFromEnteredCurrency = "22",
            userFromEnteredCurrencyType = "11",
            userFromEnteredCurrencyKey = "3",
            userFromEnteredCurrencyName = "9",
            currencyToRateKey = "82",
            currencyToRateValue = 99.2
        )
    )
}