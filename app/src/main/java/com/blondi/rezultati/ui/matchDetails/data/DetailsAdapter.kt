package com.blondi.rezultati.ui.matchDetails.data

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
import com.blondi.rezultati.databinding.ItemSportBinding

class DetailsAdapter() :
    RecyclerView.Adapter<DetailsAdapter.EventViewHolder>() {
    private val eventList: ArrayList<Event> = arrayListOf()
    private val playerList: ArrayList<Player> = arrayListOf()
    private val clubList: ArrayList<Team> = arrayListOf()


    fun insertEvents(eventList: ArrayList<Event>) {
        this.eventList.clear()
        this.eventList.addAll(eventList)
        notifyDataSetChanged()
    }

    fun insertPlayers(playerList: ArrayList<Player>) {
        this.playerList.addAll(playerList)
    }
    fun insertClub(club: Team) {
        this.clubList.add(club)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding: ItemEventBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_event,
            parent,
            false
        )
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(eventList[position])
    }

    override fun getItemCount() = eventList.size

    inner class EventViewHolder(val binding: ItemEventBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(event: Event) {
            binding.setTime(calculateTime(event.time))
            binding.setPart("(Part ${event.interval}) --")
            binding.setEventType(event.type?.let { findEventType(it) })
            binding.setPlayerName(findPlayerName(event.player_id))
            binding.setClub(findClubName(event.team_id))
        }
    }

    private fun findClubName(teamId: Int?): String? {
        for( i in 0 until clubList.size){
            if(teamId==clubList[i].id){
                return clubList[i].name
            }
        }
        return "Unknown"
    }

    private fun findPlayerName(playerId: Int?): String? {
        for (i in 0 until playerList.size) {
            if (playerId == playerList[i].id)
                return playerList[i].name
        }
        return "No name"
    }

    private fun findEventType(type: String): String? {
        when (type) {
            "red_card" -> {
                return "Red Card"
            }
            "yellow_card" -> {
                return "Yellow Card"
            }
            "penalty_kick" -> {
                return "Penalty kick"
            }
            else -> return "Goal"
        }

    }

    private fun calculateTime(time: Long?) = (time?.div(100000)).toString()+" '"


}