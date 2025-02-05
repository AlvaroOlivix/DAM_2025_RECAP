package com.dam.dam_2025_recap.core.extensions

import android.widget.ImageView
import coil3.load
import coil3.request.crossfade

fun ImageView.loadUrl(imageUrl:String){

    this.load(imageUrl) {
        crossfade(true)
        crossfade(500)
    }


}