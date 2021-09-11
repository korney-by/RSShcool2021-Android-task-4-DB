package com.korneysoft.rsshcool2021_android_task_4_db.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.korneysoft.rsshcool2021_android_task_4_db.data.Item
import com.korneysoft.rsshcool2021_android_task_4_db.data.ItemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "T4-ItemListViewModel"

class ItemViewModel(app: Application) : AndroidViewModel(app) {

    private val repository = ItemRepository.get()
    val itemListLiveData = repository.getItems()


    fun deleteItem(item:Item)=viewModelScope.launch(Dispatchers.IO){
        repository.deleteItem(item)

    }

    fun addItem(item:Item) =viewModelScope.launch(Dispatchers.IO){
        repository.addItem(item)
    }

    fun updateItem(item:Item)=viewModelScope.launch(Dispatchers.IO){
        repository.updateItem(item)
    }


    override fun onCleared() {
        super.onCleared()

        //TODO - delete in production
        //Log.d(TAG, "ListViewModel instance about to be  destroyed")
    }


}