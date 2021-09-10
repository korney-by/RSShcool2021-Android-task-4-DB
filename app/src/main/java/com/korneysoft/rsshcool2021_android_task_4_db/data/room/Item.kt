package com.korneysoft.rsshcool2021_android_task_4_db.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = TABLE_NAME)
data class Item(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = COLUMN_ID) val id: Int,
    @ColumnInfo(name = COLUMN_NAME) var name: String = "",
    @ColumnInfo(name = COLUMN_AGE) var age: Int = 0,
    @ColumnInfo(name = COLUMN_BREED) var breed: String = ""
)

