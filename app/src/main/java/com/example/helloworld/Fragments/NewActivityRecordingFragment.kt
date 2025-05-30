package com.example.helloworld.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.helloworld.App
import com.example.helloworld.databinding.FragmentNewActivityRecordingBinding
import com.example.helloworld.db.Activity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.random.Random


class NewActivityRecordingFragment : Fragment() {
    private lateinit var binding: FragmentNewActivityRecordingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewActivityRecordingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var type = arguments?.getString("key")
        if (type == null) type = "Undefined"
        binding.activityType.text = type

        val timeStart = Date()

        binding.buttonFinish.setOnClickListener {
            val timeFinish = Date()
            val duration = timeFinish.time - timeStart.time

            val hours = (duration / 1000 / 60 / 60).toInt()
            val minutes = ((duration / 1000 / 60) % 60).toInt()

            lifecycleScope.launch(Dispatchers.IO) {
                App.INSTANCE.db.activityDao().insert(
                    Activity(
                        id = null,
                        user = "me",
                        date = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(timeStart),
                        distance = Random.nextInt(1, 1000),
                        timeSpentHours = hours,
                        timeSpentMinutes = minutes,
                        timeStart = SimpleDateFormat("HH:mm", Locale.getDefault()).format(timeStart),
                        timeFinish = SimpleDateFormat("HH:mm", Locale.getDefault()).format(timeFinish),
                        activityType = type,
                        comment = ""
                    )
                )
            }

            requireActivity().supportFragmentManager.beginTransaction().apply {
                val newActivityRecordingFragment = requireActivity().supportFragmentManager.findFragmentByTag("recording_fr")
                val newActivityStartFragment = requireActivity().supportFragmentManager.findFragmentByTag("start_fr")

                if (newActivityRecordingFragment != null)
                    remove(newActivityRecordingFragment)


                if (newActivityStartFragment != null)
                    show(newActivityStartFragment)

                commit()
            }
        }
    }
}