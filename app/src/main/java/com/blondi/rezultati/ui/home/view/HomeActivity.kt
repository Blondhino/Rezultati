package com.blondi.rezultati.ui.home.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.GravityCompat.START
import androidx.lifecycle.Observer
import com.blondi.rezultati.R
import com.blondi.rezultati.common.model.MatchModel
import com.blondi.rezultati.common.model.Sport
import com.blondi.rezultati.databinding.ActivityHomeBinding
import com.blondi.rezultati.ui.base.BaseActivity
import com.blondi.rezultati.ui.home.data.HomeViewModel
import com.blondi.rezultati.ui.home.data.MatchAdapter
import com.blondi.rezultati.ui.home.data.SportsAdapter
import com.blondi.rezultati.ui.matchDetails.view.MatchDetailsActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity(), MatchAdapter.OnMatchItemClickListener,
    SportsAdapter.OnSportItemClickListener {
    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModel()
    private val matchAdapter = MatchAdapter(this)
    private val sportsAdapter = SportsAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        binding.activity = this
        binding.matchRecycler.adapter = matchAdapter
        binding.sportsRecycler.adapter=sportsAdapter
        val view = binding.root
        setContentView(view)
        setupUI()
        fetchData()
        observeData()
    }

    private fun fetchData() {
        showLoadingDialog()
        viewModel.fetchMatches(1)
    }

    private fun setupUI() {
        setupMenu()
    }

    private fun setupMenu() {
        viewModel.fetchSports()
    }

    private fun observeData() {
        viewModel.matches.observe(this, matchesObserver)
        viewModel.sports.observe(this,sportsObserver)
    }


    private val matchesObserver = Observer<ArrayList<MatchModel>> {
        matchAdapter.insertData(it)
        binding.toolbarTitle.text = it[0].sport?.name
        hideLoadingDialog()
    }

    private val sportsObserver = Observer<ArrayList<Sport>>{
        sportsAdapter.insertData(it)
    }

    fun showMenu() {
        binding.sportsMenuDrawer.bringToFront()
        binding.drawerLayout.openDrawer(START)
    }


    override fun onMatchClick(matchId: Int) {
        val intent = Intent(this, MatchDetailsActivity::class.java)
        intent.putExtra(MATCH_ID, matchId)
        startActivity(intent)
    }

    companion object {
        const val MATCH_ID = "matchId"
    }

    override fun onSportClick(matchId: Int) {

    }

}
