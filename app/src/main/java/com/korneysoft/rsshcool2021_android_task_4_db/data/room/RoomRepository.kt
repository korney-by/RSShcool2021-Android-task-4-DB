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

class RoomRepository(context: Context) : RepositoryInterface {
    override val nameType = "Room"

    private var sortField: Int = 0
    private var isSorted: Boolean = false

    private var counterChangeDataBase = 0
    private val updateChangeDataBaseCounter = MutableLiveData<Int>()

    init {
        updateChangeDataBaseCounter.value = 0
    }

    private fun onChangeData() {
        updateChangeDataBaseCounter.postValue(++counterChangeDataBase)
    }

    private val database: ItemDatabase = Room.databaseBuilder(
        context.applicationContext,
        ItemDatabase::class.java, DATABASE_NAME
    ).build()

    private val itemListFromDB: LiveData<List<Item>> = updateChangeDataBaseCounter.map {
        Log.d(TAG, "Get Items ")

        runBlocking {
            val deferredItems = this.async {
                if (isSorted) {
                    dao.getItemsSorted(sortField)
                } else {
                    dao.getItems()
                }
            }
            deferredItems.await()
        }
    }

    private val dao: RoomItemDao = database.itemDao()


    override fun getItems(): Flow<List<Item>> {
        return itemListFromDB.asFlow()
    }

//    override fun getItems(): Flow<List<Item>> {
//        Log.d(TAG, "Get Items ")
//        return dao.getItems()
//    }

    override fun getItem(id: Int): LiveData<Item?> = dao.getItem(id)

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
        //Log.d(TAG,"updateItem")
    }

    override fun close() {
        database.close()
    }

    fun getNumOfSort(sortField: String): Int {
        return when (sortField.uppercase()) {
            "NAME" -> 1
            "AGE" -> 2
            "BREED" -> 3
            else -> 0
        }
    }

    override suspend fun setSort(isSorted: Boolean, sortField: String) {
        this.isSorted = isSorted
        this.sortField = getNumOfSort(sortField)
        onChangeData()

//        if (((this.isSorted == isSorted) && (this.sortField != sortField)) ||
//            (this.isSorted != isSorted)
//        ) {
//            this.isSorted = isSorted
//            this.sortField = sortField
//            dao.re
//        }
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