package com.example.helloworld.Fragments

import ActivityInfo
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.helloworld.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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

        val arg = arguments?.getParcelable<ActivityInfo>("activity_info")
        fillFragment(arg)

        binding.buttonBack.setOnClickListener {
            parentFragmentManager.beginTransaction().apply {
                remove(this@DetailsFragment)
                val activityFragment = requireActivity().supportFragmentManager.findFragmentByTag("activity_fr")
                if (activityFragment != null) show(activityFragment)
                commit()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    fun fillFragment(activityInfo: ActivityInfo?) {
        if (activityInfo != null) {
            binding.activityType.text = activityInfo.activityType
        }
        if (activityInfo != null) {
            if (activityInfo.user != "myProfile") binding.user.text = "@${activityInfo.user}"
        }
        if (activityInfo != null) {
            binding.distance.text = "${activityInfo.distance} м"
        }
        if (activityInfo != null) {
            binding.timePassed.text = activityInfo.timePassed
        }
        if (activityInfo != null) {
            binding.timeStart.text = activityInfo.timeStart
        }
        if (activityInfo != null) {
            binding.timeFinish.text = activityInfo.timeFinish
        }
    }
}