package com.example.flashcardapp

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.widget.Toast
import kotlin.math.hypot
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var flashcardDatabase: FlashcardDatabase
    var allFlashcards = mutableListOf<Flashcard>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.drawable.book)


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

                var w = 0
                val slideout = AnimationUtils.loadAnimation(this, R.anim.slide2)
                firstTextView.startAnimation(slideout)
                firstTextView.visibility = View.VISIBLE
                w++


            }
        }
        val rightInAnim = AnimationUtils.loadAnimation(this, R.anim.right)
        nextQuestion.startAnimation(rightInAnim)

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


                val slide2 = AnimationUtils.loadAnimation(this, R.anim.slide2)
                firstTextView.startAnimation(slide2)
                firstTextView.visibility = View.INVISIBLE

                val slide1 = AnimationUtils.loadAnimation(this, R.anim.slide1)
                secondTextView.startAnimation(slide1)
                secondTextView.visibility = View.VISIBLE
            }
        }



        secondTextView.setOnClickListener {
            secondTextView.visibility = View.GONE
            firstTextView.visibility = View.VISIBLE
        }

        firstTextView.setOnClickListener {

            // Calculate the center coordinates of the view
            val cx = secondTextView.width / 2
            val cy = secondTextView.height / 2

            // Calculate the final radius for the circular reveal animation
            val finalRadius = hypot(cx.toDouble(), cy.toDouble()).toFloat()

            // Create the circular reveal animation
            val anim: Animator = ViewAnimationUtils.createCircularReveal(secondTextView, cx, cy, 0f, finalRadius)
            firstTextView.visibility=View.INVISIBLE
            secondTextView.visibility=View.VISIBLE
            // Set the duration of the animation (in milliseconds)
            anim.duration = 3000

            // Start the animation
            anim.start()
        }



        //Animation add_Card_Activity
        addImageView.setOnClickListener {
            val intent = Intent(this, add_Card_Activity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.right, R.anim.left)
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
            Log.e("TAG","")
        }

    }
    }




