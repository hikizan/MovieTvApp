package com.hikizan.movietvapp.base

import androidx.appcompat.app.AppCompatActivity
import com.hikizan.movietvapp.utils.reusable.CustomHikizanLoading

abstract class HikizanActivity : AppCompatActivity() {

    private val loading: CustomHikizanLoading by lazy {
        CustomHikizanLoading(this)
    }

    protected fun showLoading() {
        loading.show()
    }

    protected fun hideLoading() {
        if (loading.isShowing) {
            loading.cancel()
        }
    }

    abstract fun initIntent()
    abstract fun initUI()
    abstract fun initAction()
    abstract fun initProcess()
    abstract fun initObservers()
}