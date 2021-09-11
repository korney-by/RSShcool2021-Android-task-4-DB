package com.korneysoft.rsshcool2021_android_task_4_db.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.korneysoft.rsshcool2021_android_task_4_db.R
import com.korneysoft.rsshcool2021_android_task_4_db.data.ItemRepository
import com.korneysoft.rsshcool2021_android_task_4_db.databinding.ActivityMainBinding
import com.korneysoft.rsshcool2021_android_task_4_db.viewmodel.ItemViewModel

private const val TAG = "T4-MainActivity"

class MainActivity : AppCompatActivity(),ShowFragmentAddItemInterface {
    private lateinit var binding: ActivityMainBinding
    private val repository= ItemRepository.get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemListViewModel = ViewModelProviders.of(this).get(ItemViewModel::class.java)

        binding.toolbar.title = "DB-${repository.dbTypeName}"

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
}