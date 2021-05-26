package com.decagon.android.sq007.utils

import com.decagon.android.sq007.Model.AuthorsClass
import com.decagon.android.sq007.Model.CommentClass

fun authorNamePerPost(userId : Int, authorList : List<AuthorsClass>) : String {
   var authorname = "femi"
 for (i in authorList.indices){
  if (userId == authorList[i].id) {
   authorname = authorList[i].name
   break
  }
 }

 return authorname
 
 }


