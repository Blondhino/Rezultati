package com.blondi.rezultati.ui.home.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.blondi.rezultati.R
import com.blondi.rezultati.common.model.MatchModel
import com.blondi.rezultati.databinding.ItemMatchBinding

class MatchAdapter(val listener: OnMatchItemClickListener) :
    RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {
    private val matchList: ArrayList<MatchModel> = arrayListOf()


    fun insertData(matchList: ArrayList<MatchModel>) {
        this.matchList.clear()
        this.matchList.addAll(matchList)
        notifyDataSetChanged()
    }

    fun clearData() {
        this.matchList.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val binding: ItemMatchBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_match,
            parent,
            false
        )
        return MatchViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bind(matchList[position])
    }

    override fun getItemCount() = matchList.size

    inner class MatchViewHolder(private val binding: ItemMatchBinding, listener: OnMatchItemClickListener) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(match: MatchModel) {
            binding.title = "${match.teamOne?.name} - ${match.teamTwo?.name}"
            binding.root.setOnClickListener {
                match.id?.let { matchId ->
                    listener.onMatchClick(
                        matchId
                    )
                }
            }
        }
    }

    interface OnMatchItemClickListener {
        fun onMatchClick(matchId: Int)
    }
}