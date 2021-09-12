package com.korneysoft.rsshcool2021_android_task_4_db.data.sqlite

import android.content.Context
import androidx.lifecycle.LiveData
import com.korneysoft.rsshcool2021_android_task_4_db.data.EditDBInterface
import com.korneysoft.rsshcool2021_android_task_4_db.data.GetDataDBInterface
import com.korneysoft.rsshcool2021_android_task_4_db.data.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn

class SQLiteRepository(context: Context) : EditDBInterface,GetDataDBInterface {
    val nameType = "SQLiteOpenHelper"
    private val dao = SQLiteDao(context)

    override fun getItems()=dao.getItems()
//    val getItems: Flow<List<Item>>
//    get()= dao.getItems()
//       // .combine() - сортировка
//        .flowOn(Dispatchers.Default)
//        .conflate()

    override fun getItem(id: Int): LiveData<Item?> = dao.getItem(id)

    override suspend fun add(item: Item) = dao.add(item)
    override suspend fun  delete(item: Item) = dao.delete(item)
    override suspend fun update(item: Item) = dao.update(item)

}