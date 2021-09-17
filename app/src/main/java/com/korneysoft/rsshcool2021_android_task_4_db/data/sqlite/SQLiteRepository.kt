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

    override fun close() {
        dao.close()
    }

    private fun getDao(): SQLiteDao {
        return SQLiteDao(context)
    }


    override suspend fun setSort(isSorted: Boolean, sortField: String, isDesc: Boolean) {
        dao.setSort(isSorted, sortField, isDesc)
    }

    override suspend fun add(item: Item) = dao.add(item)
    override suspend fun delete(item: Item) = dao.delete(item)
    override suspend fun update(item: Item) = dao.update(item)


}