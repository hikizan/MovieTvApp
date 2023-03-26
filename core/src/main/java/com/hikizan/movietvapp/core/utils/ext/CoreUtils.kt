package com.hikizan.movietvapp.core.utils.ext

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hikizan.movietvapp.core.databinding.LayoutToolbarBinding
import com.kennyc.view.MultiStateView

fun AppCompatActivity.setupHikizanToolbar(
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

fun MultiStateView.showDefaultState() {
    viewState = MultiStateView.ViewState.CONTENT
}

fun MultiStateView.showEmptyState() {
    viewState = MultiStateView.ViewState.EMPTY
}

fun Context.showToast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()