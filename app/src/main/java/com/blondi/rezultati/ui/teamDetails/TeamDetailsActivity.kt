package com.blondi.rezultati.ui.teamDetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.blondi.rezultati.R
import com.blondi.rezultati.common.model.MatchModel
import com.blondi.rezultati.common.model.Player
import com.blondi.rezultati.databinding.ActivityMatchDetailsBinding
import com.blondi.rezultati.databinding.ActivityTeamDetailsBinding
import com.blondi.rezultati.ui.base.BaseActivity
import com.blondi.rezultati.ui.home.data.HomeViewModel
import com.blondi.rezultati.ui.home.data.MatchAdapter
import com.blondi.rezultati.ui.home.view.HomeActivity.Companion.MATCH_ID
import com.blondi.rezultati.ui.matchDetails.data.DetailsAdapter
import com.blondi.rezultati.ui.matchDetails.data.MatchDetailsViewModel
import com.blondi.rezultati.ui.matchDetails.view.MatchDetailsActivity.Companion.MATCH
import com.blondi.rezultati.ui.matchDetails.view.MatchDetailsActivity.Companion.TEAM_ID
import com.blondi.rezultati.ui.matchDetails.view.MatchDetailsActivity.Companion.TEAM_NAME
import com.blondi.rezultati.ui.matchDetails.view.MatchDetailsActivity.Companion.TEAM_NUM
import org.koin.androidx.viewmodel.ext.android.viewModel

class TeamDetailsActivity : BaseActivity() {
    private lateinit var binding: ActivityTeamDetailsBinding
    private val viewModel: TeamDetailsViewModel by viewModel()
    private val detailsAdapter = TeamDetailsAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeamDetailsBinding.inflate(layoutInflater)
        binding.activity = this
        binding.playersRecycler.adapter = detailsAdapter
        val view = binding.root
        setContentView(view)
        fetchData()
        observeData()
        setupUI()
    }

    private fun observeData() {
        viewModel.players.observe(this, teamDetailsObserver)
    }

    private val teamDetailsObserver = Observer<ArrayList<Player>> {
        binding.layoutDetailsHolder.visibility = View.VISIBLE
        hideLoadingDialog()
        if(it!=null)
        detailsAdapter.insertPlayers(it)

    }

    private fun fetchData() {
        showLoadingDialog()
        viewModel.getSingleTeamDetails(
            intent.getIntExtra(MATCH, 0),
            intent.getIntExtra(TEAM_ID, 0),
            intent.getIntExtra(TEAM_NUM, 0)
        )

    }

    private fun setupUI() {
        binding.layoutDetailsHolder.visibility = View.INVISIBLE
        binding.teamName.text = intent.getStringExtra(TEAM_NAME).toString()

    }

}
