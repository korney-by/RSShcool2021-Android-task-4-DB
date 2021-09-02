package com.korneysoft.rsshcool2021_android_task_4_db.data

import com.korneysoft.rsshcool2021_android_task_4_db.viewmodel.ItemEssence

interface EditDBInterface {
    fun addItem(item: ItemEssence)
    fun deleteItem(itemID: Int)
    fun deleteItem(item: ItemEssence)

    var onDelete: (() -> Unit)?
    var onAdd: (() -> Unit)?
}