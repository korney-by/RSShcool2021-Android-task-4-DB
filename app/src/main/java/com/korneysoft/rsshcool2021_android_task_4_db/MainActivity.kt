package com.korneysoft.rsshcool2021_android_task_4_db

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.*
import com.korneysoft.rsshcool2021_android_task_4_db.databinding.ActivityMainBinding

private const val TAG="Task4-MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        val provider : ViewModelProvider = ViewModelProviders.of(this)
        val listViewModel=provider.get(ListViewModel::class.java)
        Log.d(TAG,"Got a ListViewModel: $listViewModel")

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)


    }
}