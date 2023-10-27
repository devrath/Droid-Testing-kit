package com.istudio.code.domain

data class Profile(
    val user: User,
    val posts: List<Post>
)
