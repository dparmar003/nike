package com.example.nike.features.teams

import com.example.nike.core.extensions.empty

data class Team(
    var idTeam: String? = null,
    var strTeam: String? = null,
    var strLeague: String? = null,
    var strTeamBadge: String? = null
){
    companion object{
        fun empty() = Team(String.empty(), String.empty(), String.empty(),String.empty())
    }
}
