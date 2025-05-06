package com.example.helloworld.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.helloworld.ActivityAdapter
import com.example.helloworld.databinding.FragmentActivityMineBinding
import com.example.helloworld.ActivityRepository
import com.example.helloworld.R


class ActivityMineFragment : Fragment() {
    private lateinit var binding: FragmentActivityMineBinding

    private val activityRepository = ActivityRepository()
    private val activityAdapter = ActivityAdapter(activityRepository.getActivityMine())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentActivityMineBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(binding.recycleView) {
            adapter = activityAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        activityAdapter.setItemClickListener {
            val activityInfo = activityAdapter.getActivity(it)
            val detailsFragment = DetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("activity_info", activityInfo)
                }
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
