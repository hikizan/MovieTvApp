package com.hikizan.movietvapp.base

import androidx.fragment.app.Fragment
import com.hikizan.movietvapp.utils.reusable.CustomHikizanLoading

abstract class HikizanFragment : Fragment() {

    private val loading: CustomHikizanLoading by lazy {
        CustomHikizanLoading(requireContext())
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
    abstract fun initProcess()
    abstract fun initObservers()
}