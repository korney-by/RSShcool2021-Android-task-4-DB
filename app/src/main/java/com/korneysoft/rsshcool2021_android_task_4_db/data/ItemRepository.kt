package com.korneysoft.rsshcool2021_android_task_4_db.data

import android.content.Context
import com.korneysoft.rsshcool2021_android_task_4_db.data.room.RoomRepository
import com.korneysoft.rsshcool2021_android_task_4_db.ui.ChangeDBInterface
import java.lang.IllegalStateException

class ItemRepository private constructor(context: Context) : ChangeDBInterface {

    //private val db  = NoDBData(7)
    //private val db = SQLiteHelper(context)
    private val db = RoomRepository(context)

    val adapter get() = db.adapter
    val dbTypeName get() = db.name

    override var onChangeData: (() -> Unit)? = null

    init {
        db.onAdd = { onChange() }
        db.onDelete = { onChange() }

        onChange()
    }

    fun getItems() =db.getItems()
    fun getItem(id:Int) =db.getItem(id)


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
        private var INSTANCE: ItemRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = ItemRepository(context)
            }
        }

        fun get(): ItemRepository {
            return INSTANCE ?: throw IllegalStateException("ItemListRepository must be initialised")
        }
    }
}