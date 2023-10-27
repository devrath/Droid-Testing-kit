package com.istudio.code.domain

import com.istudio.code.domain.Profile

data class ProfileState(
    val profile: Profile? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
