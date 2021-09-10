package com.korneysoft.rsshcool2021_android_task_4_db.data.room

import androidx.room.Dao
import androidx.room.Query

@Dao
interface ItemDao {
    @Query(SQL_GET_ALL)
    fun getItems():List<Item>

    @Query(SQL_GET_ONE)
    fun getItem(id:Int):Item?
}