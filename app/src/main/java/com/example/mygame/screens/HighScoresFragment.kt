package com.example.mygame.screens


import android.os.Bundle
import android.view.*
import android.widget.TextView
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

//        binding.titleButton.setOnClickListener(
//            Navigation.createNavigateOnClickListener(HighScoresFragmentDirections.actionHighScoresFragmentToTitleFragment())
//        )

        var context = activity!!.applicationContext
        var db = DataBaseHandler(context)

//        binding.insertButton.setOnClickListener {
//            if (scoreInput.text.toString().isNotEmpty())
//            {
//                var newGameScore = GameScores(scoreInput.text.toString())
//                db.insertData(newGameScore)
//            } else {
//                Toast.makeText(context, "Please Fill All Data", Toast.LENGTH_SHORT).show()
//            }
//        }

        binding.btnRead.setOnClickListener{
            var data = db.readData()
            dbresult.text = ""
            for (i in 0..(data.size - 1)) {
                dbresult.append(data.get(i).score + "\n")
            }
        }

        binding.btnDelete.setOnClickListener {
            db.deleteData()
            dbresult.text = ""
        }

        return binding.highScoresFragment.rootView
    }
}
