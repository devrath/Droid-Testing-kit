package com.istudio.code.datagenerators

import com.istudio.code.domain.Post
import com.istudio.code.domain.Profile
import com.istudio.code.domain.User
import java.util.UUID

fun user(): User {
    return User(
        id = UUID.randomUUID().toString(),
        username = "test-user"
    )
}

fun post(userId: String): Post {
    return Post(
        id = UUID.randomUUID().toString(),
        userId = userId,
        title = "test title",
        body = "test body"
    )
}

fun profile(): Profile {
    val user = user()
    return Profile(
        user = user,
        posts = (1..10).map {
            post(user.id)
        }
    )
}