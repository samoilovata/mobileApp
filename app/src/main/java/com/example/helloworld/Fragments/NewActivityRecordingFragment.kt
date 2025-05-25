package com.example.helloworld.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.helloworld.databinding.FragmentNewActivityRecordingBinding


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

        binding.buttonFinish.setOnClickListener {
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