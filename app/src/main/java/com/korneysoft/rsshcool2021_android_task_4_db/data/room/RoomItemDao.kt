package com.korneysoft.rsshcool2021_android_task_4_db.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.korneysoft.rsshcool2021_android_task_4_db.data.DatabaseModel
import com.korneysoft.rsshcool2021_android_task_4_db.data.Item
import androidx.room.Delete
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

internal const val DATABASE_NAME = DatabaseModel.DATABASE_NAME
internal const val DATABASE_VERSION = DatabaseModel.DATABASE_VERSION
private const val TABLE_NAME = DatabaseModel.TABLE_NAME
private const val COLUMN_ID = DatabaseModel.COLUMN_ID
private const val COLUMN_NAME = DatabaseModel.COLUMN_NAME
private const val COLUMN_AGE = DatabaseModel.COLUMN_AGE
private const val COLUMN_BREED = DatabaseModel.COLUMN_BREED

private const val SQL_GET_ALL = "SELECT * FROM $TABLE_NAME"
private const val SQL_GET_ONE = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID=(:id)"

@Dao
interface RoomItemDao {
    @Query(SQL_GET_ALL)
    fun getItems(): LiveData<List<Item>>

    @Query(SQL_GET_ONE)
    fun getItem(id:Int):LiveData<Item?>

    @Delete()
    fun delete(item:Item)

    @Insert() //onConflict = OnConflictStrategy.IGNORE
    fun insert(item:Item)

    @Update
    fun update(item:Item)

}