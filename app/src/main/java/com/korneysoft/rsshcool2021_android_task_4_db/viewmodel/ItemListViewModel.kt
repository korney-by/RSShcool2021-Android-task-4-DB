package com.korneysoft.rsshcool2021_android_task_4_db.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.korneysoft.rsshcool2021_android_task_4_db.data.ItemEssence
import com.korneysoft.rsshcool2021_android_task_4_db.data.nodatabase.NoDBData
import com.korneysoft.rsshcool2021_android_task_4_db.data.sqlite.SQLiteHelper
import com.korneysoft.rsshcool2021_android_task_4_db.ui.AddItemInterface

private const val TAG = "T4-ListViewModel"

class ItemListViewModel(app: Application) : AndroidViewModel(app), AddItemInterface {

    //val db  = NoDBData(15)
    val db  = SQLiteHelper(app)


    init {
        //TODO - delete in production
        Log.d(TAG, "ListViewModel instance created")
    }


    override fun onCleared() {
        super.onCleared()

        //TODO - delete in production
        Log.d(TAG, "ListViewModel instance about to be  destroyed")
    }

    override fun addItem(item: ItemEssence) {
        db.add(item)
    }
}