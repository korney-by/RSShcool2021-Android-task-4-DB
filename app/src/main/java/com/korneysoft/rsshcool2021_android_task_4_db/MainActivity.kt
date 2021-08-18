package com.korneysoft.rsshcool2021_android_task_4_db

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.korneysoft.rsshcool2021_android_task_4_db.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding=ActivityMainBinding.inflate(layoutInflater)


    }
}