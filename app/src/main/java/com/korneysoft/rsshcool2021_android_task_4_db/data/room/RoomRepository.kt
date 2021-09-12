package com.korneysoft.rsshcool2021_android_task_4_db.data.room

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.korneysoft.rsshcool2021_android_task_4_db.data.EditDBInterface
import com.korneysoft.rsshcool2021_android_task_4_db.data.GetDataDBInterface
import com.korneysoft.rsshcool2021_android_task_4_db.data.Item
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

private const val TAG = "T4-RoomRepository"

class RoomRepository(context: Context, scope: CoroutineScope) : EditDBInterface,
    GetDataDBInterface {

    val nameType = "Room"

    private val database: ItemDatabase = Room.databaseBuilder(
        context.applicationContext,
        ItemDatabase::class.java, DATABASE_NAME
    ).build()
    private val dao: RoomItemDao = database.itemDao()

    override fun getItems(): Flow<List<Item>> = dao.getItems()
    override fun getItem(id: Int): LiveData<Item?> = dao.getItem(id)

    override suspend fun add(item: Item) = dao.insert(item)
    override suspend fun delete(item: Item) {
        dao.delete(item)
        //Log.d(TAG,"deleteItem")
    }

    override suspend fun update(item: Item) {
        dao.update(item)
        //Log.d(TAG,"deleteItem")
    }
}