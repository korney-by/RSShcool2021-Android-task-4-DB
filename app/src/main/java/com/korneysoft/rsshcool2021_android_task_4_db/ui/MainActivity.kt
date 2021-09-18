package com.korneysoft.rsshcool2021_android_task_4_db.ui

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.preference.PreferenceManager
import com.korneysoft.rsshcool2021_android_task_4_db.R
import com.korneysoft.rsshcool2021_android_task_4_db.data.Item
import com.korneysoft.rsshcool2021_android_task_4_db.databinding.ActivityMainBinding
import com.korneysoft.rsshcool2021_android_task_4_db.ui.interfaces.KeyboardControlInterface
import com.korneysoft.rsshcool2021_android_task_4_db.ui.interfaces.SetPreferencesInterface
import com.korneysoft.rsshcool2021_android_task_4_db.ui.interfaces.ShowFragmentAddItemInterface
import com.korneysoft.rsshcool2021_android_task_4_db.ui.interfaces.ToolbarUpdateInterface
import com.korneysoft.rsshcool2021_android_task_4_db.viewmodel.ItemViewModel
import kotlin.math.min

private const val TAG = "T4-MainActivity"

class MainActivity : AppCompatActivity(), ShowFragmentAddItemInterface, KeyboardControlInterface,
    ToolbarUpdateInterface, SetPreferencesInterface {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemListViewModel = ViewModelProviders.of(this).get(ItemViewModel::class.java)

        loadItemListFragment()
    }


    private fun loadItemListFragment() {
        val fragment: Fragment = ItemListFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .commit()
    }

    private fun loadItemDetalsFragment(item: Item?) {
        val fragment: Fragment = ItemDetailsFragment.newInstance(item)
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragmentContainerView, fragment)
            .commit()
    }

    private fun loadSettingsFragment() {
        val fragment: Fragment = SettingsFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragmentContainerView, fragment)
            .commit()
    }

    override fun openItemDetailsFragment(item: Item?) {
        loadItemDetalsFragment(item)
    }

    override fun openSettingsFragment() {
        loadSettingsFragment()
    }

    override fun hideKeyboard() {
        hideKeyboard(this)
    }

    private fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        var view: View? = activity.currentFocus
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun setToolbarHamburgerButton(iconResource: Int, action: () -> Unit) {
        binding.toolbar.apply {
            if (iconResource == 0) {
                navigationIcon = null
            } else {
                navigationIcon = AppCompatResources.getDrawable(context, iconResource)
                setNavigationOnClickListener { action() }
            }
        }
    }

    override fun setToolbarTitle(title: String, subtitle: String) {
        binding.toolbar.title = title
        binding.toolbar.subtitle = if (!subtitle.isBlank()) {
            "DAO: $subtitle"
        } else {
            ""
        }
    }

    override fun setToolBarMenu(
        menuResource: Int,
        resourceIconSort: Int,
        actions: Array<() -> Unit>
    ) {
        //var action: MenuItem.OnMenuItemClickListener
        binding.toolbar.apply {
            menu.clear()
            if (menuResource != 0) {
                menuInflater.inflate(menuResource, menu)

                // set icons to menu items
                if (menu.size() > 0) {
                    menu.getItem(0).isVisible = (resourceIconSort > 0)
                    if (resourceIconSort > 0) {
                        menu.getItem(0).icon =
                            AppCompatResources.getDrawable(context, resourceIconSort)
                    }
                }

                // set actions to menu items
                for (i in 0 until min(menu.size(), actions.size)) {
                    //menu.getItem(i).setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener {
                    menu.getItem(i).setOnMenuItemClickListener {
                        actions[i]()
                        true
                    }
                }
            }
        }
    }

    override fun setPreferences() {
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val viewModel = ViewModelProviders.of(this).get(ItemViewModel::class.java)
        viewModel.setActualRepository()

        prefs.getString(this.resources.getString(R.string.sort_field_key), "")
            ?.let { sortField ->
                viewModel.setSort(
                    prefs.getBoolean(this.resources.getString(R.string.sort_key), false),
                    sortField,
                    viewModel.sortIsDesc
                )

            }

    }
}
