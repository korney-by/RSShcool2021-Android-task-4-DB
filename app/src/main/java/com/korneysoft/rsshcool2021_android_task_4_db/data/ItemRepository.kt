package com.korneysoft.rsshcool2021_android_task_4_db.data

import android.content.Context
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.korneysoft.rsshcool2021_android_task_4_db.data.room.RoomRepository
import com.korneysoft.rsshcool2021_android_task_4_db.data.sqlite.SQLiteRepository
import kotlinx.coroutines.flow.Flow
import java.lang.IllegalStateException


class ItemRepository private constructor(context: Context) {

    //private val db  = NoDBData(7)
    private val db = SQLiteRepository(context)
    //private val db = RoomRepository(context)

    val dbTypeName get() = db.nameType
    //var allItems: Flow<List<Item>> = db.getItems()


    @WorkerThread
    fun getItems() = db.getItems()

    @WorkerThread
    fun getItem(id: Int) = db.getItem(id)

     fun  onChangeItems(){

       //  allItems = db.getItems()

        //allItems.value=allItems.value
    }

    @WorkerThread
    suspend fun addItem(item: Item) {
        if (db is EditDBInterface) {
            db.add(item)
            onChangeItems()
        }
    }

    @WorkerThread
    suspend
    fun deleteItem(item: Item) {
        if (db is EditDBInterface) {
            db.delete(item)
            onChangeItems()
        }
    }

    @WorkerThread
    suspend fun updateItem(item: Item) {
        if (db is EditDBInterface) {
            db.update(item)
            onChangeItems()
        }
    }

    companion object {
        private var INSTANCE: ItemRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = ItemRepository(context)
            }
        }

        fun get(): ItemRepository {
            return INSTANCE ?: throw IllegalStateException("ItemListRepository must be initialised")
        }
    }
}