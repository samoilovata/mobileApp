package com.example.helloworld.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.helloworld.databinding.ActivityMainActivityBinding
import androidx.fragment.app.commit
import com.example.helloworld.Fragments.ActivityFragment
import com.example.helloworld.Fragments.ProfileFragment
import com.example.helloworld.R


class MainActivityActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        binding = ActivityMainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.navigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigationActivity -> {
                    val profileFragment = supportFragmentManager.findFragmentByTag("profile_fr")
                    val activityFragment = supportFragmentManager.findFragmentByTag("activity_fr")
                    val detailsFragment = supportFragmentManager.findFragmentByTag("details_fr")
                    val profileEditPasswordFragment = supportFragmentManager.findFragmentByTag("edit_password_fr")
                    supportFragmentManager.commit {
                        if (detailsFragment != null) remove(detailsFragment)
                        if (profileFragment != null) hide(profileFragment)
                        if (activityFragment != null) show(activityFragment)
                        if (profileEditPasswordFragment != null) remove(profileEditPasswordFragment)
                    }
                    true
                }
                R.id.navigationProfile -> {
                    val profileFragment = supportFragmentManager.findFragmentByTag("profile_fr")
                    val activityFragment = supportFragmentManager.findFragmentByTag("activity_fr")
                    val detailsFragment = supportFragmentManager.findFragmentByTag("details_fr")
                    val profileEditPasswordFragment = supportFragmentManager.findFragmentByTag("edit_password_fr")
                    supportFragmentManager.commit {
                        if (detailsFragment != null) remove(detailsFragment)
                        if (activityFragment != null) hide(activityFragment)
                        if (profileFragment != null) show(profileFragment)
                        if (profileEditPasswordFragment != null) remove(profileEditPasswordFragment)
                    }
                    true
                }
                else -> false
            }
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().apply {
                val profileFragment = ProfileFragment()

                add(
                    binding.fragmentContainer.id,
                    profileFragment,
                    "profile_fr"
                )

                hide(profileFragment)

                add(
                    binding.fragmentContainer.id,
                    ActivityFragment(),
                    "activity_fr"
                )

                commit()
            }
        }
    }
}