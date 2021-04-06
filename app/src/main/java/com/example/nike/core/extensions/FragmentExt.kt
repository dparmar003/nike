package com.example.nike.core.extensions

import androidx.fragment.app.Fragment
import com.example.nike.SportsApp
import com.example.nike.core.di.ViewModelFactory

fun Fragment.getViewModelFactory(): ViewModelFactory{
    val repository = (requireContext().applicationContext as SportsApp).teamsRepository
    return ViewModelFactory(repository)
}


