package com.example.nike.features.teams

import com.example.nike.core.exceptions.Result
import com.example.nike.core.platform.NetworkHandler
import retrofit2.Call
import retrofit2.Retrofit

interface TeamsRepository {

    fun searchTeamByName(teamName: String): Result<List<Team>>

    class RemoteDataSource(
        private val networkHandler: NetworkHandler,
        private val retrofit: Retrofit
    ) : TeamsRepository {

        private val teamsApi: TeamsApi by lazy { retrofit.create(TeamsApi::class.java) }


        override fun searchTeamByName(teamName: String): Result<List<Team>>{
            return when(networkHandler.isConnected) {
                true -> {
                    request(
                        teamsApi.searchTeamsByName(teamName),
                        { it.teams.map {
                                teamEntity -> teamEntity.toTeam()
                        } },
                        BaseTeamsResponse(emptyList())
                    )
                }
                false -> { Result.Failure("No Network")}
            }
        }

        private fun <T , R> request(call: Call<T>, transform: (T) -> R, default: T): Result<R> {
            return try {
                val response = call.execute()
                when(response.isSuccessful){
                    true -> { Result.Success(transform(response.body() ?: default)) }
                    false -> { Result.Failure("Server Error") }
                }
            } catch (e: Exception) {
                Result.Exception(Exception("Runtime: ${e.message}"))
            }
        }

    }
}