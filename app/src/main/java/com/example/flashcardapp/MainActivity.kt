package com.example.flashcardapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firstTextView: TextView = findViewById(R.id.flaschcard_question)
        val secondTextView: TextView = findViewById(R.id.flaschcard_answer)
        val addImageView: ImageView = findViewById(R.id.add_question)

        firstTextView.setOnClickListener {
            if (secondTextView.visibility == View.GONE) {
                secondTextView.visibility = View.VISIBLE
                firstTextView.visibility = View.GONE
            }
        }



        secondTextView.setOnClickListener {
            secondTextView.visibility = View.GONE
            firstTextView.visibility = View.VISIBLE
        }
        //Change act
        addImageView.setOnClickListener {
            val intent = Intent(this, add_Card_Activity::class.java)
            startActivity(intent)
        }

        val question = intent.getStringExtra("question")
        val answer = intent.getStringExtra("answer")

        if (question != null && answer != null) {
            firstTextView.text = question
            Toast.makeText(this,question,Toast.LENGTH_SHORT).show()
            secondTextView.text = answer
            Toast.makeText(this,question,Toast.LENGTH_SHORT).show()

       }


        }
    }




