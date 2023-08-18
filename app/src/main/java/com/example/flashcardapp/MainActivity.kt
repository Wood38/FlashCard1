package com.example.flashcardapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var flashcardDatabase: FlashcardDatabase
    var allFlashcards = mutableListOf<Flashcard>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        flashcardDatabase = FlashcardDatabase(this)
        allFlashcards = flashcardDatabase.getAllCards().toMutableList()

        val firstTextView: TextView = findViewById(R.id.flaschcard_question)
        val secondTextView: TextView = findViewById(R.id.flaschcard_answer)
        val addImageView: ImageView = findViewById(R.id.add_question)
        val nextQuestion: ImageView = findViewById(R.id.next_question)
        val suppAnswerAndQuestion: ImageView = findViewById(R.id.delete_question)


        nextQuestion.setOnClickListener() {

            val questionList = flashcardDatabase.getAllCards()
            if (questionList.isNotEmpty()) {
                val randomIndex = Random.nextInt(0, questionList.size)
                val randomQuestion = questionList[randomIndex]
                firstTextView.text = randomQuestion.question
                secondTextView.text = randomQuestion.answer
            }
        }

        suppAnswerAndQuestion.setOnClickListener() {
            var index: Int = 0
            allFlashcards = flashcardDatabase.getAllCards().toMutableList()
            for (index in 0..allFlashcards.size) {

                val questionToDelete = firstTextView.text.toString()
                if (allFlashcards.isNotEmpty()) {
                    flashcardDatabase.deleteCard(questionToDelete)
                    firstTextView.text = ""
                    secondTextView.text = ""
                }

            }
            allFlashcards[index--]

        }

        if (allFlashcards.size > 0) {
            var (question, answer) = allFlashcards[0]
            firstTextView.text = question
            secondTextView.text = answer
        }


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
            flashcardDatabase.insertCard(Flashcard(question, answer))
            allFlashcards = flashcardDatabase.getAllCards().toMutableList()
            firstTextView.text = question
//            Toast.makeText(this,question,Toast.LENGTH_SHORT).show()
            secondTextView.text = answer
//            Toast.makeText(this,allFlashcards,Toast.LENGTH_SHORT).show()
        } else {
            Log.e("TAG", "Al anba")
        }



    }
    }




