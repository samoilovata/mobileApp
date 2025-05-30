package com.example.helloworld.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.helloworld.R
import com.example.helloworld.TypeAdapter
import com.example.helloworld.databinding.FragmentNewActivityStartBinding


class NewActivityStartFragment : Fragment() {
    private lateinit var binding: FragmentNewActivityStartBinding

    private val list = listOf("Велосипед", "Бег", "Шаг")
    private val typeAdapter = TypeAdapter(list)
    private var currentActivityType = list[0]

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewActivityStartBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonStart.setOnClickListener {
            val bundle = Bundle().apply {
                putString("key", currentActivityType)
            }

            requireActivity().supportFragmentManager.beginTransaction().apply {
                val newActivityStartFragment = requireActivity().supportFragmentManager.findFragmentByTag("start_fr")

                if (newActivityStartFragment != null)
                    hide(newActivityStartFragment)

                val newActivityRecordingFragment = NewActivityRecordingFragment()
                newActivityRecordingFragment.arguments = bundle

                add(
                    R.id.fragmentContainer,
                    newActivityRecordingFragment,
                    "recording_fr"
                )

                commit()
            }
        }

        with(binding.recycleView) {
            adapter = typeAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        typeAdapter.setItemClickListener {
            val typeInfo = typeAdapter.getType(it)
            currentActivityType = typeInfo

            typeAdapter.updateType(typeInfo)
        }
    }
}