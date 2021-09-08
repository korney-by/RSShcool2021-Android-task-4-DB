package com.korneysoft.rsshcool2021_android_task_4_db.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.korneysoft.rsshcool2021_android_task_4_db.R
import com.korneysoft.rsshcool2021_android_task_4_db.databinding.ActivityMainBinding
import com.korneysoft.rsshcool2021_android_task_4_db.viewmodel.ItemListViewModel

private const val TAG = "T4-MainActivity"

class MainActivity : AppCompatActivity(),AddOpenFragmentInterface {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemListViewModel = ViewModelProviders.of(this).get(ItemListViewModel::class.java)

        binding.toolbar.title = "DB-${itemListViewModel.dbTypeName}"

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