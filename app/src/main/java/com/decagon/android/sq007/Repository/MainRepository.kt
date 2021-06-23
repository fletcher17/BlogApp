package com.decagon.android.sq007.Repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.decagon.android.sq007.Model.AuthorsClass
import com.decagon.android.sq007.Model.CommentClass
import com.decagon.android.sq007.Retrofits.RetrofitClient.apiservice
import com.decagon.android.sq007.UsersComments.CommentsDataManager
import com.decagon.android.sq007.data.PostsClass
import com.decagon.android.sq007.data.UserDao

class MainRepository(val userDao: UserDao) {

    //read posts from the Database via the DAO
    val readAllPostData: LiveData<List<PostsClass>> = userDao.readAllData()

    //read Users post from the dao
    suspend fun addUserPost(user: PostsClass) {
        userDao.addUser(user)
    }



    /**While the Repository gets post data from the API,
    It drops the Data in the room data base
    */
   suspend fun getPostFromApi() {
        var res = apiservice.getAllPost()
       userDao.addAllPost(res)

    }

    suspend fun getAuthorsFromApi() : List<AuthorsClass> {
        var res = apiservice.getAllAuthors()
        return res
    }

    suspend fun getCommentsFromApi() : List<CommentClass> {
        var res = apiservice.getAllComments()
        return res
    }

//    fun getCommentsFromSharedPref(context: Context) : ArrayList<CommentClass> {
//        var res = CommentsDataManager(context).readListOfComments()
//        return res
//    }

}