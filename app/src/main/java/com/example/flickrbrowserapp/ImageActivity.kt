package com.example.flickrbrowserapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.bumptech.glide.Glide

class ImageActivity : AppCompatActivity() {

    lateinit var imageView: ImageView
    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        imageView = findViewById(R.id.imageView2)
        Glide.with(this).load(OurClass.imageViewURL).into(imageView)

        button = findViewById(R.id.button2)

        button.setOnClickListener{
            finish()
        }
    }

}