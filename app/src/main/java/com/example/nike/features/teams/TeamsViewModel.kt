package com.example.nike.features.teams

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.example.nike.core.exceptions.Result
import com.example.nike.core.extensions.empty
import com.example.nike.core.platform.BaseViewModel
import kotlinx.coroutines.launch

class TeamsViewModel(private val searchTeams: SearchTeams): BaseViewModel() {
    private var _snackbarText = MutableLiveData<String>()
    val snackbarText: LiveData<String> = _snackbarText

    private var _teams = MutableLiveData<List<Team>>()
    val teams: LiveData<List<Team>> = _teams

    private var _searchedTeam = MutableLiveData(String.empty())
    val searchedTeam: LiveData<String> = _searchedTeam

    val empty: LiveData<Boolean> = Transformations.map(teams){
        it.isEmpty()
    }

    init {
        // default loading for demo
        searchTeamByName("Manchester")
    }

    fun searchTeamByName(team: String?) {
        viewModelScope.launch {
            when (val tasks = searchTeams(team!!)) {
                is Result.Success -> {
                    _teams.value = tasks.data!!
                    _searchedTeam.value = team
                }
                is Result.Failure -> {
                    _teams.value = emptyList()
                    _snackbarText.value = "Try again"
                    handleFailure(tasks)
                }
                is Result.Exception -> {
                    _teams.value = emptyList()
                    _snackbarText.value = "Try again"
                }
            }
        }

    }

    fun teamDetails(teamId: String?) {
        _snackbarText.value = "not yet implemented"
    }
}