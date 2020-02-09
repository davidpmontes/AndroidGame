package com.example.mygame.screens.game


import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.mygame.screens.game.GameFragmentDirections
import com.example.mygame.R
import com.example.mygame.databinding.FragmentGameBinding

/**
 * A simple [Fragment] subclass.
 */
class GameFragment : Fragment() {

    private lateinit var viewModel: GameViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentGameBinding>(inflater,
            R.layout.fragment_game, container, false)

        binding.titleButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(GameFragmentDirections.actionGameFragmentToTitleFragment())
        )

        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)

        setHasOptionsMenu(true)

        Toast.makeText(context, "GLHF", Toast.LENGTH_LONG).show()

        return binding.gameFragment.rootView
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.winner_menu, menu)
        // check if the activity resolves
        if (null == getShareIntent().resolveActivity(activity!!.packageManager)) {
            // hide the menu item if it doesn't resolve
            menu.findItem(R.id.share)?.setVisible(false)
        }
    }

    // Creating our Share Intent
    private fun getShareIntent() : Intent {
        return ShareCompat.IntentBuilder.from(activity!!)
                .setText("Share it!")
                .setType("text/plain")
                .intent
    }

    // Starting an Activity with our new Intent
    private fun shareSuccess() {
        startActivity(getShareIntent())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.share -> shareSuccess()
        }
        return super.onOptionsItemSelected(item)
    }
}
