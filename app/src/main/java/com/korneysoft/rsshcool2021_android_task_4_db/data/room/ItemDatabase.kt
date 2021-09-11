package com.korneysoft.rsshcool2021_android_task_4_db.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.korneysoft.rsshcool2021_android_task_4_db.data.Item

@Database(entities = [Item::class], version = DATABASE_VERSION)
abstract class ItemDatabase:RoomDatabase() {
    abstract fun itemDao(): RoomItemDao
}