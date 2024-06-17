package com.myapp.application.newsreader.ui.activity

import android.app.UiModeManager
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.myapp.application.newsreader.R
import com.myapp.application.newsreader.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.newsNavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        navController.popBackStack(R.id.newsNavHostFragment, false)
        binding.bottomNavigationView.setupWithNavController(navController)
        val uiManager: UiModeManager = this.getSystemService(Context.UI_MODE_SERVICE) as UiModeManager
        when (this.resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
            Configuration.UI_MODE_NIGHT_YES -> {
                binding.bottomNavigationView.setBackgroundColor(ContextCompat.getColor(this,R.color.pink))
                binding.switchButton.text = getString(R.string.dark_mode)
                binding.switchButton.isChecked = true
            }
            Configuration.UI_MODE_NIGHT_NO -> {
                binding.bottomNavigationView.setBackgroundColor(ContextCompat.getColor(this,R.color.blue))
                binding.switchButton.text = getString(R.string.standard)
                binding.switchButton.isChecked = false
            }
            Configuration.UI_MODE_NIGHT_UNDEFINED -> {
                Log.e("fdshfdhh", "undefined")
            }
        }

        binding.switchButton.setOnCheckedChangeListener { buttonView, isChecked ->

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    if (isChecked)
                    {
                        uiManager.setApplicationNightMode(UiModeManager.MODE_NIGHT_YES)
                        binding.switchButton.text = getString(R.string.dark_mode)
                        binding.switchButton.setTextColor(ContextCompat.getColor(this,R.color.white))
                        binding.bottomNavigationView.setBackgroundColor(ContextCompat.getColor(this,R.color.pink))
                    }
                    else
                    {
                        uiManager.setApplicationNightMode(UiModeManager.MODE_NIGHT_NO)
                        binding.switchButton.text = getString(R.string.standard)
                        binding.switchButton.setTextColor(ContextCompat.getColor(this,R.color.black))
                        binding.bottomNavigationView.setBackgroundColor(ContextCompat.getColor(this,R.color.blue))
                    }
                }
        }
    }
}