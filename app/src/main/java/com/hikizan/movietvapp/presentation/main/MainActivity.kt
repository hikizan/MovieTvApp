package com.hikizan.movietvapp.presentation.main

import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.hikizan.movietvapp.R
import com.hikizan.movietvapp.base.HikizanActivity
import com.hikizan.movietvapp.databinding.ActivityMainBinding
import com.hikizan.movietvapp.presentation.favorite.FavoriteActivity
import com.hikizan.movietvapp.presentation.main.adapter.SectionPagerAdapter

class MainActivity : HikizanActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding

    private val viewPagerAdapter: SectionPagerAdapter by lazy {
        SectionPagerAdapter(
            supportFragmentManager,
            lifecycle
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        initUI()
        initAction()
    }

    override fun initIntent() {
    }

    override fun initUI() {
        binding?.apply {
            val tabTitles = listOf(
                getString(R.string.title_movie),
                getString(R.string.title_tv)
            )

            vpMain.apply {
                adapter = viewPagerAdapter
            }

            TabLayoutMediator(tabMain, vpMain) { tab, position ->
                tab.text = tabTitles[position]
            }.attach()
        }
    }

    override fun initAction() {
        binding?.apply {
            fabFavorite.setOnClickListener {
                FavoriteActivity.start(this@MainActivity)
            }
        }
    }

    override fun initProcess() {
    }

    override fun initObservers() {
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}