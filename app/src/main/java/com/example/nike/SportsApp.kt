package com.example.nike

import android.app.Application
import com.example.nike.core.di.ServiceLocator
import com.example.nike.features.teams.TeamsRepository

class SportsApp: Application() {

    val teamsRepository: TeamsRepository = ServiceLocator.provideTeamsRepository(this)

    override fun onCreate() {
        super.onCreate()
    }
}