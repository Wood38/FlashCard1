package com.example.flashcardapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firstTextView: TextView = findViewById(R.id.flaschcard_question)
        val secondTextView: TextView = findViewById(R.id.flaschcard_answer)

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
    }
}



