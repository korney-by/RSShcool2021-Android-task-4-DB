package com.korneysoft.rsshcool2021_android_task_4_db.data.sqlite

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import androidx.lifecycle.LiveData
import com.korneysoft.rsshcool2021_android_task_4_db.data.Item
import com.korneysoft.rsshcool2021_android_task_4_db.data.RepositoryInterface

class SQLiteRepository(val context: Context) : RepositoryInterface {
    override val nameType = "SQLiteOpenHelper"

    private var dao: SQLiteDao = getDao()

    override fun getItems() = dao.getItems()
//    val getItems: Flow<List<Item>>
//    get()= dao.getItems()
//       // .combine() - сортировка
//        .flowOn(Dispatchers.Default)
//        .conflate()

//    override fun open() {
//        if (!dao.readableDatabase.isOpen()){
//            dao=getDao()
//        }
//    }

//    override fun close() {
//        dao.close()
//    }

    private fun getDao(): SQLiteDao {
        return SQLiteDao(context)
    }

    override fun getItem(id: Int): LiveData<Item?> = dao.getItem(id)

    override suspend fun setSort(isSorted: Boolean, sortField: String) {
        dao.setSort(isSorted, sortField)
    }

    override suspend fun add(item: Item) = dao.add(item)
    override suspend fun delete(item: Item) = dao.delete(item)
    override suspend fun update(item: Item) = dao.update(item)


}