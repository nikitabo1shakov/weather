package com.nikitabolshakov.weather.presentation.view.activity.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.nikitabolshakov.weather.databinding.ActivitySplashBinding
import com.nikitabolshakov.weather.presentation.view.activity.main.MainActivity

class SplashActivity : AppCompatActivity() {

    var handler = Handler()

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logoSplashActivity.animate().rotationBy(250f)
            .setInterpolator(LinearInterpolator()).duration = 10000

        handler.postDelayed({
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }, 7000)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }
}