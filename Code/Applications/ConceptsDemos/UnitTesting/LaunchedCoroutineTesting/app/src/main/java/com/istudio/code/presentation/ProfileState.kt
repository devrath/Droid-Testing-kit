package com.istudio.code.presentation

import com.istudio.code.domain.Profile

data class ProfileState(
    val profile: Profile? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
