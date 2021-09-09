package com.korneysoft.rsshcool2021_android_task_4_db.data

import android.content.Context
import com.korneysoft.rsshcool2021_android_task_4_db.data.sqlite.SQLiteHelper
import com.korneysoft.rsshcool2021_android_task_4_db.ui.ChangeDBInterface
import java.lang.IllegalStateException

class ItemListRepository private constructor(context: Context) : ChangeDBInterface {

    //private val db  = NoDBData(7)
    private val db = SQLiteHelper(context)

    val adapter get() = db.adapter
    val dbTypeName get() = db.name

    override var onChangeData: (() -> Unit)? = null

    init {
        db.onAdd = { onChange() }
        db.onDelete = { onChange() }

        onChange()
    }

    private fun onChange() {
        db.adapter.update()
        onChangeData?.invoke()
    }

    override fun addItem(item: ItemEssence) {
        if (db is EditDBInterface) {
            db.add(item)
        }
    }

    override fun deleteItem(item: ItemEssence) {
        if (db is EditDBInterface) {
            db.delete(item)
        }
    }


    companion object {
        private var INSTANCE: ItemListRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = ItemListRepository(context)
            }
        }

        fun get(): ItemListRepository {
            return INSTANCE ?: throw IllegalStateException("ItemListRepository must be initialised")
        }
    }
}