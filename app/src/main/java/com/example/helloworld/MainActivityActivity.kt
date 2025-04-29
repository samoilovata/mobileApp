package com.example.helloworld

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.helloworld.databinding.ActivityMainActivityBinding
import androidx.fragment.app.commit


class MainActivityActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        binding = ActivityMainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val activityFragment = ActivityFragment()
        val profileFragment = ProfileFragment()

        binding.navigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigationActivity -> {
                    val tag = supportFragmentManager.findFragmentByTag("profile_fr")
                    supportFragmentManager.commit() {
                        if (tag != null) hide(profileFragment)
                        show(activityFragment)
                    }
                    true
                }
                R.id.navigationProfile -> {
                    val tag = supportFragmentManager.findFragmentByTag("profile_fr")
                    if (tag == null) {
                        supportFragmentManager.beginTransaction().apply {
                            add(
                                binding.fragmentContainer.id,
                                profileFragment,
                                "profile_fr"
                            )
                            commit()
                        }
                    }
                    supportFragmentManager.commit {
                        hide(activityFragment)
                        show(profileFragment)
                    }
                    true
                }
                else -> false
            }
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().apply {
                add(
                    binding.fragmentContainer.id,
                    activityFragment,
                    "activity_fr"
                )
                commit()
            }
        }
    }
}