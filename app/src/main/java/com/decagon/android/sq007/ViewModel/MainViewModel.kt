package com.decagon.android.sq007.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.decagon.android.sq007.Model.AuthorsClass
import com.decagon.android.sq007.Model.PostsClass
import com.decagon.android.sq007.Repository.MainRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    var id = 0
    val _allPostData: MutableLiveData<MutableList<PostsClass>> = MutableLiveData<MutableList<PostsClass>>()
    val allPostData: LiveData<MutableList<PostsClass>>
        get() = _allPostData

    val _allAuthorData: MutableLiveData<List<AuthorsClass>> = MutableLiveData<List<AuthorsClass>>()
    val allAuthorData: LiveData<List<AuthorsClass>>
        get() = _allAuthorData



    fun getAllPostData() {

        viewModelScope.launch {
            val response = MainRepository.getPostFromApi()
            _allPostData.value = response
        }

    }

    fun getAllAuthorData() {
        viewModelScope.launch{
            val response = MainRepository.getAuthorsFromApi()
            _allAuthorData.value = response
        }
    }

    fun addPostData(posts: PostsClass) {
        _allPostData.value?.add(posts)
    }

}