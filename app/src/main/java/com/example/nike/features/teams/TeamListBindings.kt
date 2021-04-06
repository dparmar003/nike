package com.example.nike.features.teams

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("app:teams")
fun setTeams(recyclerView: RecyclerView, list: List<Team>?){
    (recyclerView.adapter as TeamsAdapter).submitList(list)
}