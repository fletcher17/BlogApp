package com.decagon.android.sq007.ViewModel

import android.app.Application
import androidx.lifecycle.*
import com.decagon.android.sq007.Model.CommentClass
import com.decagon.android.sq007.Repository.MainRepository
import com.decagon.android.sq007.data.UserDataBase
import kotlinx.coroutines.launch

class CommentViewModel(application: Application) : AndroidViewModel(application) {

    val userDao = UserDataBase.getDataBase(application).userDao()
    var MainRepository : MainRepository = MainRepository(userDao)

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