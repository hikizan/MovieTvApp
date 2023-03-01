package com.hikizan.movietvapp.presentation.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.hikizan.movietvapp.R
import com.hikizan.movietvapp.core.base.HikizanActivity
import com.hikizan.movietvapp.core.utils.ext.showToast
import com.hikizan.movietvapp.databinding.ActivityMainBinding
import com.hikizan.movietvapp.presentation.main.adapter.SectionPagerAdapter

class MainActivity : HikizanActivity() {

    companion object {
        fun start(context: Context) {
            context.startActivity(
                Intent(context, MainActivity::class.java)
            )
        }
    }

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
                try {
                    val moveToFavoritePage = Intent()
                    moveToFavoritePage.setClassName(this@MainActivity, "com.hikizan.movietvapp.favorite.presentation.favorite.FavoriteActivity")
                    startActivity(moveToFavoritePage)
                } catch (e: Exception) {
                    this@MainActivity.showToast(e.toString())
                }

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