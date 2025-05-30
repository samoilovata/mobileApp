package com.example.helloworld.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.helloworld.R
import com.example.helloworld.databinding.FragmentActivityUsersBinding
import com.example.helloworld.ActivityAdapter
import com.example.helloworld.ActivityRepository


class ActivityUsersFragment : Fragment() {
    private lateinit var binding: FragmentActivityUsersBinding

    private val activityRepository = ActivityRepository()
    private val activityAdapter = ActivityAdapter(activityRepository.getActivityUsers())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentActivityUsersBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(binding.recycleView) {
            adapter = activityAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        activityAdapter.setItemClickListener {
            val activity = activityAdapter.getActivity(it)
            val detailsFragment = DetailsFragment().apply {
                arguments = Bundle().apply {
                    activity.id?.let { it1 -> putInt("id", it1) }
                }
                /*
                Это не будет работать, потому что Activity из ActivityRepository нет
                в БД, а нет его там, потому что нет реализации регистрации
                и всей логики с ней связанной, т.к. не было такого задания
                */
            }

            requireActivity().supportFragmentManager.beginTransaction().apply {
                val activityFragment = requireActivity().supportFragmentManager.findFragmentByTag("activity_fr")

                if (activityFragment != null)
                    hide(activityFragment)

                add(
                    R.id.fragmentContainer,
                    detailsFragment,
                    "details_fr"
                )

                commit()
            }
        }
    }
}