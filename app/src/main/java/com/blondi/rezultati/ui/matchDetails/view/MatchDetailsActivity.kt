package com.blondi.rezultati.ui.matchDetails.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.blondi.rezultati.R
import com.blondi.rezultati.common.model.MatchModel
import com.blondi.rezultati.common.repo.TeamDetailsRepo
import com.blondi.rezultati.databinding.ActivityMatchDetailsBinding
import com.blondi.rezultati.ui.base.BaseActivity
import com.blondi.rezultati.ui.home.data.HomeViewModel
import com.blondi.rezultati.ui.home.data.MatchAdapter
import com.blondi.rezultati.ui.home.view.HomeActivity.Companion.MATCH_ID
import com.blondi.rezultati.ui.matchDetails.data.DetailsAdapter
import com.blondi.rezultati.ui.matchDetails.data.MatchDetailsViewModel
import com.blondi.rezultati.ui.teamDetails.TeamDetailsActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MatchDetailsActivity : BaseActivity() {
    private lateinit var binding: ActivityMatchDetailsBinding
    private val viewModel: MatchDetailsViewModel by viewModel()
    private val detailsAdapter = DetailsAdapter()
    private var team1Id =0
    private var team2Id =0
    private lateinit var match : MatchModel
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
        binding.layoutDetailsHolder.visibility=View.VISIBLE
        hideLoadingDialog()
        team1Id = it.team_one!!
        team2Id = it.team_two!!
        match = it
        binding.teamName1.text=it.teamOne?.name
        binding.teamName2.text=it.teamTwo?.name
        binding.result.text=it.score
        it.teamOne?.players?.let { playersTeamOne -> detailsAdapter.insertPlayers(playersTeamOne) }
        it.teamTwo?.players?.let { playersTeamTwo -> detailsAdapter.insertPlayers(playersTeamTwo) }
        it.teamOne?.let { teamOne -> detailsAdapter.insertClub(teamOne) }
        it.teamTwo?.let { teamTwo -> detailsAdapter.insertClub(teamTwo) }
        detailsAdapter.insertEvents(it.events)

    }

    private fun fetchData() {
        showLoadingDialog()
        viewModel.getSingleMatchDetails(intent.getIntExtra(MATCH_ID, 0))
    }

    private fun setupUI() {
        binding.layoutDetailsHolder.visibility=View.INVISIBLE
    }

    fun onTeam1Click(){
    showTeamDetails(binding.teamName1.text.toString(),team1Id,1)
    }

    fun onTeam2Click(){
        showTeamDetails(binding.teamName2.text.toString(),team2Id,2)
    }

    private fun showTeamDetails(teamName : String, teamId: Int, teamNum: Int){
       Log.d("TeamDetails","team name:  $teamName  team id: $teamId")
        val intent = Intent(this, TeamDetailsActivity::class.java)
        intent.putExtra(TEAM_NAME,teamName)
        intent.putExtra(TEAM_ID,teamId)
        intent.putExtra(TEAM_NUM,teamNum)
        intent.putExtra(MATCH,match.id)
        startActivity(intent)
    }

    companion object {
        const val TEAM_NAME = "teamName"
        const val TEAM_ID = "teamID"
        const val TEAM_NUM = "teamNum"
        const val MATCH = "matchNumber"
    }

}
