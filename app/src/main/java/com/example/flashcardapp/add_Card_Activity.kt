 package com.example.flashcardapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog

 class add_Card_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_card)


        val backImageView:ImageView=findViewById(R.id.delete_question)
        val addNewQuestion: EditText=findViewById(R.id.add_new_question)
        val addNewAnswer: EditText=findViewById(R.id.add_new_answer)
        val saveQuestion: ImageView=findViewById(R.id.download_question)

      // save Question
        saveQuestion.setOnClickListener {
            val newQuestion = addNewQuestion.text.toString()
            val newAnswer = addNewAnswer.text.toString()
//            while (newQuestion == "" && newAnswer == "") {
//                if (newQuestion == "") {
//                    val dialog1 = AlertDialog.Builder(this)
//                    dialog1.setTitle("Warming")
//                    dialog1.setMessage("Yous must add a new question")
//                } else if (newAnswer == "") {
//                    val dialog2 = AlertDialog.Builder(this)
//                    dialog2.setTitle("Warming")
//                    dialog2.setMessage("Yous must add a new answer")
//                }
//            }
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("question", newQuestion)
                intent.putExtra("answer", newAnswer)
                startActivity(intent)
                finish()
            }

        // back to the main activity
        backImageView.setOnClickListener {
            val newQuestion=addNewQuestion.text.toString()
            val newAnswer=addNewAnswer.text.toString()

            val intent=Intent()
            intent.putExtra("question",newQuestion)
            intent.putExtra("answer",newAnswer)
            setResult(RESULT_OK,intent)
            finish()
        }

    }
}