package com.istudio.code.data

import com.istudio.code.domain.Post
import com.istudio.code.domain.User
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApi {

    @GET("/user")
    suspend fun getUser(@Path("id") id: String): User?

    @GET("/posts")
    suspend fun getPosts(@Query("userId") id: String): List<Post>
}