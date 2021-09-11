package com.korneysoft.rsshcool2021_android_task_4_db.data.nodatabase

import com.korneysoft.rsshcool2021_android_task_4_db.data.EditDBInterface
import com.korneysoft.rsshcool2021_android_task_4_db.data.Item
import com.korneysoft.rsshcool2021_android_task_4_db.viewmodel.NoDBAdapter
import java.util.*

class NoDBData(count: Int) : NoDBAdapterInterface, EditDBInterface {
    val nameType = "ArrayList"
    val adapter = NoDBAdapter(this)

    val ITEMS: MutableList<Item> = ArrayList()
    val ITEM_MAP: MutableMap<Int, Item> = HashMap()

    init {
        for (i in 1..count) {
            addItem(generateItemEssence(ITEMS.size))
        }
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

    override fun getItemEssence(position: Int): Item {
        //return ITEMS[position]
        return ITEM_MAP.get(position)!!
    }

    override fun getItemCount(): Int = ITEMS.size

    override fun getItemList(): List<Item> = ITEMS

    override suspend fun delete(item: Item) {
        deleteItem(item)
    }

    override fun add(item: Item) {
        addItem(item)
    }

    override fun update(item: Item) {
        //updateItem(item)
    }
}