package com.korneysoft.rsshcool2021_android_task_4_db.data

interface EditDBInterface {
    fun add(item: ItemEssence)
    fun delete(item: ItemEssence)

    var onDelete: (() -> Unit)?
    var onAdd: (() -> Unit)?
}