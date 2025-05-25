package com.example.helloworld.Activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.helloworld.Fragments.ActivityFragment
import com.example.helloworld.Fragments.NewActivityRecordingFragment
import com.example.helloworld.Fragments.NewActivityStartFragment
import com.example.helloworld.databinding.ActivityNewBinding

class NewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        binding = ActivityNewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().apply {
                add(
                    binding.fragmentContainer.id,
                    NewActivityStartFragment(),
                    "start_fr"
                )

                commit()
            }
        }

        binding.buttonBack.setOnClickListener {
            val intent = Intent(this, MainActivityActivity::class.java)
            startActivity(intent)
        }
    }
}