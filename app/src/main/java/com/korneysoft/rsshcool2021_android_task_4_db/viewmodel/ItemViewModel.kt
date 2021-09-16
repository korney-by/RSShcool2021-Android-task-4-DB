package com.korneysoft.rsshcool2021_android_task_4_db.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData


import androidx.lifecycle.viewModelScope
import com.korneysoft.rsshcool2021_android_task_4_db.data.Item
import com.korneysoft.rsshcool2021_android_task_4_db.data.ItemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

private const val TAG = "T4-ItemListViewModel"

class ItemViewModel(app: Application) : AndroidViewModel(app) {

    private var repository = ItemRepository.get()

    var itemListLiveData: LiveData<List<Item>> = getConnectToRepository()

    fun setSort(isSorted: Boolean, sortField: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.setSort(isSorted, sortField)
    }

    val daoTypeName: String
        get() = repository.dbTypeName

    fun setActualRepository() {
        repository.setActualRepository()
        itemListLiveData = getConnectToRepository()
    }

    private fun getConnectToRepository() = repository.getItems()
    //.asLiveData(context = Dispatchers.IO)


    fun deleteItem(item: Item) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteItem(item)
    }

    fun addItem(item: Item) = viewModelScope.launch(Dispatchers.IO) {
        repository.addItem(item)
    }


    fun updateItem(item: Item) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateItem(item)
    }


}