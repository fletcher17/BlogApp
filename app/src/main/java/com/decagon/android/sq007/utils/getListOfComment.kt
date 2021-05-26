package com.decagon.android.sq007.utils

import com.decagon.android.sq007.Model.CommentClass

fun getListOfComment(comments : List<CommentClass>, postId : Int) : MutableList<CommentClass>{
    return comments.filter { it.postId == postId }.toMutableList()
}