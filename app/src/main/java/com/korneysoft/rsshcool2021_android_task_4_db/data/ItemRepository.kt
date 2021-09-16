package com.korneysoft.rsshcool2021_android_task_4_db.data

import android.content.Context
import androidx.annotation.WorkerThread
import androidx.preference.PreferenceManager
import com.korneysoft.rsshcool2021_android_task_4_db.R
import com.korneysoft.rsshcool2021_android_task_4_db.data.room.RoomRepository
import com.korneysoft.rsshcool2021_android_task_4_db.data.sqlite.SQLiteRepository
import kotlinx.coroutines.flow.Flow
import java.lang.Thread.sleep


class ItemRepository private constructor(val context: Context, daoKey: String) {

    //private val db  = NoDBRepository(10)
    //private val db = SQLiteRepository(context)
    //private var _db = RoomRepository(context)

    private var currentDaoKey = ""

    private var db = getRepository(daoKey)

    val dbTypeName get() = db.nameType ?: ""

    fun setActualRepository() {
        val newDaoKey = getDaoKeyFromPreference(context)
        if (newDaoKey != currentDaoKey) {
            //db.close()
            db = getRepository(newDaoKey)
            //db.open()
        }
    }

    private fun getRepository(newDaoKey: String): RepositoryInterface {
        val idDaoCursor = context.resources.getString(R.string.key_dao_cursor)
        val idDaoRoom = context.resources.getString(R.string.key_dao_room)
        if (newDaoKey != currentDaoKey) {
            return when (newDaoKey) {
                idDaoCursor -> {
                    currentDaoKey = newDaoKey
                    SQLiteRepository(context)
                }
                idDaoRoom -> {
                    currentDaoKey = newDaoKey
                    RoomRepository(context)
                }
                else -> this.db
            }
        }
        return this.db
    }



    @WorkerThread
    fun getItems() = db.getItems()

    @WorkerThread
    fun getItem(id: Int) = db.getItem(id)

    @WorkerThread
    suspend fun addItem(item: Item) = db.add(item)

    @WorkerThread
    suspend
    fun deleteItem(item: Item) = db.delete(item)

    @WorkerThread
    suspend fun updateItem(item: Item) = db.update(item)

    @WorkerThread
    suspend fun setSort(isSorted:Boolean, sortField:String){
        db.setSort(isSorted,sortField)
    }

    companion object {

        private var INSTANCE: ItemRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = ItemRepository(context, getDaoKeyFromPreference(context))
            }
        }

        fun get(): ItemRepository {
            return INSTANCE ?: throw IllegalStateException("ItemListRepository must be initialised")
        }

        private fun getDaoKeyFromPreference(context: Context): String {
            val prefs = PreferenceManager.getDefaultSharedPreferences(context)
            //val defaultValue=context.findPreference(context,context.resources.getString(R.string.dao_key),)
            val allPossibleDao = context.resources.getStringArray(R.array.dao_values)
            return prefs.getString(
                context.resources.getString(R.string.dao_key),
                allPossibleDao[0]
            )
                .toString()
        }
    }
}