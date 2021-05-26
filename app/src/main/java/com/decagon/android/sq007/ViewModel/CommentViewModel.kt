package com.decagon.android.sq007.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.decagon.android.sq007.Model.CommentClass
import com.decagon.android.sq007.Repository.MainRepository
import kotlinx.coroutines.launch

class CommentViewModel : ViewModel() {

    var id = 0
    var commentDatas: MutableList<CommentClass> = mutableListOf()
    val _allCommentData: MutableLiveData<MutableList<CommentClass>> =
        MutableLiveData<MutableList<CommentClass>>()
    val allCommentData: LiveData<MutableList<CommentClass>>
        get() = _allCommentData

    //Get Comments from the repo
    fun getAllCommentData() {

        viewModelScope.launch {
            var response = MainRepository.getCommentsFromApi()
            _allCommentData.value = response.toMutableList()
        }
    }

    //Get comment from dialog
    fun addCommentData(comment: CommentClass) {
        _allCommentData.value?.add(comment)
    }

//    fun addCommentsFromRoomDataBase(commentFromDB: CommentClass) {
//
//        viewModelScope.launch {
//            var response = MainRepository.getCommentsFromSharedPref()
//            _allCommentData.value = response.toMutableList()
//        }
//    }
}