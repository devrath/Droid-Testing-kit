package com.istudio.core.platform.functional

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class UseCaseResult<out R> {

    data class Success<out T>(val value: T) : UseCaseResult<T>()
    data class Error(val exception: Exception) : UseCaseResult<Nothing>()
    object Loading : UseCaseResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$value]"
            is Error -> "Error[exception=$exception]"
            Loading -> "Loading"
        }
    }
}