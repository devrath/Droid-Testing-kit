package com.istudio.code.domain

import com.istudio.code.domain.Profile

interface UserRepository {
    suspend fun getProfile(userId: String): Result<Profile>
}