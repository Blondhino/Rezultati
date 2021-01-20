package com.blondi.rezultati.ui.matchDetails.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.blondi.rezultati.R
import com.blondi.rezultati.common.model.MatchModel
import com.blondi.rezultati.databinding.ActivityMatchDetailsBinding
import com.blondi.rezultati.ui.base.BaseActivity
import com.blondi.rezultati.ui.home.data.HomeViewModel
import com.blondi.rezultati.ui.home.data.MatchAdapter
import com.blondi.rezultati.ui.home.view.HomeActivity.Companion.MATCH_ID
import com.blondi.rezultati.ui.matchDetails.data.DetailsAdapter
import com.blondi.rezultati.ui.matchDetails.data.MatchDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MatchDetailsActivity : BaseActivity() {
    private lateinit var binding: ActivityMatchDetailsBinding
    private val viewModel: MatchDetailsViewModel by viewModel()
    private val detailsAdapter = DetailsAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMatchDetailsBinding.inflate(layoutInflater)
        binding.activity = this
        binding.eventsRecycler.adapter=detailsAdapter
        val view = binding.root
        setContentView(view)
        fetchData()
        observeData()
        setupUI()
    }

    private fun observeData() {
        viewModel.match.observe(this, matchObserver)
    }

    private val matchObserver = Observer<MatchModel>{
        hideLoadingDialog()
        binding.teamName1.text=it.teamOne?.name
        binding.teamName2.text=it.teamTwo?.name
        binding.result.text=it.score

        detailsAdapter.insertEvents(it.events)

    }

    private fun fetchData() {
        showLoadingDialog()
        viewModel.getSingleMatchDetails(intent.getIntExtra(MATCH_ID, 0))
    }

    private fun setupUI() {
    }

}
