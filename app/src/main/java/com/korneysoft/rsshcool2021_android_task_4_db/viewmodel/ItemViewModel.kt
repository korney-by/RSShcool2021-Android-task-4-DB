package com.korneysoft.rsshcool2021_android_task_4_db.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.korneysoft.rsshcool2021_android_task_4_db.data.Item
import com.korneysoft.rsshcool2021_android_task_4_db.data.ItemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "T4-ItemListViewModel"

class ItemViewModel(app: Application) : AndroidViewModel(app) {

    private var repository = ItemRepository.get()
    var itemListLiveData: LiveData<List<Item>> = getConnectToRepository()

    val daoTypeName: String get() = repository.dbTypeName

    private var _isSorted: Boolean = false
    private var _sortField: String = ""
    private var _sortIsDesc: Boolean = false

    val isSorted: Boolean get() = _isSorted
    val sortIsDesc: Boolean get() = _sortIsDesc

    fun changeSortOrder() {
        setSort(_isSorted, _sortField, !_sortIsDesc)
    }

    fun setSort(isSorted: Boolean, sortField: String, isDesc: Boolean) {
        this._isSorted = isSorted
        this._sortField = sortField
        this._sortIsDesc = isDesc

        viewModelScope.launch(Dispatchers.IO) {
            repository.setSort(isSorted, sortField, isDesc)
        }
    }

    fun setActualRepository() {
        repository.setActualRepository()
        itemListLiveData = getConnectToRepository()
    }

    private fun getConnectToRepository() = repository.getItems()

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