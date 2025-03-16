package com.example.helloworld

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class HelloUserActivity : AppCompatActivity(R.layout.activity_hello_user) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        val editText = findViewById<EditText>(R.id.etUserName)
        val button = findViewById<Button>(R.id.btnHello)

        button.setOnClickListener {
            val intent = Intent(this, HelloWorldActivity::class.java)
            startActivity(intent)
        }
    }
}