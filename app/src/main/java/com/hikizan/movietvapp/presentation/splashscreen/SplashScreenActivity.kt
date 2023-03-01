package com.hikizan.movietvapp.presentation.splashscreen

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.hikizan.movietvapp.R
import com.hikizan.movietvapp.presentation.main.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private val delayTime: Long = 2500L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler(Looper.getMainLooper()).postDelayed({
            MainActivity.start(this@SplashScreenActivity)
            finish()
        }, delayTime)
    }
}