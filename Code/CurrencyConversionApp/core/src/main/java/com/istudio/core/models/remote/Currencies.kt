package com.istudio.core.models.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Currencies (
  @SerialName("AED" ) var AED : String? = null
)
