package com.decagon.android.sq007.dialogFragment

//import android.app.Dialog
//import android.content.DialogInterface
//import android.os.Bundle
//import android.widget.EditText
//import androidx.appcompat.app.AlertDialog
//import androidx.fragment.app.DialogFragment
//import com.decagon.android.sq007.Adapter.CommentAdapter
//import com.decagon.android.sq007.Model.CommentClass
//import com.decagon.android.sq007.R
//import com.decagon.android.sq007.UsersComments.ListOfComments
//
//class MyDialog: DialogFragment() {
//
//   lateinit var commentsAdapter : CommentAdapter
//    lateinit var commentData: List<CommentClass>
//    lateinit var listOfComments: ArrayList<ListOfComments>
//    lateinit var commentsText: EditText
//    lateinit var name: EditText
////    lateinit var commentsDataManager: CommentsDataManager
//
//
//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        return activity?.let {
//            val alertDialog = AlertDialog.Builder(it)
//            alertDialog.setView(requireActivity().layoutInflater.inflate(R.layout.comment_dialog, null))
//
//            alertDialog.setPositiveButton("Add Comment", DialogInterface.OnClickListener({dialog, _ ->
//       //             name = findViewById(R.id.commentdialog_nametextview)
//                    commentsText = it.findViewById(R.id.comentdialog_editTextTextMultiLine)
//                val commentsTyped = ListOfComments(name.text.toString(), commentsText.text.toString())
//     //           commentsDataManager.saveComments(commentsTyped)
//                commentsAdapter = CommentAdapter(commentData)
////                commentsAdapter.addComments(commentsTyped)
//
//                    dialog.dismiss()
//            }))
//            alertDialog.create()
//        }?:throw IllegalStateException("Activity is null")
//    }
//}