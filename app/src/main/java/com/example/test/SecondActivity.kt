package com.example.test

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    private lateinit var imageView: ImageView
    private lateinit var descriptionTextView: TextView
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        imageView = findViewById(R.id.imageView)
        descriptionTextView = findViewById(R.id.descriptionTextView)
        backButton = findViewById(R.id.backButton)

        val imageResId = intent.getIntExtra("imageResId", 0)
        if (imageResId != 0) {
            imageView.setImageResource(imageResId)
            // Set the description for the selected image
            val description = getString(R.string.image_description, imageResId.toString())
            descriptionTextView.text = description
        }

        backButton.setOnClickListener {
            finish()
        }
    }
}
