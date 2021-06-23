package com.decagon.android.sq007.ViewModel

//import android.app.Application
//import androidx.lifecycle.AndroidViewModel
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.viewModelScope
//import com.decagon.android.sq007.Repository.NewUserRepository.UserRepository
//import com.decagon.android.sq007.data.User
//import com.decagon.android.sq007.data.UserDataBase
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//
//class PostViewModel(application: Application) : AndroidViewModel(application) {
//
//    private val readAllData: LiveData<List<User>>
//    private val repository : UserRepository
//
//    init {
//        val userDao = UserDataBase.getDataBase(application).userDao()
//        repository = UserRepository(userDao)
//        readAllData = repository.readAllPostData
//    }
//
//    fun addUserPost(user:User) {
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.addUserPost(user)
//        }
//    }
//}