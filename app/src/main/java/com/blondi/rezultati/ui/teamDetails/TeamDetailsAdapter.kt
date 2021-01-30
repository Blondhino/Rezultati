package com.blondi.rezultati.ui.teamDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.blondi.rezultati.R
import com.blondi.rezultati.common.model.Event
import com.blondi.rezultati.common.model.Player
import com.blondi.rezultati.common.model.Sport
import com.blondi.rezultati.common.model.Team
import com.blondi.rezultati.databinding.ItemEventBinding
import com.blondi.rezultati.databinding.ItemPlayerBinding
import com.blondi.rezultati.databinding.ItemSportBinding

class TeamDetailsAdapter() :
    RecyclerView.Adapter<TeamDetailsAdapter.TeamDetailsViewHolder>() {
    private val playerList: ArrayList<Player> = arrayListOf()

    fun insertPlayers(playerList: ArrayList<Player>) {
        this.playerList.addAll(playerList)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamDetailsViewHolder {
        val binding: ItemPlayerBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_player,
            parent,
            false
        )
        return TeamDetailsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeamDetailsViewHolder, position: Int) {
        holder.bind(playerList[position])
    }

    override fun getItemCount() = playerList.size

    inner class TeamDetailsViewHolder(val binding: ItemPlayerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(player: Player) {
            binding.setPlayerName(player.name)
        }
    }



}