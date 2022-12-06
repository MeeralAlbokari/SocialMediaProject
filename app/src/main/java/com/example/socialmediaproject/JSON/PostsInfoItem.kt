package com.example.socialmediaproject.JSON

data class PostsInfoItem(

    val id: Int,
    val title: String,
    val user: String,
    val text: String,
    var likes: String,
    var comments: String
)