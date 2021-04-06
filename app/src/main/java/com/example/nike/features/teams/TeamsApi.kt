package com.example.nike.features.teams

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TeamsApi {

    companion object{
        private const val FAVOURITE_TEAMS = "searchloves.php"
        private const val SEARCH_TEAMS = "searchteams.php"
        private const val LAST_FIVE_EVENTS = "eventslast.php"
    }

    @GET(FAVOURITE_TEAMS) fun favouriteTeamsByUser(@Query("u") userName: String)
    @GET(SEARCH_TEAMS) fun searchTeamsByName(@Query("t") teamName: String) : Call<BaseTeamsResponse>
    @GET(LAST_FIVE_EVENTS) fun lastEventsByTeamId(@Query("id") teamId: String)
}