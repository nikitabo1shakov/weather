package com.nikitabolshakov.weather.presentation.view.activity.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.nikitabolshakov.weather.R
import com.nikitabolshakov.weather.databinding.ActivityMainBinding
import com.nikitabolshakov.weather.presentation.view.fragment.history.HistoryFragment
import com.nikitabolshakov.weather.presentation.view.fragment.citylist.CityListFragment
import com.nikitabolshakov.weather.presentation.view.fragment.googlemaps.GoogleMapsFragment
import com.nikitabolshakov.weather.utils.showToast

class MainActivity : AppCompatActivity() {

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

        showToast(getString(R.string.version_app))

        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.bnv_city_list -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container_main_activity, CityListFragment())
                        .addToBackStack("")
                        .commitAllowingStateLoss()
                    true
                }
                R.id.bnv_history -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container_main_activity, HistoryFragment())
                        .addToBackStack("")
                        .commitAllowingStateLoss()
                    true
                }
                R.id.bnv_google_maps -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container_main_activity, GoogleMapsFragment())
                        .addToBackStack("")
                        .commitAllowingStateLoss()
                    true
                }
                else -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container_main_activity, CityListFragment())
                        .addToBackStack("")
                        .commitAllowingStateLoss()
                    true
                }
            }
        }

        binding.bottomNavigationView.setOnNavigationItemReselectedListener { item ->
            when(item.itemId) {
                R.id.bnv_city_list -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container_main_activity, CityListFragment())
                        .addToBackStack("")
                        .commitAllowingStateLoss()
                }
                R.id.bnv_history -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container_main_activity, HistoryFragment())
                        .addToBackStack("")
                        .commitAllowingStateLoss()
                }
                R.id.bnv_google_maps -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container_main_activity, GoogleMapsFragment())
                        .addToBackStack("")
                        .commitAllowingStateLoss()
                }
                else -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container_main_activity, CityListFragment())
                        .addToBackStack("")
                        .commitAllowingStateLoss()
                }
            }
        }
    }
}