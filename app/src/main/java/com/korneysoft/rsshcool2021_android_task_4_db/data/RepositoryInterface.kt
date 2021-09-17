package com.korneysoft.rsshcool2021_android_task_4_db.data

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

interface RepositoryInterface {
   val nameType:String

   fun getItems(): LiveData<List<Item>>

   suspend fun setSort(isSorted:Boolean, sortField:String, isDesc: Boolean)

   suspend fun add(item: Item)
   suspend fun delete(item: Item)
   suspend fun update(item: Item)

   fun close()

}