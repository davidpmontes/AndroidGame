package com.example.mygame


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.mygame.databinding.FragmentAboutBinding

/**
 * A simple [Fragment] subclass.
 */
class AboutFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentAboutBinding>(inflater, R.layout.fragment_about, container, false)
        binding.titleButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_aboutFragment_to_titleFragment)
        )
        return binding.root
    }


}