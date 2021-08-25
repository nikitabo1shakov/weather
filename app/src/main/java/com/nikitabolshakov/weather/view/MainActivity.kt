package com.nikitabolshakov.weather.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.nikitabolshakov.weather.R
import com.nikitabolshakov.weather.databinding.ActivityMainBinding
import com.nikitabolshakov.weather.view.contactlist.ContactListFragment
import com.nikitabolshakov.weather.view.history.HistoryFragment
import com.nikitabolshakov.weather.view.main.MainFragment
import com.nikitabolshakov.weather.view.maps.MapsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.container.id, MainFragment.newInstance())
                .commitNow()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_action, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_history -> {
                supportFragmentManager.apply {
                    beginTransaction()
                        .replace(R.id.container, HistoryFragment.newInstance())
                        .addToBackStack("")
                        .commitAllowingStateLoss()
                }
                true
            }
            R.id.menu_contact_list -> {
                supportFragmentManager.apply {
                    beginTransaction()
                        .replace(R.id.container, ContactListFragment.newInstance())
                        .addToBackStack("")
                        .commitAllowingStateLoss()
                }
                true
            }
            R.id.menu_maps -> {
                supportFragmentManager.apply {
                    beginTransaction()
                        .replace(R.id.container, MapsFragment.newInstance())
                        .commitNow()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}