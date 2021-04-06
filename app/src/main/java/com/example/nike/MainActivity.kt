package com.example.nike

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nike.features.teams.SearchTeamsFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        addFragment(savedInstanceState)
    }

    private fun addFragment(savedInstanceState: Bundle?) {
        savedInstanceState ?: supportFragmentManager.beginTransaction().add(
            R.id.fragmentContainer,
            SearchTeamsFragment.newInstance()
        ).commit()
    }

}