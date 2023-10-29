package com.istudio.currency_converter.presentation.landscape

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.istudio.core_ui.composables.DropDownField
import com.istudio.core_ui.composables.GridInput
import com.istudio.core_ui.composables.InputTextField
import com.istudio.core_ui.theme.LocalSpacing

@Composable
fun CurrencyScreenLandscape(onKeyBoardOutsideClick: () -> Unit) {

    Row(modifier = Modifier
        .fillMaxSize()
        .padding(LocalSpacing.current.spaceExtraSmall)
        .pointerInput(Unit) {
            detectTapGestures(onTap = {
                onKeyBoardOutsideClick.invoke()
            })
        }
    ) {
        Column(
            modifier = Modifier.weight(1f).fillMaxHeight()
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().weight(1f),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                InputTextField(modifier = Modifier.fillMaxWidth())

                Spacer(modifier = Modifier.height(LocalSpacing.current.spaceExtraSmall))

                Box(modifier = Modifier.fillMaxWidth().weight(1f)) { DropDownField() }

                Spacer(modifier = Modifier.height(LocalSpacing.current.spaceExtraSmall))

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
            }
        }
        Column(
            modifier = Modifier.weight(1f).fillMaxHeight()
        ) {
            GridInput(
                modifier = Modifier.fillMaxWidth().weight(1f),
            ){selectedItem ->

            }
        }
    }
}

@Preview(
    showSystemUi = true,
    device = "spec:width=411dp,height=891dp,dpi=420,isRound=false,chinSize=0dp,orientation=landscape"
)
@Composable
private fun CurrentScreen() {
    CurrencyScreenLandscape{

    }
}