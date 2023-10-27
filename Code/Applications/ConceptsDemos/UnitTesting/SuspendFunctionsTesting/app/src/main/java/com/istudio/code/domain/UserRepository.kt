package com.istudio.code.domain

interface UserRepository {
    suspend fun getProfile(userId: String): Result<Profile>
}