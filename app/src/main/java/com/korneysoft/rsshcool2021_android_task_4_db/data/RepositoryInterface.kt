package com.korneysoft.rsshcool2021_android_task_4_db.data

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

interface RepositoryInterface {
   val nameType:String

   fun getItems(): Flow<List<Item>>
   fun getItem(id:Int): LiveData<Item?>

   suspend fun add(item: Item)
   suspend fun delete(item: Item)
   suspend fun update(item: Item)
}