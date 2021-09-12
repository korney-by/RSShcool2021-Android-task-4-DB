package com.korneysoft.rsshcool2021_android_task_4_db.ui

import android.app.Activity
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.korneysoft.rsshcool2021_android_task_4_db.R
import com.korneysoft.rsshcool2021_android_task_4_db.data.ItemRepository
import com.korneysoft.rsshcool2021_android_task_4_db.databinding.ActivityMainBinding
import com.korneysoft.rsshcool2021_android_task_4_db.ui.interfaces.KeyboardInterface
import com.korneysoft.rsshcool2021_android_task_4_db.ui.interfaces.ShowFragmentAddItemInterface
import com.korneysoft.rsshcool2021_android_task_4_db.ui.interfaces.ToolbarUpdateItnerface
import com.korneysoft.rsshcool2021_android_task_4_db.viewmodel.ItemViewModel

private const val TAG = "T4-MainActivity"

class MainActivity : AppCompatActivity(), ShowFragmentAddItemInterface, KeyboardInterface,
    ToolbarUpdateItnerface {
    private lateinit var binding: ActivityMainBinding
    private val repository = ItemRepository.get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemListViewModel = ViewModelProviders.of(this).get(ItemViewModel::class.java)

        binding.toolbar.subtitle = "${repository.dbTypeName}"

        loadItemListFragment()
    }


    private fun loadItemListFragment() {
        val fragment: Fragment = ItemListFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .commit()
    }

    private fun loadAddItemFragment() {
        val fragment: Fragment = AddItemFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragmentContainerView, fragment)
            .commit()
    }

    override fun openAddItemFragment() {
        loadAddItemFragment()
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


    override fun setToolBarSettings(
        fragmentName: String,
        menuResource: Int,
        menuIconResource: Int,
        iconResource: Int,
        action: () -> Unit
    ) {
        binding.toolbar.apply {
            title = fragmentName
            if (menuResource == 0) {
                menu.clear()
            } else {
                //menu.add(getItem(menuResource)
                menu.getItem(0).icon = getDrawable(menuIconResource)
            }
            //menu.getItem(0).icon = null//getDrawable(menuResource);
            navigationIcon = getDrawable(iconResource)
            setNavigationOnClickListener { action() }
        }
    }
}