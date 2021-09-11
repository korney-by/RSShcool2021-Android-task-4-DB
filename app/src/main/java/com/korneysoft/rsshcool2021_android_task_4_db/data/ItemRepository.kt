package com.korneysoft.rsshcool2021_android_task_4_db.data

import android.content.Context
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.korneysoft.rsshcool2021_android_task_4_db.data.room.RoomRepository
import com.korneysoft.rsshcool2021_android_task_4_db.data.sqlite.SQLiteRepository
import java.lang.IllegalStateException


class ItemRepository private constructor(context: Context) {

    //private val db  = NoDBData(7)
    private val db = SQLiteRepository(context)
    //private val db = RoomRepository(context)

    val dbTypeName get() = db.nameType
    val itemsChangeLiveData = MutableLiveData<Boolean>()

    @WorkerThread
    fun getItems() = db.getItems()

    @WorkerThread
    fun getItem(id: Int) = db.getItem(id)

    fun onChangeItems(){
        //itemsChangeLiveData.value=!itemsChangeLiveData.value
    }

    @WorkerThread
    fun addItem(item: Item) {
        if (db is EditDBInterface) {
            db.add(item)
            onChangeItems()
        }
    }

    @WorkerThread
    fun deleteItem(item: Item) {
        if (db is EditDBInterface) {
            db.delete(item)
            onChangeItems()
        }
    }

    @WorkerThread
    fun updateItem(item: Item) {
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