package com.korneysoft.rsshcool2021_android_task_4_db.data.room

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.korneysoft.rsshcool2021_android_task_4_db.data.EditDBInterface
import com.korneysoft.rsshcool2021_android_task_4_db.data.Item
import kotlinx.coroutines.flow.Flow

private const val TAG="T4-RoomRepository"

class RoomRepository(context: Context): EditDBInterface {

    val nameType = "Room"

    private val database: ItemDatabase = Room.databaseBuilder(
        context.applicationContext,
        ItemDatabase::class.java, DATABASE_NAME
    ).build()

    private val dao: RoomItemDao = database.itemDao()

    fun getItems(): Flow<List<Item>> = dao.getItems()
    fun getItem(id:Int): LiveData<Item?> = dao.getItem(id)

    override fun add(item: Item)  {
        dao.insert(item)
    }

//     private fun insert(item:Item){
//        dao.insert(item)
//    }

    override suspend fun delete(item: Item)  {
        dao.delete(item)
        //Log.d(TAG,"deleteItem")
    }

    override fun update(item: Item)  {
        dao.update(item)
        //Log.d(TAG,"deleteItem")
    }
}