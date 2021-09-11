package com.korneysoft.rsshcool2021_android_task_4_db.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

private const val TABLE_NAME = DatabaseModel.TABLE_NAME
private const val COLUMN_ID = DatabaseModel.COLUMN_ID
private const val COLUMN_NAME = DatabaseModel.COLUMN_NAME
private const val COLUMN_AGE = DatabaseModel.COLUMN_AGE
private const val COLUMN_BREED = DatabaseModel.COLUMN_BREED

@Entity(tableName = TABLE_NAME)
data class Item(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = COLUMN_ID) val id: Int,
    @ColumnInfo(name = COLUMN_NAME) var name: String = "",
    @ColumnInfo(name = COLUMN_AGE) var age: Int = 0,
    @ColumnInfo(name = COLUMN_BREED) var breed: String = ""
) {
    override fun toString(): String = "$id. $name - $age - $breed"
}