package com.korneysoft.rsshcool2021_android_task_4_db.data.sqlite

import android.database.Cursor
import com.korneysoft.rsshcool2021_android_task_4_db.data.ItemEssence

interface SQLiteCursorAdapterInterface {
    fun getItem(cursor: Cursor): ItemEssence
    fun getCursor():Cursor
}