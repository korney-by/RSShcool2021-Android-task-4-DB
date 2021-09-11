package com.korneysoft.rsshcool2021_android_task_4_db.data.sqlite

import android.content.Context
import androidx.lifecycle.LiveData
import com.korneysoft.rsshcool2021_android_task_4_db.data.EditDBInterface
import com.korneysoft.rsshcool2021_android_task_4_db.data.Item
import kotlinx.coroutines.flow.Flow

class SQLiteRepository(context: Context) : EditDBInterface {
    val nameType = "SQLiteOpenHelper"

    private val dao = SQLiteDao(context)

    fun getItems(): Flow<List<Item>> = dao.getItems()
    fun getItem(id: Int): LiveData<Item?> = dao.getItem(id)

    override fun add(item: Item) = dao.add(item)
    override suspend fun  delete(item: Item) = dao.delete(item)
    override fun update(item: Item) = dao.update(item)

}