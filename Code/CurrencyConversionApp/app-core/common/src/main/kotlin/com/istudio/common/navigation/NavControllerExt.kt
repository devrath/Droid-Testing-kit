package com.istudio.common.navigation

import androidx.navigation.NavController
import com.istudio.common.platform.uiEvent.UiEvent

fun NavController.navigate(event: UiEvent.Navigate) {
    this.navigate(event.route)
}