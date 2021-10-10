package com.nikitabolshakov.weather.presentation.view.activity.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.nikitabolshakov.weather.R
import com.nikitabolshakov.weather.databinding.ActivityMainBinding
import com.nikitabolshakov.weather.presentation.view.fragment.citylist.CityListFragment
import com.nikitabolshakov.weather.presentation.utils.BottomNavigationViewMenuOpener
import com.nikitabolshakov.weather.presentation.utils.showToast

class MainActivity : AppCompatActivity() {

    private val bnvMenuOpener = BottomNavigationViewMenuOpener(supportFragmentManager)

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.containerMainActivity.id, CityListFragment())
                .commitNow()
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
                    R.id.bnv_city_list -> {
                        bnvMenuOpener.openCityListFragment()
                        true
                    }
                    R.id.bnv_history -> {
                        bnvMenuOpener.openHistoryFragment()
                        true
                    }
                    R.id.bnv_google_maps -> {
                        bnvMenuOpener.openGoogleMapsFragment()
                        true
                    }
                    else -> {
                        bnvMenuOpener.openCityListFragment()
                        true
                    }
                }
            }

            bottomNavigationView.setOnNavigationItemReselectedListener { item ->
                when (item.itemId) {
                    R.id.bnv_city_list -> {
                        bnvMenuOpener.openCityListFragment()
                    }
                    R.id.bnv_history -> {
                        bnvMenuOpener.openHistoryFragment()
                    }
                    R.id.bnv_google_maps -> {
                        bnvMenuOpener.openGoogleMapsFragment()
                    }
                    else -> {
                        bnvMenuOpener.openCityListFragment()
                    }
                }
            }
        }
    }
}