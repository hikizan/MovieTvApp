package com.hikizan.movietvapp.core.utils.ext

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.hikizan.movietvapp.core.R
import com.hikizan.movietvapp.core.base.HikizanActivity
import com.hikizan.movietvapp.core.databinding.LayoutToolbarBinding
import com.kennyc.view.MultiStateView

fun HikizanActivity.setupHikizanToolbar(
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

fun MultiStateView.showLoadingState() {
    viewState = MultiStateView.ViewState.LOADING
}

fun MultiStateView.showEmptyState() {
    viewState = MultiStateView.ViewState.EMPTY
}

fun MultiStateView.showErrorState(
    message: String? = null,
    action: Pair<String, (() -> Unit)>? = null
) {
    viewState = MultiStateView.ViewState.ERROR
    val view = getView(MultiStateView.ViewState.ERROR)

    message?.let {
        val tvError = view?.findViewById<TextView>(R.id.tvError)
        if (message.isNotEmpty()) {
            tvError?.text = message
        }
    }

    action?.let { pair ->
        val btnError = view?.findViewById<Button>(R.id.btnRetry)
        btnError?.text = pair.first

        btnError?.setOnClickListener { pair.second.invoke() }
    }
}

fun Context.showToast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()