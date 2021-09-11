package com.korneysoft.rsshcool2021_android_task_4_db.data

interface EditDBInterface {
    abstract fun add(item: Item)
    abstract fun delete(item: Item)
    abstract fun update(item: Item)
}