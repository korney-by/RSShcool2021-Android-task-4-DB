package com.korneysoft.rsshcool2021_android_task_4_db.db

import android.database.sqlite.SQLiteDatabase
import com.korneysoft.rsshcool2021_android_task_4_db.viewmodel.ItemEssence

interface ReciclerViewAdapterInterface {
    fun getItemEssence( position: Int): ItemEssence
    fun getItemCount(): Int
    fun getItemList():List<ItemEssence>
}