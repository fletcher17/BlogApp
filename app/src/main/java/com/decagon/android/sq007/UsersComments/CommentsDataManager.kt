package com.decagon.android.sq007.UsersComments

import android.content.Context
import androidx.preference.PreferenceManager
import com.decagon.android.sq007.Model.CommentClass
import com.decagon.android.sq007.utils.CommonUse
import com.decagon.android.sq007.utils.CommonUse.commentData

class CommentsDataManager(val context: Context) {


    fun saveComments(listOfComments: CommentClass) {
        //creates a preference manager to save preference
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context.applicationContext).edit()
        sharedPrefs.putString(listOfComments.name, listOfComments.body)
        sharedPrefs.apply()
    }

    // Create a function to read the list
    fun readListOfComments() : ArrayList<CommentClass> {
        //reference to sharedPref
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context.applicationContext)
        //gets the content (it contains keys and values
        val contents = sharedPrefs.all
        //This saves the list of comment gotten from the contents (sharedprefs)
       var commentsLists = ArrayList<CommentClass>()

        //Loop through the contents
        for(comments in contents) {
            val list = CommentClass(1,2,comments.key,"femi@gmail.com", comments.value.toString())
            commentsLists.add(list)
        }
        return commentsLists
    }

//    listOfComments.forEach {
//        sharedPrefs.edit().putString(it.name, it.body)
//        sharedPrefs.edit().apply()
//    }

}