package com.example.helloworld.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.helloworld.App
import com.example.helloworld.databinding.FragmentDetailsBinding
import com.example.helloworld.db.Activity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = arguments?.getInt("id")

        if (id != null) {
            App.INSTANCE.db.activityDao().getActivityByID(id).observe(viewLifecycleOwner) { activity ->
                fillFragment(activity)
            }
        }

        binding.buttonBack.setOnClickListener {
            parentFragmentManager.beginTransaction().apply {
                remove(this@DetailsFragment)
                val activityFragment = requireActivity().supportFragmentManager.findFragmentByTag("activity_fr")
                if (activityFragment != null) show(activityFragment)
                commit()
            }
        }

        binding.buttonDelete.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                id?.let { it1 -> App.INSTANCE.db.activityDao().delete(it1) }
            }

            parentFragmentManager.beginTransaction().apply {
                remove(this@DetailsFragment)
                val activityFragment = requireActivity().supportFragmentManager.findFragmentByTag("activity_fr")
                if (activityFragment != null) show(activityFragment)
                commit()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    fun fillFragment(activity: Activity?) {
        if (activity != null) {
            binding.activityType.text = activity.activityType
            binding.user.text = "@${activity.user}"
            binding.distance.text = "${activity.distance} м"
            binding.timeSpent.text = "${activity.timeSpentHours} ч ${activity.timeSpentMinutes} мин"
            binding.timePassed.text = "Сейчас"
            binding.timeStart.text = activity.timeStart
            binding.timeFinish.text = activity.timeFinish
        }
    }
}