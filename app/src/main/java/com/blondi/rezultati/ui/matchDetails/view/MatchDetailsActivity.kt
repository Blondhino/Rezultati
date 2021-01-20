package com.blondi.rezultati.ui.matchDetails.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.blondi.rezultati.R
import com.blondi.rezultati.databinding.ActivityMatchDetailsBinding
import com.blondi.rezultati.ui.home.data.HomeViewModel
import com.blondi.rezultati.ui.home.view.HomeActivity.Companion.MATCH_ID
import com.blondi.rezultati.ui.matchDetails.data.MatchDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MatchDetailsActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMatchDetailsBinding
    private val viewModel: MatchDetailsViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMatchDetailsBinding.inflate(layoutInflater)
        binding.activity = this
        val view = binding.root
        setContentView(view)
        fetchData()
        observeData()
        setupUI()
    }

    private fun observeData() {

    }


    private fun fetchData() {
        intent.getIntExtra(MATCH_ID,0).let { Log.d("matchId", it.toString()) }
    }

    private fun setupUI() {
        binding.text.text="Fetching data for matchId: ${intent.getIntExtra(MATCH_ID,0)}"
    }

}
