package com.nikitabolshakov.weather.presentation.view.activity.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.nikitabolshakov.weather.R
import com.nikitabolshakov.weather.application.App
import com.nikitabolshakov.weather.databinding.ActivityMainBinding
import com.nikitabolshakov.weather.utils.cicerone.IScreenOpener
import com.nikitabolshakov.weather.utils.ui.showToast
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screenOpener: IScreenOpener

    private val navigator = AppNavigator(this, R.id.container)

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.appInstance?.appComponent?.inject(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            router.replaceScreen(screenOpener.openHomeFragment())
        }

        showToast(getString(R.string.version_app))

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.e(
                    "ActivityFIREBASEMSG",
                    "Fetching FCM registration token failed",
                    task.exception
                )
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            Log.e("ActivityFIREBASEMSG", token!!)
        })

        with(binding) {
            bottomNavigationView.setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.bnv_home -> {
                        router.navigateTo(screenOpener.openHomeFragment())
                        true
                    }
                    R.id.bnv_history -> {
                        router.navigateTo(screenOpener.openHistoryFragment())
                        true
                    }
                    R.id.bnv_google_maps -> {
                        router.navigateTo(screenOpener.openGoogleMapsFragment())
                        true
                    }
                    else -> {
                        router.navigateTo(screenOpener.openHomeFragment())
                        true
                    }
                }
            }

            bottomNavigationView.setOnNavigationItemReselectedListener { item ->
                when (item.itemId) {
                    R.id.bnv_home -> {
                        router.navigateTo(screenOpener.openHomeFragment())
                    }
                    R.id.bnv_history -> {
                        router.navigateTo(screenOpener.openHistoryFragment())
                    }
                    R.id.bnv_google_maps -> {
                        router.navigateTo(screenOpener.openGoogleMapsFragment())
                    }
                    else -> {
                        router.navigateTo(screenOpener.openHomeFragment())
                    }
                }
            }
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }
}