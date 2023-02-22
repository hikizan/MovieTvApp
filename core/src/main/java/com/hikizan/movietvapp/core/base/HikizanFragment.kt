package com.hikizan.movietvapp.core.base

import androidx.fragment.app.Fragment
import com.hikizan.movietvapp.core.utils.reusable.CustomHikizanLoading

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
    abstract fun initAction()
    abstract fun initProcess()
    abstract fun initObservers()
}