package com.example.mygame.screens


import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.mygame.R
import com.example.mygame.databinding.FragmentHighscoresBinding

/**
 * A simple [Fragment] subclass.
 */
class HighScoresFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentHighscoresBinding>(inflater,
            R.layout.fragment_highscores, container, false)

        binding.titleButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(HighScoresFragmentDirections.actionHighScoresFragmentToTitleFragment())
        )

        return binding.highScoresFragment.rootView
    }


}
