package com.example.helloworld.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.helloworld.ActivityAdapter
import com.example.helloworld.databinding.FragmentActivityMineBinding
import com.example.helloworld.App
import com.example.helloworld.R
import com.example.helloworld.db.Activity


class ActivityMineFragment : Fragment() {
    private lateinit var binding: FragmentActivityMineBinding

    private val activityAdapter by lazy {
        val temp = mutableListOf<Activity>()
        ActivityAdapter(temp)
    }

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

        App.INSTANCE.db.activityDao().getAllActivity().observe(viewLifecycleOwner) { activityList ->
            val converted = activityList

            activityAdapter.updateData(converted, binding.textEmpty)
        }

        if (activityAdapter.itemCount == 0) {
            binding.textEmpty.visibility = View.VISIBLE
        } else binding.textEmpty.visibility = View.GONE

        activityAdapter.setItemClickListener {
            val activity = activityAdapter.getActivity(it)
            val detailsFragment = DetailsFragment().apply {
                arguments = Bundle().apply {
                    activity.id?.let { it1 -> putInt("id", it1) }
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
