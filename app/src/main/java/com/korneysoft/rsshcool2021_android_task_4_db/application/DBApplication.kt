package com.korneysoft.rsshcool2021_android_task_4_db.application

import android.app.Application
import android.util.Log
import androidx.preference.PreferenceManager
import com.korneysoft.rsshcool2021_android_task_4_db.R
import com.korneysoft.rsshcool2021_android_task_4_db.data.ItemRepository
import java.util.*

class DBApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        ItemRepository.initialize(this)
    }

    override fun onTerminate() {
        super.onTerminate()
        //ItemRepository.
    }

}