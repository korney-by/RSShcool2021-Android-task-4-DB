package com.korneysoft.rsshcool2021_android_task_4_db.data.nodatabase

import androidx.lifecycle.LiveData
import com.korneysoft.rsshcool2021_android_task_4_db.data.Item
import com.korneysoft.rsshcool2021_android_task_4_db.data.RepositoryInterface

class NoDBRepository(count: Int) : RepositoryInterface {
    override val nameType = "ArrayList"
    private val dao = NoDBDao(count)

    override fun getItems() = dao.getItems()
    override fun getItem(id: Int): LiveData<Item?> = dao.getItem(id)

    override suspend fun add(item: Item) = dao.add(item)
    override suspend fun delete(item: Item) = dao.delete(item)
    override suspend fun update(item: Item) = dao.update(item)

    override suspend fun setSort(isSorted: Boolean, sortField: String){}
    override fun close() {}


}
