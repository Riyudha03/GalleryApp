package com.example.test

import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Base64
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import java.io.ByteArrayOutputStream

class ThirdActivity : AppCompatActivity() {
    private lateinit var imageView: ImageView
    private lateinit var descriptionEditText: EditText
    private lateinit var saveButton: Button

    private lateinit var sharedPreferences: SharedPreferences

    private val selectImageLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == AppCompatActivity.RESULT_OK) {
                val data: Intent? = result.data
                val imageUri = data?.data
                val inputStream = contentResolver.openInputStream(imageUri!!)
                val bitmap = BitmapFactory.decodeStream(inputStream)
                imageView.setImageBitmap(bitmap)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        imageView = findViewById(R.id.imageView)
        descriptionEditText = findViewById(R.id.descriptionEditText)
        saveButton = findViewById(R.id.saveButton)

        sharedPreferences = getSharedPreferences("MyImages", Context.MODE_PRIVATE)

        imageView.setOnClickListener {
            openGallery()
        }

        saveButton.setOnClickListener {
            saveImage()
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        selectImageLauncher.launch(intent)
    }

    private fun saveImage() {
        val imageBitmap = (imageView.drawable as BitmapDrawable).bitmap
        val description = descriptionEditText.text.toString()

        val stream = ByteArrayOutputStream()
        imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        val byteArray = stream.toByteArray()

        val editor = sharedPreferences.edit()
        editor.putString("image", Base64.encodeToString(byteArray, Base64.DEFAULT))
        editor.putString("description", description)
        editor.apply()

        finish()
    }
}