package com.korneysoft.rsshcool2021_android_task_4_db.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.korneysoft.rsshcool2021_android_task_4_db.data.Item
import com.korneysoft.rsshcool2021_android_task_4_db.data.ItemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

private const val TAG = "T4-ItemListViewModel"

class ItemViewModel(app: Application) : AndroidViewModel(app) {

    private val repository = ItemRepository.get()

    val itemListLiveData: LiveData<List<Item>> = repository.getItems().asLiveData(context = Dispatchers.IO) // . allItems

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