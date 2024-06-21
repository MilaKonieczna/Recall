package com.example.recall.results

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recall.R

class Adapter(private val list: List<DataGames>) :
    RecyclerView.Adapter<Adapter.UpcomingViewHolder>() {


    inner class UpcomingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_past_games, parent, false)
        return UpcomingViewHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: UpcomingViewHolder, position: Int) {
          }



    override fun getItemCount(): Int {
        return list.size
    }


}