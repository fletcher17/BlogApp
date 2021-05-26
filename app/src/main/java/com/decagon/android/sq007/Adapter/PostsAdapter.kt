 package com.decagon.android.sq007.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.decagon.android.sq007.ClickListener.PostClickListener
import com.decagon.android.sq007.Model.AuthorsClass
import com.decagon.android.sq007.Model.PostsClass
import com.decagon.android.sq007.R
import com.decagon.android.sq007.utils.authorNamePerPost

class PostsAdapter(var listOfPosts: List<PostsClass>, var listOfAuthors: List<AuthorsClass>, var clickItem: PostClickListener) : RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.posts_list, parent, false)
        return PostsViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.titlePost.text = listOfPosts[position].title
        holder.bodyPost.text = listOfPosts[position].body
       holder.authorName.text = authorNamePerPost(listOfPosts[position].userId, listOfAuthors)
    }

    override fun getItemCount(): Int {
        return listOfPosts.size
    }

    inner class PostsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var titlePost: TextView
        var bodyPost : TextView
        var authorName: TextView

       init {
           titlePost = itemView.findViewById(R.id.title_post)
           bodyPost = itemView.findViewById(R.id.body_post)
           authorName = itemView.findViewById(R.id.authors_name)
           itemView.setOnClickListener(this)
       }

        override fun onClick(v: View?) {
            clickItem.onClickItem(listOfPosts[adapterPosition].id)
        }
    }
}