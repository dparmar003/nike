package com.example.nike.features.teams

import com.example.nike.core.extensions.empty
import com.google.gson.annotations.SerializedName

data class TeamEntity(

    @SerializedName("idTeam") var idTeam : String? = null,
    @SerializedName("strTeam") var strTeam: String? = null,
    @SerializedName("strLeague") var strLeague : String? = null,
    @SerializedName( "strTeamBadge") var strTeamBadge : String? = null

){
    companion object{
        fun empty() = TeamEntity(String.empty(), String.empty(), String.empty(),String.empty())
    }

    fun toTeam() = Team(idTeam, strTeam, strLeague, strTeamBadge)
}
