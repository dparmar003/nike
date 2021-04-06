package com.example.nike.core.extensions

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import androidx.annotation.RequiresApi

val Context.networkInfo: Network? @RequiresApi(Build.VERSION_CODES.M) get() =
    (this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetwork
