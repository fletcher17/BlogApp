package com.decagon.android.sq007.utils

import com.decagon.android.sq007.Model.CommentClass

fun getPostsComment(id : Int, commentsList: List<CommentClass>) : String {
    var comments = "no comments"
    for (i in commentsList.indices) {
        if (id == commentsList[i].postId) {
            comments = commentsList[i].body
            break
        }
    }
    return comments
}

fun getPostsName(id : Int, commentsList: List<CommentClass>) : String {
    var comments = "no name"
    for (i in commentsList.indices) {
        if (id == commentsList[i].postId) {
            comments = commentsList[i].name
            break
        }
    }
    return comments
}

fun getPostsEmail(id : Int, commentsList: List<CommentClass>) : String {
    var comments = "no email"
    for (i in commentsList.indices) {
        if (id == commentsList[i].postId) {
            comments = commentsList[i].email
            break
        }
    }
    return comments
}

var email = "okeh.joseph@yahoo.com"

fun getCommentNameFromEmail(email : String) : String{
    var name = ""
    for (i in email.indices){
        if (email[i] != '@') {
            name += email[i]
        } else break
    }

    return name
}