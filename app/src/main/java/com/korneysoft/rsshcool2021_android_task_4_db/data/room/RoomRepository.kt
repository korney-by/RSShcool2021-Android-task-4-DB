package com.korneysoft.rsshcool2021_android_task_4_db.data.room

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.room.Room
import com.korneysoft.rsshcool2021_android_task_4_db.data.Item
import com.korneysoft.rsshcool2021_android_task_4_db.data.RepositoryInterface
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

private const val TAG = "T4-RoomRepository"

class RoomRepository(val context: Context) : RepositoryInterface {
    override val nameType = "Room"

    private var numOfSort: Int = -1

    private var counterChangeDataBase = 0
    private val updateChangeDataBaseCounter = MutableLiveData<Int>()

    private var database: ItemDatabase = getDao()

    private fun onChangeData() {
        updateChangeDataBaseCounter.postValue(++counterChangeDataBase)
    }

    override fun close() {
        database.close()
    }

    private fun getDao(): ItemDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            ItemDatabase::class.java, DATABASE_NAME
        ).build()
    }

    private val itemListFromDB: LiveData<List<Item>> = updateChangeDataBaseCounter.map {
        // run getting List<Item> in the coroutine
        runBlocking {
            val deferredItems = this.async {
                when {
                    numOfSort == 0 -> {
                        dao.getItems()
                    }
                    numOfSort < 4 -> { // numOfSort in 1..3
                        dao.getItemsSorted(numOfSort)
                    }
                    else -> { // numOfSort in 4..6
                        dao.getItemsSortedDesc(numOfSort)
                    }
                }
            }
            deferredItems.await()
        }
    }

    private val dao: RoomItemDao = database.itemDao()

    override fun getItems() = itemListFromDB

    override suspend fun add(item: Item) {
        dao.insert(item)
        onChangeData()
    }

    override suspend fun delete(item: Item) {
        dao.delete(item)
        onChangeData()
        Log.d(TAG, "delete Item")
    }

    override suspend fun update(item: Item) {
        dao.update(item)
        onChangeData()
    }

    private fun getNumOfSort(isSorted: Boolean, sortField: String, isDesc: Boolean): Int {
        if (!isSorted) return 0
        var result = when (sortField.uppercase()) {
            COLUMN_NAME -> 1
            COLUMN_AGE -> 2
            COLUMN_BREED -> 3
            else -> 0
        }
        if (result > 0 && isDesc) result += 3
        return result
    }

    override suspend fun setSort(isSorted: Boolean, sortField: String, isDesc: Boolean) {
        val newSort = getNumOfSort(isSorted, sortField, isDesc)
        if (numOfSort != newSort) {
            numOfSort = newSort
            onChangeData()
        }
    }
}