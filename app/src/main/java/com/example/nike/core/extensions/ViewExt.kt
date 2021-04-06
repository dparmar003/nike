package com.example.nike.core.extensions

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("app:searchTeamBadge")
fun ImageView.setSearchTeamBadge(url: String?) =
    url?.let {
        Glide.with(this.context.applicationContext)
            .load(url)
            .into(this)
    }

