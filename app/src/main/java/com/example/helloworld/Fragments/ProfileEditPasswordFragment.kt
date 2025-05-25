package com.example.helloworld.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.helloworld.databinding.FragmentProfileEditPasswordBinding


class ProfileEditPasswordFragment : Fragment() {
    private lateinit var binding: FragmentProfileEditPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileEditPasswordBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonBack.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().apply {
                val profileFragment = requireActivity().supportFragmentManager.findFragmentByTag("profile_fr")
                val profileEditPasswordFragment = requireActivity().supportFragmentManager.findFragmentByTag("edit_password_fr")

                if (profileEditPasswordFragment != null)
                    remove(profileEditPasswordFragment)
                if (profileFragment != null)
                    show(profileFragment)

                commit()
            }
        }
    }
}