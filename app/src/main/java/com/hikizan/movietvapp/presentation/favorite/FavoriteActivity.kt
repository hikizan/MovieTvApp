package com.hikizan.movietvapp.presentation.favorite

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.hikizan.movietvapp.R.string
import com.hikizan.movietvapp.base.HikizanActivity
import com.hikizan.movietvapp.databinding.ActivityFavoriteBinding
import com.hikizan.movietvapp.presentation.favorite.adapter.FavoriteViewPagerAdapter
import com.hikizan.movietvapp.utils.ext.setupHikizanToolbar

class FavoriteActivity : HikizanActivity() {

    companion object {

        fun start(context: Context) {
            context.startActivity(
                Intent(context, FavoriteActivity::class.java)
            )
        }
    }

    private var _binding: ActivityFavoriteBinding? = null
    private val binding get() = _binding

    private val viewPagerAdapter: FavoriteViewPagerAdapter by lazy {
        FavoriteViewPagerAdapter(
            supportFragmentManager,
            lifecycle
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        initUI()
        initAction()
    }

    override fun initIntent() {
    }

    override fun initUI() {
        binding?.apply {
            setupHikizanToolbar(
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}