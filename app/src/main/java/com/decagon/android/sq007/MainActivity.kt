package com.decagon.android.sq007

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.decagon.android.sq007.Adapter.PostsAdapter
import com.decagon.android.sq007.ClickListener.PostClickListener
import com.decagon.android.sq007.Model.PostsClass
import com.decagon.android.sq007.ViewModel.MainViewModel
import com.decagon.android.sq007.utils.CommonUse.PostData
import com.decagon.android.sq007.utils.CommonUse.authorsName
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), PostClickListener {


    lateinit var viewmodel : MainViewModel
    lateinit var postsRecyclerView : RecyclerView
    lateinit var postAdapter : PostsAdapter
    lateinit var search_adapter : PostsAdapter
    lateinit var search_bar : SearchView
    lateinit var fabButton : FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        postsRecyclerView = findViewById(R.id.recyclerview_activity_main)

        search_bar = findViewById(R.id.search_bar_activity_main)
        fabButton = findViewById(R.id.fab_main_activity)

        fabButton.setOnClickListener {
            PostUser()
        }


        viewmodel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewmodel.getAllPostData()
        viewmodel.getAllAuthorData()


        viewmodel.allPostData.observe(this, Observer{
           PostData = it

            viewmodel.allAuthorData.observe(this, Observer{
                authorsName = it
                postAdapter = PostsAdapter(PostData, authorsName, this)
                postsRecyclerView.layoutManager = LinearLayoutManager(this)
                postsRecyclerView.adapter = postAdapter
            })
        })

        search_bar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                search_bar.clearFocus()
                startSearch(query!!)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                startSearch(newText!!)
                return false
            }


        })



    }

    override fun onClickItem(postId: Int) {
        val intent = Intent(this, CommentActivity::class.java)
        intent.putExtra("id", postId)
        startActivity(intent)
    }

    fun startSearch(text: String) {
        if(PostData.isNotEmpty()){
            val resultPost = ArrayList<PostsClass>()
            for (posts in PostData) {
                if(posts.title.toLowerCase().contains(text.toLowerCase()))
                    resultPost.add(posts)
                search_adapter = PostsAdapter(resultPost, authorsName, this)
                postsRecyclerView.adapter = search_adapter
            }
        }else {
            Toast.makeText(this, "Data not found", Toast.LENGTH_SHORT).show()
        }
    }

    fun PostUser() {
        var dialog = AlertDialog.Builder(this)
        val view = LayoutInflater.from(this).inflate(R.layout.post_dialog, null)
        dialog.setView(view)
        val commentDialog = dialog.create()
        commentDialog.show()

        var title: EditText = view.findViewById(R.id.postdialog_titletextview)
        //   var email: EditText = view.findViewById(R.id.postdialog_emailtextview)
        var body: EditText = view.findViewById(R.id.postdialog_editTextTextMultiLine)
        var add: TextView = view.findViewById(R.id.add_post_textView)

        add.setOnClickListener {
            var postsTyped = PostsClass(
                viewmodel.id,
                0,
                title.text.toString(),
                body.text.toString()
            )

            //    commentDataManager.saveComments(commentsTyped)
            viewmodel.addPostData(postsTyped)

            //     commentViewModel.addCommentsFromRoomDataBase(commentsTyped)
            viewmodel.allPostData.observe(this, Observer {

                viewmodel.allAuthorData.observe(this, Observer {
                    authorsName = it
                    postAdapter = PostsAdapter(PostData, authorsName, this)
                    postsRecyclerView.layoutManager = LinearLayoutManager(this)
                    postsRecyclerView.adapter = postAdapter
                })

            })
            commentDialog.dismiss()
        }
    }
}