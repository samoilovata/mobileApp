package com.example.helloworld.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.helloworld.R
import com.example.helloworld.databinding.FragmentActivityBinding


class ActivityFragment : Fragment() {
    private lateinit var binding: FragmentActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentActivityBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.navigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigationMine -> {
                    val usersFragment = childFragmentManager.findFragmentByTag("activity_users_fr")
                    val mineFragment = childFragmentManager.findFragmentByTag("activity_mine_fr")
                    childFragmentManager.commit {
                        if (usersFragment != null) hide(usersFragment)
                        if (mineFragment != null) show(mineFragment)
                    }
                    true
                }
                R.id.navigationUsers -> {
                    val usersFragment = childFragmentManager.findFragmentByTag("activity_users_fr")
                    val mineFragment = childFragmentManager.findFragmentByTag("activity_mine_fr")
                    childFragmentManager.commit {
                        if (mineFragment != null) hide(mineFragment)
                        if (usersFragment != null) show(usersFragment)
                    }
                    true
                }
                else -> false
            }
        }

        if (savedInstanceState == null) {
            childFragmentManager.beginTransaction().apply {
                val activityUsersFragment = ActivityUsersFragment()

                add(
                    binding.fragmentContainer.id,
                    activityUsersFragment,
                    "activity_users_fr"
                )

                hide(activityUsersFragment)

                add(
                    binding.fragmentContainer.id,
                    ActivityMineFragment(),
                    "activity_mine_fr"
                )

                commit()
            }
        }
    }
}