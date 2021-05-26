package com.decagon.android.sq007.Repository

import android.content.Context
import com.decagon.android.sq007.Model.AuthorsClass
import com.decagon.android.sq007.Model.CommentClass
import com.decagon.android.sq007.Model.PostsClass
import com.decagon.android.sq007.Retrofits.RetrofitClient.apiservice
import com.decagon.android.sq007.UsersComments.CommentsDataManager

object MainRepository {
   suspend fun getPostFromApi() : MutableList<PostsClass>{
        var res = apiservice.getAllPost()
       return res
    }

    suspend fun getAuthorsFromApi() : List<AuthorsClass> {
        var res = apiservice.getAllAuthors()
        return res
    }

    suspend fun getCommentsFromApi() : List<CommentClass> {
        var res = apiservice.getAllComments()
        return res
    }

    fun getCommentsFromSharedPref(context: Context) : ArrayList<CommentClass> {
        var res = CommentsDataManager(context).readListOfComments()
        return res
    }
}