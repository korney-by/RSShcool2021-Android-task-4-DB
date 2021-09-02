package com.korneysoft.rsshcool2021_android_task_4_db.data.nodatabase

import com.korneysoft.rsshcool2021_android_task_4_db.data.ItemEssence

interface NoDBAdapterInterface {
    fun getItemEssence( position: Int): ItemEssence
    fun getItemCount(): Int
    fun getItemList():List<ItemEssence>
}