package com.example.nike.core.di

import android.content.Context
import com.example.nike.core.platform.NetworkHandler
import com.example.nike.features.teams.TeamsRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceLocator {
    private var okHttpClient: OkHttpClient? = null
    private var teamsRepository: TeamsRepository? = null

    private fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.thesportsdb.com/api/v1/json/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient ?: createClient())
            .build()
    }

    private fun createClient(): OkHttpClient {
        val clientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        clientBuilder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
        val result = clientBuilder.build()
        okHttpClient = result
        return result
    }

    fun provideTeamsRepository(context: Context): TeamsRepository {
        synchronized(this){
            return teamsRepository ?: createTeamsRepository(context)
        }
    }

    private fun createTeamsRepository(context: Context): TeamsRepository {
        val result = TeamsRepository.RemoteDataSource(NetworkHandler(context), provideRetrofit())
        teamsRepository = result
        return result
    }
}