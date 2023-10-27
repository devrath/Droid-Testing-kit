package com.istudio.code.domain

import com.istudio.code.datagenerators.profile

class UserRepositoryFake : UserRepository {

    var profile  = profile()
    var errorToReturn : Exception? = null

    override suspend fun getProfile(userId: String): Result<Profile> {
        return if(errorToReturn != null) {
            Result.failure(errorToReturn!!)
        } else {
            Result.success(profile)
        }
    }


}