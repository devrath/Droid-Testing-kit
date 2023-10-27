package com.istudio.code.data

import com.istudio.code.domain.Post
import com.istudio.code.domain.User

class UserApiFake : UserApi {

    var users = (1..10).map {
        User(id = it.toString(), username = "User$it")
    }

    var posts = (1..10).map{
        Post(
            id = it.toString(),
            userId = it.toString(),
            title = "Test title$it",
            body = "Test body$it"
        )
    }

    override suspend fun getUser(id: String): User? {
        // Return the matching user Id
        return users.find { it.id == id }
    }

    override suspend fun getPosts(id: String): List<Post> {
        // GEt all the posts that belong to particular user
        return posts.filter { it.id == id }
    }


}