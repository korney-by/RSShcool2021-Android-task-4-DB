package com.korneysoft.rsshcool2021_android_task_4_db.data.nodatabase

import com.korneysoft.rsshcool2021_android_task_4_db.data.EditDBInterface
import com.korneysoft.rsshcool2021_android_task_4_db.data.ItemEssence
import com.korneysoft.rsshcool2021_android_task_4_db.viewmodel.NoDBAdapter
import java.util.*

class NoDBData(count: Int) : NoDBAdapterInterface, EditDBInterface {
    val name="ArrayList"
    val adapter= NoDBAdapter(this)

    val ITEMS: MutableList<ItemEssence> = ArrayList()
    val ITEM_MAP: MutableMap<Int, ItemEssence> = HashMap()

    override var onDelete: (() -> Unit)? = null
    override var onAdd: (() -> Unit)? = null

    init {
        for (i in 1..count) {
            add(createItemEssence(i))
        }
    }

    override fun add(item: ItemEssence) {
        ITEMS.add(item)
        ITEM_MAP.put(item.id, item)
        onAdd?.invoke()
    }

    override fun delete(item: ItemEssence) {
        ITEMS.remove(item)
        ITEM_MAP.remove(item.id)
        onDelete?.invoke()
    }

    private fun createItemEssence(position: Int): ItemEssence {
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