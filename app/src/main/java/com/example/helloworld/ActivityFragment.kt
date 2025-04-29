package com.example.helloworld

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
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

        val activityMineFragment = ActivityMineFragment();
        val activityUsersFragment = ActivityUsersFragment();

        binding.navigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigationActivity -> {
                    val tag = childFragmentManager.findFragmentByTag("activity_users_fr")
                    childFragmentManager.commit() {
                        if (tag != null) hide(activityUsersFragment)
                        show(activityMineFragment)
                    }
                    true
                }
                R.id.navigationProfile -> {
                    val tag = childFragmentManager.findFragmentByTag("activity_users_fr")
                    if (tag == null) {
                        childFragmentManager.beginTransaction().apply {
                            add(
                                binding.fragmentContainer.id,
                                activityUsersFragment,
                                "activity_users_fr"
                            )
                            commit()
                        }
                    }
                    childFragmentManager.commit {
                        hide(activityMineFragment)
                        show(activityUsersFragment)
                    }
                    true
                }
                else -> false
            }
        }

        if (savedInstanceState == null) {
            childFragmentManager.beginTransaction().apply {
                add(
                    binding.fragmentContainer.id,
                    activityMineFragment,
                    "activity_mine_fr"
                )
                commit()
            }
        }
    }
}