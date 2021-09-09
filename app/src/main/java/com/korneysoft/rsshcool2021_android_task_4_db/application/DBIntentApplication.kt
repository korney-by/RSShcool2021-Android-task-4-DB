package com.korneysoft.rsshcool2021_android_task_4_db.application

import android.app.Application
import com.korneysoft.rsshcool2021_android_task_4_db.data.ItemListRepository

class DBIntentApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        ItemListRepository.initialize(this)
    }
}