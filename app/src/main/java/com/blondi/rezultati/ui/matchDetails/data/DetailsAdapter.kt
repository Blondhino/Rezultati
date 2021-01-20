package com.blondi.rezultati.ui.matchDetails.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.blondi.rezultati.R
import com.blondi.rezultati.common.model.Event
import com.blondi.rezultati.common.model.Player
import com.blondi.rezultati.common.model.Sport
import com.blondi.rezultati.databinding.ItemEventBinding
import com.blondi.rezultati.databinding.ItemSportBinding

class DetailsAdapter() :
    RecyclerView.Adapter<DetailsAdapter.EventViewHolder>() {
    private val eventList: ArrayList<Event> = arrayListOf()
    private val playerList: ArrayList<Player> = arrayListOf()


    fun insertEvents(eventList: ArrayList<Event>) {
        this.eventList.clear()
        this.eventList.addAll(eventList)
        notifyDataSetChanged()
    }
    fun insertPlayers(playerList: ArrayList<Player>) {
        this.playerList.clear()
        this.playerList.addAll(playerList)
        notifyDataSetChanged()
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
            binding.setTime("35 '")
            binding.setPart("Part 1")
            binding.setEventType("Red Card")
            binding.setPlayerName("Ivo Ivić")
            binding.setClub("Hajduk")
        }
    }


}