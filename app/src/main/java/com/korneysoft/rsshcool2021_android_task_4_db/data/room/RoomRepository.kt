package com.korneysoft.rsshcool2021_android_task_4_db.data.room

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.korneysoft.rsshcool2021_android_task_4_db.data.Item
import com.korneysoft.rsshcool2021_android_task_4_db.data.RepositoryInterface
import kotlinx.coroutines.flow.Flow

private const val TAG = "T4-RoomRepository"

class RoomRepository(context: Context) : RepositoryInterface {
    override val nameType = "Room"

    private var sortField: String = ""
    private var isSorted: Boolean = false

    private val database: ItemDatabase = Room.databaseBuilder(
        context.applicationContext,
        ItemDatabase::class.java, DATABASE_NAME
    ).build()

    private val dao: RoomItemDao = database.itemDao()

    //override fun getItems(): Flow<List<Item>> = dao.getItems()

    override fun getItems(): Flow<List<Item>> {
        return dao.getItemsSorted(sortField.uppercase())
        //   return dao.getItems()

//        return if (isSorted) {
//            Log.d(TAG,"getItems - Sorted")
//            dao.getItemsSorted(sortField.uppercase())
//        } else {
//            Log.d(TAG,"getItems - NO Sorted")
//            dao.getItems()
//        }
    }

    override fun getItem(id: Int): LiveData<Item?> = dao.getItem(id)

    override suspend fun add(item: Item) = dao.insert(item)

    override suspend fun delete(item: Item) {
        dao.delete(item)
        Log.d(TAG,"delete Item")
    }

    override suspend fun update(item: Item) {
        dao.update(item)
        //Log.d(TAG,"updateItem")
    }

    override fun close() {
        database.close()
    }

    suspend fun updateAfterSort() {
        delete(Item(-1))
    }

    override suspend fun setSort(isSorted: Boolean, sortField: String) {
        this.isSorted = isSorted
        this.sortField = sortField
        updateAfterSort()

//        if (((this.isSorted == isSorted) && (this.sortField != sortField)) ||
//            (this.isSorted != isSorted)
//        ) {
//            this.isSorted = isSorted
//            this.sortField = sortField
//            dao.re
//        }
    }
}