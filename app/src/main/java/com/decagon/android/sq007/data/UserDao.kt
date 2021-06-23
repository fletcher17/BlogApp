package com.decagon.android.sq007.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    //when a user post is added, it ignores if there is a conflict in the data provided
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: PostsClass)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAllPost(user: List<PostsClass>)



    //To select all the items from the database
    @Query("SELECT * FROM userPost_table ORDER BY id DESC")
    fun readAllData(): LiveData<List<PostsClass>>
}