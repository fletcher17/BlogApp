package com.decagon.android.sq007.Model

data class PostsClass(val userId: Int, val id: Int, val title: String, val body: String)

data class AuthorsClass(val id: Int, val name: String)

data class CommentClass(val postId: Int, val id: Int, val name: String, val email: String, val body: String)