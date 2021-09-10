package com.korneysoft.rsshcool2021_android_task_4_db.data.room

import android.content.Context
import androidx.room.Room

class RoomRepository(context: Context) {

    private val db: ItemDatabase = Room.databaseBuilder(
        context.applicationContext,
        ItemDatabase::class.java, DATABASE_NAME
    ).build()

    private val dao: ItemDao = db.ItemDao()

    fun getItems():List<Item> = dao.getItems()
    fun getItem(id:Int):Item? = dao.getItem(id)
}