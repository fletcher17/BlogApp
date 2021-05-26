package com.decagon.android.sq007.utils

import com.decagon.android.sq007.Model.AuthorsClass
import com.decagon.android.sq007.Model.CommentClass
import com.decagon.android.sq007.Model.PostsClass

val BASE_URL = "https://jsonplaceholder.typicode.com/"

object CommonUse {

 var PostData: List<PostsClass> = mutableListOf()
 var commentData: List<CommentClass> = mutableListOf()
 var authorsName : List<AuthorsClass> = mutableListOf()

}

