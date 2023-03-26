package com.hikizan.movietvapp.favorite.utils.ext

import android.graphics.Color
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.hikizan.movietvapp.core.utils.ext.orEmptyString
import com.hikizan.movietvapp.favorite.databinding.FavoriteLayoutToolbarBinding

fun AppCompatActivity.setupFavoriteToolbar(
    toolbarLayout: FavoriteLayoutToolbarBinding,
    title: String,
    isChild: Boolean = false,
    isFavoriteVisible: Boolean = false
) {
    with(toolbarLayout) {
        setSupportActionBar(favoriteToolbar)
        supportActionBar?.title = title.orEmptyString()
        supportActionBar?.setDisplayHomeAsUpEnabled(isChild)
        favoriteToolbar.setTitleTextColor(Color.WHITE)
        if (isFavoriteVisible) {
            imgFavorite.visibility = View.VISIBLE
        }
    }
}