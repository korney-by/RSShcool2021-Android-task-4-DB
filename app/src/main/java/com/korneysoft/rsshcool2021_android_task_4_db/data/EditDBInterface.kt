package com.korneysoft.rsshcool2021_android_task_4_db.data

interface EditDBInterface {
    abstract suspend fun add(item: Item)
    abstract suspend fun delete(item: Item)
    abstract suspend fun update(item: Item)
}