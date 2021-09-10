package com.korneysoft.rsshcool2021_android_task_4_db.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.korneysoft.rsshcool2021_android_task_4_db.data.ItemEssence
import com.korneysoft.rsshcool2021_android_task_4_db.data.ItemRepository

private const val TAG = "T4-ListViewModel"

class ItemViewModel(app: Application) : AndroidViewModel(app) {

    private val repository = ItemRepository.get()
    //val items: LiveData<List<ItemEssence>>
    val items= repository.getItems()

    init {
    }


    override fun onCleared() {
        super.onCleared()

        //TODO - delete in production
        //Log.d(TAG, "ListViewModel instance about to be  destroyed")
    }


}