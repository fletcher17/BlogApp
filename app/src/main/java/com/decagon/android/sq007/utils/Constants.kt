package com.decagon.android.sq007.utils

import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.decagon.android.sq007.Model.AuthorsClass
import com.decagon.android.sq007.Model.CommentClass
import com.decagon.android.sq007.data.PostsClass


val BASE_URL = "https://jsonplaceholder.typicode.com/"

object CommonUse {

 var PostData: List<PostsClass> = mutableListOf()
 var commentData: List<CommentClass> = mutableListOf()
 var authorsName : List<AuthorsClass> = mutableListOf()

}

//private fun recyclerViewVisibility() {
// if(listOfClientData.isNotEmpty()) {
//  binding.fragmentMeasurementTabRecyclerview.isVisible = true
//  binding.fragmentMeasurementTabButton.isVisible = true
//  binding.fragmentAddMeasurementTabTextView.isVisible = false
//
//  binding.fragmentMeasurementTabRecyclerview.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
//  binding.fragmentMeasurementTabRecyclerview.adapter = clientAdapterMeasurement
// }
// else {
//  binding.fragmentAddMeasurementTabTextView.isVisible = true
// }

