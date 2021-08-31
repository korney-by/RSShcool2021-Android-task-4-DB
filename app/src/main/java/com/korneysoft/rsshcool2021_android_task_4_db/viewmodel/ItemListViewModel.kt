package com.korneysoft.rsshcool2021_android_task_4_db.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel

import java.util.ArrayList
import java.util.HashMap

private const val TAG="T4-ListViewModel"

class ItemListViewModel() : ViewModel() {

    val ITEMS: MutableList<ItemEssence> = ArrayList()
    val ITEM_MAP: MutableMap<String, ItemEssence> = HashMap()
    private val COUNT = 10

    init {
        //TODO - delete in production
        Log.d(TAG,"ListViewModel instance created")

//         Add some sample items.
        for (i in 1..COUNT) {
            addItem(createItemEssence(i))
        }
    }

    private fun addItem(item: ItemEssence) {
        ITEMS.add(item)
        ITEM_MAP.put(item.id, item)
    }

    private fun createItemEssence(position: Int): ItemEssence {
        return ItemEssence(position.toString(), "Item $position", (1..15).random(),"Breed $position")
    }


    override fun onCleared() {
        super.onCleared()

        //TODO - delete in production
        Log.d(TAG,"ListViewModel instance about to be  destroyed")
    }
}