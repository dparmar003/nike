package com.example.nike.core.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nike.features.teams.SearchTeams
import com.example.nike.features.teams.TeamsRepository
import com.example.nike.features.teams.TeamsViewModel
import java.lang.Exception

@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(
    private val teamsRepository: TeamsRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        with(modelClass){
            when{
                isAssignableFrom(TeamsViewModel::class.java) ->
                    TeamsViewModel(SearchTeams(teamsRepository))
                else -> Exception("Undefined ViewModel Type")
            }

    } as T
}