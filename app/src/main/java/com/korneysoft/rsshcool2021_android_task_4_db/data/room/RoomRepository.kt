package com.korneysoft.rsshcool2021_android_task_4_db.data.room

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.map
import androidx.room.Room
import com.korneysoft.rsshcool2021_android_task_4_db.data.Item
import com.korneysoft.rsshcool2021_android_task_4_db.data.RepositoryInterface
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking

private const val TAG = "T4-RoomRepository"

class RoomRepository(val context: Context) : RepositoryInterface {
    override val nameType = "Room"

    private var numOfSort: Int = 0

    private var counterChangeDataBase = 0
    private val updateChangeDataBaseCounter = MutableLiveData<Int>()

    private var database: ItemDatabase = getDao()

    init {
        updateChangeDataBaseCounter.value
    }

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
        Log.d(TAG, "Get Items ")

        // run getting List<Item> in the couroutine
        runBlocking {
            val deferredItems = this.async {
                if (numOfSort == 0) {
                    dao.getItems()
                } else if (numOfSort < 4) {         //numOfSort in 1..3
                    dao.getItemsSorted(numOfSort)
                } else {                            // numOfSort in 4..6
                    dao.getItemsSortedDesc(numOfSort)
                }
            }
            deferredItems.await()
        }
    }

    private val dao: RoomItemDao = database.itemDao()

    override fun getItems() = itemListFromDB//.asFlow()

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

    fun getNumOfSort(isSorted: Boolean, sortField: String, isDesc: Boolean): Int {
        if (!isSorted) return 0
        var result = when (sortField.uppercase()) {
            "NAME" -> 1
            "AGE" -> 2
            "BREED" -> 3
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


//combine(
//listFlow.debounce(250),
//stateFlow.debounce(250),
//searchFlow.debounce(250)
//.distinctUntilChanged()
//// не делать запрос короче 3 символов
//.filter { it.isBlank() || it.length > searchLength },
//::updateList
//)