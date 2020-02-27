package com.example.mygame.screens


import android.app.DatePickerDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.*
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.mygame.R
import com.example.mygame.databinding.FragmentTitleBinding
import java.util.*

/**
 * A simple [Fragment] subclass.
 */

class TitleFragment : Fragment(), DatePickerDialog.OnDateSetListener {

    var mediaPlayer: MediaPlayer? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentTitleBinding>(inflater,
            R.layout.fragment_title, container, false)

        //binding.gameButton.setOnClickListener(
        //    Navigation.createNavigateOnClickListener(TitleFragmentDirections.actionTitleFragmentToGameFragment())
        //)

        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context, R.raw.network)
        }
        mediaPlayer!!.start()

        binding.highScoresButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(TitleFragmentDirections.actionTitleFragmentToHighScoresFragment())
        )

        binding.secondActivityButton.setOnClickListener {
            mediaPlayer?.stop()
            val intent = Intent(this.activity, SecondActivity::class.java)
            intent.putExtra("name", "David")
            startActivity(intent)
        }

        binding.setDateButton.setOnClickListener{
            showDateDialog()
        }

        setHasOptionsMenu(true)

        return binding.titleFragment.rootView
    }

    fun showDateDialog() {
        var datePickerDialog = DatePickerDialog(
            this.context!!,
            this,
            Calendar.getInstance().get(Calendar.YEAR),
            Calendar.getInstance().get(Calendar.MONTH),
            Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        Toast.makeText(activity, "The date of your adventure is set!", Toast.LENGTH_SHORT).show()
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
