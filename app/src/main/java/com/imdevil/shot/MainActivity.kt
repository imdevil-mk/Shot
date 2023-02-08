package com.imdevil.shot

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.imdevil.shot.common.ui.mainviewpager.LOCAL_FRAGMENT_INDEX
import com.imdevil.shot.common.ui.mainviewpager.NETEASE_FRAGMENT_INDEX
import com.imdevil.shot.common.ui.mainviewpager.QQ_FRAGMENT_INDEX
import com.imdevil.shot.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}