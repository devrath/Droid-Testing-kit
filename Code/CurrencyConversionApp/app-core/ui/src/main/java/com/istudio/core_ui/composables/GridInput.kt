package com.istudio.core_ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.istudio.models.local.RatesEntity

@Composable
fun GridInput(
    modifier: Modifier = Modifier,
    data: List<RatesEntity>,
    onClick: (Int) -> Unit
){
    // Context
    val noOfColumns = 3
    val state = rememberLazyGridState()
    LazyVerticalGrid(
        modifier = Modifier.background(color = MaterialTheme.colorScheme.background),
        columns = GridCells.Fixed(noOfColumns),
        state = state,
        content = {
            items(data.size){ i ->
                GridInputItem(
                    data[i], position = i,
                ){ selectedItem ->
                    onClick.invoke(selectedItem)
                }
            }
        }
    )

}

@Preview
@Composable
private fun CurrentDisplay() {
    GridInput(data = emptyList()){

    }
}