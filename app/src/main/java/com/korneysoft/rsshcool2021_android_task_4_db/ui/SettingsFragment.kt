package com.korneysoft.rsshcool2021_android_task_4_db.ui

import android.os.Bundle
import android.view.View
import androidx.preference.PreferenceFragmentCompat
import com.korneysoft.rsshcool2021_android_task_4_db.R
import com.korneysoft.rsshcool2021_android_task_4_db.ui.interfaces.ToolbarUpdateInterface

class SettingsFragment : PreferenceFragmentCompat() {
    private val fragmentName by lazy { getString(R.string.preference_title) }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
        //       addPreferencesFromResource(R.xml.root_preferences)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar()
    }


    private fun setToolbar() {
        activity.let {
            if (it is ToolbarUpdateInterface) {
                it.apply {
                    setToolbarTitle(fragmentName)
                    setToolbarHamburgerButton(
                        R.drawable.ic_baseline_arrow_back_24,
                        { closeFragment() })
                    setToolBarMenu(0,0, emptyArray())
                }
            }
        }
    }

    private fun closeFragment() {
        activity?.onBackPressed()
    }


    companion object {
        @JvmStatic
        fun newInstance() = SettingsFragment()
    }

}