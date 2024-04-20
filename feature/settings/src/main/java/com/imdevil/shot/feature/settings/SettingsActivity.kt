package com.imdevil.shot.feature.settings

import android.os.Bundle
import androidx.navigation.createGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.fragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.imdevil.shot.feature.common.base.BaseActivity
import com.imdevil.shot.feature.settings.cookie.AddCookiesFragment
import com.imdevil.shot.feature.settings.databinding.ActivitySettingsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsActivity : BaseActivity<ActivitySettingsBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createViewBinding(ActivitySettingsBinding.inflate(layoutInflater))
        setSupportActionBar(binding.toolbar)

        val startDestination = intent?.extras?.getString("start_destination") ?: "main_settings"

        // workaround for findNavController in activity
        // when use FragmentContainerView as a fragment container
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
        val navController = navHostFragment!!.navController
        //

        navController.graph = navController.createGraph(startDestination) {
            fragment<SettingsFragment>("main_settings") {
                label = resources.getString(R.string.app_settings)
            }
            fragment<AddCookiesFragment>("add_cookies") {
                label = resources.getString(R.string.add_cookies)
            }
        }

        val appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(),
            fallbackOnNavigateUpListener = ::onSupportNavigateUp,
        )
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)

        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}
