package com.korneysoft.rsshcool2021_android_task_4_db.data.room

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room

class RoomRepository(context: Context) {

    private val db: ItemDatabase = Room.databaseBuilder(
        context.applicationContext,
        ItemDatabase::class.java, DATABASE_NAME
    ).build()

    private val dao: ItemDao = db.ItemDao()

    fun getItems():LiveData<List<Item>> = dao.getItems()
    fun getItem(id:Int): LiveData<Item?> = dao.getItem(id)
}