package com.decagon.android.sq007.Retrofits

import com.decagon.android.sq007.Model.AuthorsClass
import com.decagon.android.sq007.Model.CommentClass
import com.decagon.android.sq007.data.PostsClass

import retrofit2.http.GET

interface ApiService {
    @GET("posts")
   suspend fun getAllPost() : List<PostsClass>


   @GET("users")
   suspend fun getAllAuthors() : List<AuthorsClass>


   @GET("comments")
   suspend fun getAllComments() : List<CommentClass>
}