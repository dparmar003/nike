package com.example.nike.core.platform

import android.content.Context
import com.example.nike.core.extensions.networkInfo

class NetworkHandler(private val context: Context) {

    val isConnected: Boolean get() = context.networkInfo != null
}