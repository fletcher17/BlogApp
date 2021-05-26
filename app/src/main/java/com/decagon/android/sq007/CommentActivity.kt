package com.decagon.android.sq007

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceDataStore
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.decagon.android.sq007.Adapter.CommentAdapter
import com.decagon.android.sq007.Model.CommentClass
import com.decagon.android.sq007.UsersComments.CommentsDataManager
import com.decagon.android.sq007.ViewModel.CommentViewModel
import com.decagon.android.sq007.utils.getListOfComment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CommentActivity : AppCompatActivity() {

    lateinit var commentViewModel: CommentViewModel
    lateinit var commentRecyclerView: RecyclerView
    lateinit var commentAdapter: CommentAdapter
    lateinit var fabbutton: FloatingActionButton
    lateinit var name: EditText
   // val commentDataManager: CommentsDataManager = CommentsDataManager()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)
        commentViewModel = ViewModelProvider(this).get(CommentViewModel::class.java)
        commentRecyclerView = findViewById(R.id.comment_recyclerview)

        //floating Action Button
        fabbutton = findViewById(R.id.fab_comment_activity)
        fabbutton.setOnClickListener {
            CommentUser()
        }
        //data from intent is retrieved
        commentViewModel.id = intent.getIntExtra("id", 0)

        //The viewModel
        commentViewModel.getAllCommentData()



        commentViewModel.allCommentData.observe(this, Observer {
            updateRecyclerview(it)
        })
    }




    fun CommentUser() {
        var dialog = AlertDialog.Builder(this)
        val view = LayoutInflater.from(this).inflate(R.layout.comment_dialog, null)
        dialog.setView(view)
        val commentDialog = dialog.create()
        commentDialog.show()

        var name: EditText = view.findViewById(R.id.commentdialog_nametextview)
        var email: EditText = view.findViewById(R.id.commentdialog_emailtextview)
        var comment: EditText = view.findViewById(R.id.comentdialog_editTextTextMultiLine)
        var add: TextView = view.findViewById(R.id.add_comment_textView)

        add.setOnClickListener {
            var commentsTyped = CommentClass(
                commentViewModel.id,
                0,
                name.text.toString(),
                email.text.toString(),
                comment.text.toString()
            )

        //    commentDataManager.saveComments(commentsTyped)
           commentViewModel.addCommentData(commentsTyped)

       //     commentViewModel.addCommentsFromRoomDataBase(commentsTyped)
            commentViewModel.allCommentData.observe(this, Observer {

                updateRecyclerview(it)

            })
            commentDialog.dismiss()
        }



//
//        add.setOnClickListener {
//            var commentsTyped = CommentClass(
//                commentViewModel.id,
//                0,
//                name.text.toString(),
//                email.text.toString(),
//                comment.text.toString()
//            )
//
//            commentViewModel.addCommentData(commentsTyped)
//
//            commentViewModel.allCommentData.observe(this, Observer {
//
//                updateRecyclerview(it)
//
//            })
//            commentDialog.dismiss()



//
//        dialog.setPositiveButton("Add comment", DialogInterface.OnClickListener { dialog, _ ->
//            var commentsTyped = CommentClass(1,2,name.text.toString(),email.text.toString(), comment.text.toString())
//            commentsDataManager.saveComments(commentsTyped)
//            commentData = listOf(commentsTyped)
//            commentAdapter = CommentAdapter(commentData)
//        })

    }

    private fun updateRecyclerview(list : MutableList<CommentClass>) {

        commentAdapter = CommentAdapter(getListOfComment(list, commentViewModel.id))
        commentRecyclerView.layoutManager = LinearLayoutManager(this)
        commentRecyclerView.adapter = commentAdapter
    }

}