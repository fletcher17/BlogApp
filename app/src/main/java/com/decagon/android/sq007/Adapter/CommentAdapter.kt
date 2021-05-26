package com.decagon.android.sq007.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.decagon.android.sq007.Model.CommentClass
import com.decagon.android.sq007.R
import com.decagon.android.sq007.UsersComments.ListOfComments
import com.decagon.android.sq007.utils.getCommentNameFromEmail


class CommentAdapter(var comments: List<CommentClass>) : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comment_list, parent, false)
        return CommentViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.nameOfComment.text = comments[position].name
        holder.emailOfComment.text = getCommentNameFromEmail(comments[position].email)
        holder.bodyOfComment.text = comments[position].body
    }

    override fun getItemCount(): Int {
        var Comments = comments.size
        return Comments
    }


    inner class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameOfComment : TextView
        var emailOfComment : TextView
        var bodyOfComment : TextView


        init {
            nameOfComment = itemView.findViewById(R.id.comment_name)
            emailOfComment = itemView.findViewById(R.id.comment_email)
            bodyOfComment = itemView.findViewById(R.id.comment_body)
        }
    }
}

//var listOfPosts: List<PostsClass>