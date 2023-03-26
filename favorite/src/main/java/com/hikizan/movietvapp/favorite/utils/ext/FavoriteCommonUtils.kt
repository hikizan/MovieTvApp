package com.hikizan.movietvapp.favorite.utils.ext

import android.graphics.Color
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.hikizan.movietvapp.core.databinding.LayoutToolbarBinding
import com.hikizan.movietvapp.core.utils.ext.orEmptyString

fun AppCompatActivity.setupBaseToolbar(
    toolbarLayout: LayoutToolbarBinding,
    title: String,
    isChild: Boolean = false,
    isFavoriteVisible: Boolean = false
) {
    with(toolbarLayout) {
        setSupportActionBar(toolbar)
        supportActionBar?.title = title.orEmptyString()
        supportActionBar?.setDisplayHomeAsUpEnabled(isChild)
        toolbar.setTitleTextColor(Color.WHITE)
        if (isFavoriteVisible) {
            imgFavorite.visibility = View.VISIBLE
        }
    }
}