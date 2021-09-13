package com.korneysoft.rsshcool2021_android_task_4_db.data.nodatabase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.map
import com.korneysoft.rsshcool2021_android_task_4_db.data.Item
import com.korneysoft.rsshcool2021_android_task_4_db.data.RepositoryInterface
import kotlinx.coroutines.flow.Flow
import java.util.*

class NoDBDao(count: Int)  {
    private val ITEMS: MutableList<Item> = ArrayList()
    private val ITEM_MAP: MutableMap<Int, Item> = HashMap()

    private var counterChangeDataBase = 0
    private val updateChangeDataBaseCounter = MutableLiveData<Int>()
    private val listCatsFromDB: LiveData<List<Item>> =
        updateChangeDataBaseCounter.map { getItemList() }

    init {
        for (i in 1..count) {
            addItem(generateItemEssence(ITEMS.size))
        }
        updateChangeDataBaseCounter.value = 0
    }


    fun addItem(_item: Item) {
        val item = if (_item.id <= 0) changeIDItemEssence(ITEMS.size, _item) else _item
        ITEMS.add(item)
        ITEM_MAP.put(item.id, item)
    }

    fun deleteItem(item: Item) {
        ITEMS.remove(item)
        ITEM_MAP.remove(item.id)
    }

    private fun getItemList(): List<Item> {
        return ITEMS.toList()
    }

    private fun changeIDItemEssence(newId: Int, item: Item): Item {
        return Item(
            newId, item.name, item.age, item.breed
        )
    }

    private fun generateItemEssence(position: Int): Item {
        return Item(
            position,
            "Item $position",
            (1..15).random(),
            "Breed $position"
        )
    }


    fun getItems(): Flow<List<Item>> {
        return listCatsFromDB.asFlow()
    }

    fun getItem(id: Int): LiveData<Item?> {
        val item: Item? = ITEM_MAP.get(id)
        val itemLiveData = MutableLiveData<Item?>()

        itemLiveData.postValue(item)
        return itemLiveData
    }

    private suspend fun onChangeData() {
        updateChangeDataBaseCounter.postValue(++counterChangeDataBase)
    }

    suspend fun delete(item: Item) {
        deleteItem(item)
        onChangeData()
    }

    suspend fun add(item: Item) {
        addItem(item)
        onChangeData()
    }

    suspend fun update(item: Item) {
        //updateItem(item)
        //onChangeData()
    }
}