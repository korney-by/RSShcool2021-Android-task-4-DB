package com.korneysoft.rsshcool2021_android_task_4_db.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.korneysoft.rsshcool2021_android_task_4_db.data.DatabaseModel
import com.korneysoft.rsshcool2021_android_task_4_db.data.Item
import kotlinx.coroutines.flow.Flow

internal const val DATABASE_NAME = DatabaseModel.DATABASE_NAME
internal const val DATABASE_VERSION = DatabaseModel.DATABASE_VERSION
private const val TABLE_NAME = DatabaseModel.TABLE_NAME
private const val COLUMN_ID = DatabaseModel.COLUMN_ID
private const val COLUMN_NAME = DatabaseModel.COLUMN_NAME
private const val COLUMN_AGE = DatabaseModel.COLUMN_AGE
private const val COLUMN_BREED = DatabaseModel.COLUMN_BREED

private const val SQL_GET_ALL = "SELECT * FROM $TABLE_NAME"
private const val SQL_GET_ONE = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID=:id"

//internal const val SQL_GET_ALL_SORTED = "SELECT * FROM $TABLE_NAME ORDER BY "+
//        "CASE WHEN :columnName='$COLUMN_NAME' THEN $COLUMN_NAME "+
//        "WHEN :columnName='$COLUMN_AGE' THEN $COLUMN_AGE "+
//        "WHEN :columnName='$COLUMN_BREED' THEN $COLUMN_BREED "+
//        "END COLLATE NOCASE"

//internal const val SQL_GET_ALL_SORTED_RAW =
//    """SELECT * FROM $TABLE_NAME ORDER BY
//        CASE %s
//        WHEN $COLUMN_NAME THEN $COLUMN_NAME
//        WHEN $COLUMN_AGE THEN $COLUMN_AGE
//        WHEN $COLUMN_BREED THEN $COLUMN_BREED
//        END COLLATE NOCASE"""
//
//internal const val SQL_GET_ALL_SORTED =
//    """SELECT * FROM $TABLE_NAME ORDER BY
//        CASE :fieldName
//        WHEN $COLUMN_NAME THEN $COLUMN_NAME
//        WHEN $COLUMN_AGE THEN $COLUMN_AGE
//        WHEN $COLUMN_BREED THEN $COLUMN_BREED
//        END COLLATE NOCASE"""

internal const val SQL_GET_ALL_SORTED1 = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_NAME!=:fieldName"


//@Dao
//interface RoomRawDao {
//    @RawQuery
//    fun getItemsSortedRaw(query: String):Flow<List<Item>>
//}
@Dao
interface RoomItemDao {
    @Query(SQL_GET_ALL)
    fun getItems(): Flow<List<Item>>

//
//    @Query(SQL_GET_ALL_SORTED)
//    fun getItemsSorted(fieldName: String): List<Item>



    @Query(SQL_GET_ONE)
    fun getItem(id: Int): LiveData<Item?>

    @Delete()
    fun delete(item: Item)

    @Insert() //onConflict = OnConflictStrategy.IGNORE
    fun insert(item: Item)

    @Update
    fun update(item: Item)

    @Delete
    fun updateAfterSort(item: Item)
}