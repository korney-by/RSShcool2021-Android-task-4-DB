package com.korneysoft.rsshcool2021_android_task_4_db.data

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

interface RepositoryInterface {
   val nameType:String

   fun getItems(): LiveData<List<Item>>
   fun getItem(id:Int): LiveData<Item?>

   fun setSort(isSorted:Boolean, sortField:String)

   suspend fun add(item: Item)
   suspend fun delete(item: Item)
   suspend fun update(item: Item)

   fun close()

}