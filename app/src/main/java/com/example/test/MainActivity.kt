package com.example.test

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var imageButton1: ImageButton
    private lateinit var imageButton2: ImageButton
    private lateinit var imageButton3: ImageButton
    private lateinit var imageButton4: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageButton1 = findViewById(R.id.imageButton1)
        imageButton2 = findViewById(R.id.imageButton2)
        imageButton3 = findViewById(R.id.imageButton3)
        imageButton4 = findViewById(R.id.imageButton4)

        imageButton1.setOnClickListener {
            navigateToSecondPage(R.drawable.image1)
        }

        imageButton2.setOnClickListener {
            navigateToSecondPage(R.drawable.image2)
        }

        imageButton3.setOnClickListener {
            navigateToSecondPage(R.drawable.image3)
        }

        imageButton4.setOnClickListener {
            navigateToSecondPage(R.drawable.image4)
        }
    }

    fun onPlusButtonClick(view:View) {
        navigateToThirdPage()
    }

    private fun navigateToSecondPage(imageResId: Int) {
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra("imageResId", imageResId)
        startActivity(intent)
    }

    private fun navigateToThirdPage() {
        val intent = Intent(this, ThirdActivity::class.java)
        startActivity(intent)
    }
}