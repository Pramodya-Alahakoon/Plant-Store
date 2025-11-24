package com.example.botaniq

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddReviewActivity : AppCompatActivity() {

    private lateinit var ratingBar: RatingBar
    private lateinit var commentEditText: EditText
    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_review)

        ratingBar = findViewById(R.id.ratingBar)
        commentEditText = findViewById(R.id.commentEditText)
        submitButton = findViewById(R.id.submitButton)

        submitButton.setOnClickListener {
            val rating = ratingBar.rating
            val comment = commentEditText.text.toString()

            if (rating > 0 || comment.isNotEmpty()) {
                Toast.makeText(this, "Successfully submitted the review", Toast.LENGTH_SHORT).show()
                // Handle the submission logic here, like saving the review to a database or sending it to a server
                startActivity(Intent(this@AddReviewActivity, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Please add a rating or comment", Toast.LENGTH_SHORT).show()
            }
        }
    }
}