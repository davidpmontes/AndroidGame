package com.example.mygame.screens


import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.mygame.R
import com.example.mygame.databinding.FragmentTitleBinding

/**
 * A simple [Fragment] subclass.
 */
class TitleFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentTitleBinding>(inflater,
            R.layout.fragment_title, container, false)

        binding.gameButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(TitleFragmentDirections.actionTitleFragmentToGameFragment())
        )

        binding.highScoresButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(TitleFragmentDirections.actionTitleFragmentToHighScoresFragment())
        )

        binding.secondActivityButton.setOnClickListener {
            val intent = Intent(this.activity, SecondActivity::class.java)
            startActivity(intent)
        }

        setHasOptionsMenu(true)
        
        return binding.titleFragment.rootView
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,
            view!!.findNavController()) || super.onOptionsItemSelected(item)
    }
}
