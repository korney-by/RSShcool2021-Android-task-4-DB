package com.korneysoft.rsshcool2021_android_task_4_db

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel

private const val TAG="Task4-ListViewModel"

class ListViewModel(application: Application) : AndroidViewModel(application) {
    init{
        Log.d(TAG,"ListViewModel instance created")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG,"ListViewModel instance about to be  destroyed")
    }
}