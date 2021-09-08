package com.korneysoft.rsshcool2021_android_task_4_db.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.korneysoft.rsshcool2021_android_task_4_db.data.EditDBInterface
import com.korneysoft.rsshcool2021_android_task_4_db.data.ItemEssence
import com.korneysoft.rsshcool2021_android_task_4_db.data.nodatabase.NoDBData
import com.korneysoft.rsshcool2021_android_task_4_db.data.sqlite.SQLiteHelper
import com.korneysoft.rsshcool2021_android_task_4_db.ui.ChangeDBInterface

private const val TAG = "T4-ListViewModel"

class ItemListViewModel(app: Application) : AndroidViewModel(app), ChangeDBInterface {

    private val db  = NoDBData(15)
        //private val db = SQLiteHelper(app)

    val adapter get()= db.adapter
    val dbTypeName get() = db.name

    override var onChangeData: (() -> Unit)? = null

    init {
        //TODO - delete in production
        //Log.d(TAG, "ListViewModel instance created")

        db.onAdd={ onChange() }
        db.onDelete={ onChange() }

        onChange()
    }

    private fun onChange(){
        db.adapter.update()
        onChangeData?.invoke()
    }

    override fun onCleared() {
        super.onCleared()

        //TODO - delete in production
        //Log.d(TAG, "ListViewModel instance about to be  destroyed")
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

}