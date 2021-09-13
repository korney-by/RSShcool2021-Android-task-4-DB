package com.korneysoft.rsshcool2021_android_task_4_db.data

import android.content.Context
import android.content.res.Resources
import androidx.annotation.WorkerThread
import androidx.preference.PreferenceManager
import com.korneysoft.rsshcool2021_android_task_4_db.R
import com.korneysoft.rsshcool2021_android_task_4_db.data.room.RoomRepository
import com.korneysoft.rsshcool2021_android_task_4_db.data.sqlite.SQLiteRepository


class ItemRepository private constructor(val context: Context) {

    //private val db  = NoDBRepository(10)
    //private val db = SQLiteRepository(context)
    //private var db = RoomRepository(context)

    private var db: RepositoryInterface? = null
    private var currentTypeDao = ""

    val dbTypeName get() = db?.nameType ?: ""
    //var allItems: Flow<List<Item>> = db.getItems()

    private fun setRepository() {
        val idDaoCursor = Resources.getSystem().getString(R.string.id_dao_cursor)
        val idDaoRoom = Resources.getSystem().getString(R.string.id_dao_room)
        val newTypeDao = getTypeDaoFromPreference()
        if (newTypeDao != currentTypeDao) {
            when (newTypeDao) {
                idDaoCursor -> {
                    db = SQLiteRepository(context)
                    currentTypeDao = newTypeDao
                }
                idDaoRoom -> {
                    db = RoomRepository(context)
                    this.currentTypeDao = newTypeDao
                }
                else -> {
                    db = null
                    currentTypeDao = ""
                }
            }
        }
    }

    private fun getTypeDaoFromPreference(): String {
        var prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val arrDao = Resources.getSystem().getStringArray(R.array.dao_values)

        return prefs.getString("dao_key", arrDao[0]).toString()
    }


    @WorkerThread
    fun getItems() = db?.getItems()

    @WorkerThread
    fun getItem(id: Int) = db?.getItem(id)


    @WorkerThread
    suspend fun addItem(item: Item) {
        if (db is RepositoryInterface) {
            db?.add(item)
        }
    }

    @WorkerThread
    suspend
    fun deleteItem(item: Item) {
        if (db is RepositoryInterface) {
            db?.delete(item)
        }
    }

    @WorkerThread
    suspend fun updateItem(item: Item) {
        if (db is RepositoryInterface) {
            db?.update(item)

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
            INSTANCE?.let {
                it.setRepository()
                return it
            }
            throw IllegalStateException("ItemListRepository must be initialised")
        }


    }
}