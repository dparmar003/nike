package com.example.nike.features.teams

import com.example.nike.core.exceptions.Result
import com.example.nike.core.interactors.UseCase

class SearchTeams(private val teamsRepository: TeamsRepository): UseCase<String, List<Team>>(){

    override suspend fun run(params: String): Result<List<Team>> {
        return teamsRepository.searchTeamByName(teamName = params)
    }

}