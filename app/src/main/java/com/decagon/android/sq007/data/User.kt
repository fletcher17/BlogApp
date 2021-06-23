package com.decagon.android.sq007.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userPost_table")
data class PostsClass(
    @PrimaryKey(autoGenerate = true)
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)