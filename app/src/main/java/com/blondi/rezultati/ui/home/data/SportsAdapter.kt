package com.blondi.rezultati.ui.home.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.blondi.rezultati.R
import com.blondi.rezultati.common.model.Sport
import com.blondi.rezultati.databinding.ItemSportBinding

class SportsAdapter(val listener: OnSportItemClickListener) :
    RecyclerView.Adapter<SportsAdapter.SportViewHolder>() {
    private val sportLIst: ArrayList<Sport> = arrayListOf()


    fun insertData(sportList: ArrayList<Sport>) {
        this.sportLIst.clear()
        this.sportLIst.addAll(sportList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportViewHolder {
        val binding: ItemSportBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_sport,
            parent,
            false
        )
        return SportViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: SportViewHolder, position: Int) {
        holder.bind(sportLIst[position])
    }

    override fun getItemCount() = sportLIst.size

    inner class SportViewHolder(val binding: ItemSportBinding, listener: OnSportItemClickListener) :
        RecyclerView.ViewHolder(binding.root) {
        init {

        }

        fun bind(sport: Sport) {
            binding.name = sport.name
            binding.root.setOnClickListener {
                sport.id?.let { sportId ->
                    sport.name?.let { sportName ->
                        listener.onSportClick(
                            sportId,
                            sportName
                        )
                    }
                }
            }
        }
    }

    interface OnSportItemClickListener {
        fun onSportClick(sportId: Int, sportName: String)
    }
}