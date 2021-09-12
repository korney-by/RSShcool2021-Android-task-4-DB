package com.korneysoft.rsshcool2021_android_task_4_db.data

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

interface GetDataDBInterface {
    abstract fun getItems(): Flow<List<Item>>
    abstract fun getItem(id:Int): LiveData<Item?>
}