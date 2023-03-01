package com.hikizan.movietvapp.utils.ext

import android.content.Context
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.hikizan.movietvapp.core.R
import com.kennyc.view.MultiStateView

fun MultiStateView.showDefaultState() {
    viewState = MultiStateView.ViewState.CONTENT
}

fun MultiStateView.showLoadingState() {
    viewState = MultiStateView.ViewState.LOADING
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