package com.example.mygame.screens


import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.mygame.R
import com.example.mygame.databinding.FragmentHighscoresBinding
import kotlinx.android.synthetic.main.fragment_highscores.*

/**
 * A simple [Fragment] subclass.
 */
class HighScoresFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentHighscoresBinding>(inflater,
            R.layout.fragment_highscores, container, false)

        binding.titleButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(HighScoresFragmentDirections.actionHighScoresFragmentToTitleFragment())
        )

        var context = activity!!.applicationContext

        binding.insertButton.setOnClickListener {
            if (guidInput.text.toString().isNotEmpty() &&
                latitudeInput.text.toString().isNotEmpty() &&
                longitudeInput.text.toString().isNotEmpty() &&
                altitudeInput.text.toString().isNotEmpty())
            {
                var newAircraft = Aircraft(guidInput.text.toString(), latitudeInput.text.toString(), longitudeInput.text.toString(), altitudeInput.text.toString())
                var db = DataBaseHandler(context)
                db.insertData(newAircraft)
            } else {
                Toast.makeText(context, "Please Fill All Data", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.highScoresFragment.rootView
    }
}
