package com.korneysoft.rsshcool2021_android_task_4_db.data.nodatabase

import com.korneysoft.rsshcool2021_android_task_4_db.data.EditDBInterface
import com.korneysoft.rsshcool2021_android_task_4_db.data.ItemEssence
import com.korneysoft.rsshcool2021_android_task_4_db.viewmodel.NoDBAdapter
import java.util.*

class NoDBData(count: Int) : NoDBAdapterInterface, EditDBInterface {
    val name = "ArrayList"
    val adapter = NoDBAdapter(this)

    val ITEMS: MutableList<ItemEssence> = ArrayList()
    val ITEM_MAP: MutableMap<Int, ItemEssence> = HashMap()

    override var onDelete: (() -> Unit)? = null
    override var onAdd: (() -> Unit)? = null

    init {
        for (i in 1..count) {
            addItem(generateItemEssence(ITEMS.size))
        }
    }

    fun addItem(_item: ItemEssence) {
        val item = if (_item.id <= 0) changeIDItemEssence(ITEMS.size, _item) else _item
        ITEMS.add(item)
        ITEM_MAP.put(item.id, item)
    }

    fun deleteItem(item: ItemEssence) {
        ITEMS.remove(item)
        ITEM_MAP.remove(item.id)
    }

    override fun delete(item: ItemEssence) {
        deleteItem(item)
        onDelete?.invoke()
    }

    override fun add(item: ItemEssence) {
        addItem(item)
        onAdd?.invoke()
    }

    private fun changeIDItemEssence(newId: Int, item: ItemEssence): ItemEssence {
        return ItemEssence(
            newId, item.name, item.age, item.breed
        )
    }

    private fun generateItemEssence(position: Int): ItemEssence {
        return ItemEssence(
            position,
            "Item $position",
            (1..15).random(),
            "Breed $position"
        )
    }

    override fun getItemEssence(position: Int): ItemEssence {
        //return ITEMS[position]
        return ITEM_MAP.get(position)!!
    }

    override fun getItemCount(): Int = ITEMS.size

    override fun getItemList(): List<ItemEssence> = ITEMS


}