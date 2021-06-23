package com.decagon.android.sq007.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.decagon.android.sq007.Model.AuthorsClass
import com.decagon.android.sq007.Repository.MainRepository
import com.decagon.android.sq007.data.PostsClass
import com.decagon.android.sq007.data.UserDataBase
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {


    var MainRepository : MainRepository
    var readAllData: LiveData<List<PostsClass>>
    val repository : MainRepository


    init {
        val userDao = UserDataBase.getDataBase(application).userDao()
        repository = MainRepository(userDao)
        readAllData = repository.readAllPostData
        MainRepository = MainRepository(userDao)
    }

    var id = 0


    val _allAuthorData: MutableLiveData<List<AuthorsClass>> = MutableLiveData<List<AuthorsClass>>()
    val allAuthorData: LiveData<List<AuthorsClass>>
        get() = _allAuthorData


    fun getAllPostData() {

        viewModelScope.launch {
          MainRepository.getPostFromApi()
        }

    }

    fun getAllAuthorData() {
        viewModelScope.launch{
            val response = MainRepository.getAuthorsFromApi()
            _allAuthorData.value = response
        }
    }



    fun addUserPost(user:PostsClass) {
        viewModelScope.launch(IO) {
            repository.addUserPost(user)
        }
    }


}

//    val _allPostData: MutableLiveData<List<PostsClass>> = MutableLiveData<List<PostsClass>>()
//    val allPostData: LiveData<List<PostsClass>>
//        get() = _allPostData