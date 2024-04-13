package com.imdevil.shot.feature.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat

internal class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.settings)
    }
}