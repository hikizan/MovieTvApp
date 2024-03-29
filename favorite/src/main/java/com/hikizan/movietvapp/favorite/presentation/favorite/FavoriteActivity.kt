package com.hikizan.movietvapp.favorite.presentation.favorite

import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.hikizan.movietvapp.favorite.utils.ext.setupFavoriteToolbar
import com.hikizan.movietvapp.favorite.R.string
import com.hikizan.movietvapp.favorite.base.FavoriteBaseActivity
import com.hikizan.movietvapp.favorite.databinding.ActivityFavoriteBinding
import com.hikizan.movietvapp.favorite.presentation.favorite.adapter.FavoriteViewPagerAdapter

class FavoriteActivity : FavoriteBaseActivity<ActivityFavoriteBinding>() {


    private val viewPagerAdapter: FavoriteViewPagerAdapter by lazy {
        FavoriteViewPagerAdapter(
            supportFragmentManager,
            lifecycle
        )
    }

    override fun initViewBinding(): ActivityFavoriteBinding {
        return ActivityFavoriteBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initUI()
        initAction()
    }

    override fun initIntent() {
    }

    override fun initUI() {
        binding?.apply {
            setupFavoriteToolbar(
                toolbarLayout = layoutToolbarFavorite,
                title = getString(string.title_favorite_list_page),
                isChild = true
            )

            val tabTitles = listOf(
                getString(string.title_movie),
                getString(string.title_tv)
            )

            vpFavorite.apply {
                adapter = viewPagerAdapter
            }

            TabLayoutMediator(tabFavorite, vpFavorite) { tab, position ->
                tab.text = tabTitles[position]
            }.attach()
        }
    }

    override fun initAction() {
    }

    override fun initProcess() {
    }

    override fun initObservers() {
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}