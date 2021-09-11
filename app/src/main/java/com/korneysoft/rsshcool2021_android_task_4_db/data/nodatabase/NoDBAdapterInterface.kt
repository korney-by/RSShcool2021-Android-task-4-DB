package com.korneysoft.rsshcool2021_android_task_4_db.data.nodatabase

import com.korneysoft.rsshcool2021_android_task_4_db.data.Item

interface NoDBAdapterInterface {
    fun getItemEssence( position: Int): Item
    fun getItemCount(): Int
    fun getItemList():List<Item>
}