package com.decagon.android.sq007

import android.content.Intent
import android.net.NetworkInfo
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
import com.decagon.android.sq007.ViewModel.MainViewModel
import com.decagon.android.sq007.data.PostsClass
import com.decagon.android.sq007.utils.CommonUse.PostData
import com.decagon.android.sq007.utils.CommonUse.authorsName
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_comment.view.*

class MainActivity : AppCompatActivity(), PostClickListener {


    private lateinit var viewmodel: MainViewModel
    private lateinit var postsRecyclerView: RecyclerView
    private lateinit var postAdapter: PostsAdapter
    private lateinit var search_adapter: PostsAdapter
    private lateinit var search_bar: SearchView
    private lateinit var fabButton: FloatingActionButton
    private lateinit var disposable: Disposable


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        postsRecyclerView = findViewById(R.id.recyclerview_activity_main)

        search_bar = findViewById(R.id.search_bar_activity_main)
        fabButton = findViewById(R.id.fab_main_activity)


        fabButton.setOnClickListener {
            PostUser()
        }


        //Initializing the view model
        viewmodel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewmodel.getAllPostData()
        viewmodel.getAllAuthorData()

    //    postAdapter = PostsAdapter(authorsName, this)
        viewmodel.readAllData.observe(this, Observer {listOfPosts ->
            PostData = listOfPosts

            viewmodel.allAuthorData.observe(this, Observer {
                authorsName = it
                postAdapter = PostsAdapter(authorsName, this)
                postAdapter.setList(PostData)
                postsRecyclerView.layoutManager = LinearLayoutManager(this)
                postsRecyclerView.adapter = postAdapter
            })
        })


        //Search Bar Queries the list of Data and displays in the recyclerview
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

        //check for internet connectivity
//        disposable = ReactiveNetwork
//            .observeNetworkConnectivity(this)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe {
//                if (it.state() == NetworkInfo.State.CONNECTED) {
//                    ListOfPosts()
//                } else if (it.state() == NetworkInfo.State.DISCONNECTED) {
//                    Toast.makeText(this, "No Internet", Toast.LENGTH_SHORT).show()
//                }
//            }


    }

    //Onclick item on the recyclerview
    override fun onClickItem(postId: Int) {
        val intent = Intent(this, CommentActivity::class.java)
        intent.putExtra("id", postId)
        startActivity(intent)
    }

    //Search posts in hte postData list
    fun startSearch(text: String) {
        if (PostData.isNotEmpty()) {
            val resultPost = ArrayList<PostsClass>()
            for (posts in PostData) {
                if (posts.title.toLowerCase().contains(text.toLowerCase()))
                    resultPost.add(posts)
                search_adapter = PostsAdapter(authorsName, this)
                search_adapter.setList(resultPost)
                postsRecyclerView.adapter = search_adapter
            }
        } else {
            Toast.makeText(this, "Data not found", Toast.LENGTH_SHORT).show()
        }
    }

    //User Posts from the Alert Dialog
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
            viewmodel.addUserPost(postsTyped)


            //     commentViewModel.addCommentsFromRoomDataBase(commentsTyped)
            viewmodel.readAllData.observe(this, Observer { postList ->
                viewmodel.allAuthorData.observe(this, Observer { authorLiist ->
                    authorsName = authorLiist
                    postAdapter.setList(postList)
                })

            })
            commentDialog.dismiss()
        }
    }

    //Post List Observed by the view Model
    fun ListOfPosts() {
        viewmodel.readAllData.observe(this, Observer {listOfPosts ->
            PostData = listOfPosts
            viewmodel.allAuthorData.observe(this, Observer {
                authorsName = it
                postAdapter.setList(listOfPosts)
                postAdapter = PostsAdapter(authorsName, this)
                postsRecyclerView.layoutManager = LinearLayoutManager(this)
                postsRecyclerView.adapter = postAdapter
            })
        })
    }
}