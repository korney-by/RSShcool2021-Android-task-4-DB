package com.korneysoft.rsshcool2021_android_task_4_db.data.sqlite

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.korneysoft.rsshcool2021_android_task_4_db.data.DatabaseModel
import com.korneysoft.rsshcool2021_android_task_4_db.data.Item
import java.sql.SQLException

private const val TAG = "T4-SQLiteDao"

private const val DATABASE_NAME = DatabaseModel.DATABASE_NAME
internal const val DATABASE_VERSION = DatabaseModel.DATABASE_VERSION
private const val TABLE_NAME = DatabaseModel.TABLE_NAME
private const val COLUMN_ID = DatabaseModel.COLUMN_ID
private const val COLUMN_NAME = DatabaseModel.COLUMN_NAME
private const val COLUMN_AGE = DatabaseModel.COLUMN_AGE
private const val COLUMN_BREED = DatabaseModel.COLUMN_BREED

private const val CREATE_TABLE_SQL =
    "CREATE TABLE IF NOT EXISTS $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            "$COLUMN_NAME TEXT NOT NULL, $COLUMN_AGE INTEGER NOT NULL, $COLUMN_BREED TEXT NOT NULL)"
private const val INSERT_RECORD_SQL =
    "INSERT INTO $TABLE_NAME ($COLUMN_NAME, $COLUMN_AGE, $COLUMN_BREED) VALUES ('%s', %d, '%s')"

private const val UPDATE_RECORD_SQL =
    """UPDATE $TABLE_NAME SET
        $COLUMN_NAME='%s',
        $COLUMN_AGE=%d,
        $COLUMN_BREED='%s'
        WHERE $COLUMN_ID=%d"""

private const val DELETE_RECORD_SQL =
    "DELETE FROM $TABLE_NAME WHERE $COLUMN_ID=%d"

private const val SELECT_ALL_SQL = "SELECT * FROM $TABLE_NAME"

private const val SORT_SQL = " ORDER BY %s COLLATE LOCALIZED"
private const val SORT_SQL_DESC = " ORDER BY %s COLLATE LOCALIZED DESC"

class SQLiteDao(context: Context) : SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION
) {

    private var counterChangeDataBase = 0
    private val updateChangeDataBaseCounter = MutableLiveData<Int>()

    private var sortString: String = ""

    init {
        updateChangeDataBaseCounter.value = 0
    }

    private val itemListFromDB: LiveData<List<Item>> =
        updateChangeDataBaseCounter.map { getItemList() }

    override fun onCreate(db: SQLiteDatabase) {
        try {
            db.execSQL(CREATE_TABLE_SQL)
            (1..15).forEach {
                db.execSQL(INSERT_RECORD_SQL.format("name $it", (1..10).random(), "breed $it"))
            }
        } catch (e: SQLException) {
            Log.e(TAG, "Exception while trying to create database", e)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        Log.d(TAG, "onUpgrade called")
    }

    private fun getCursorForAll(): Cursor {
        return readableDatabase.rawQuery(SELECT_ALL_SQL + sortString, null)
    }

    private fun getSortString(isSorted: Boolean, sortField: String, isDesc: Boolean): String {
        if (!isSorted) return ""
        return if (sortField.isNotEmpty()) {
            if (isDesc) SORT_SQL_DESC.format(sortField)
            else SORT_SQL.format(sortField)
        } else ""
    }

    private fun insertItem(item: Item) {
        item.apply {
            writableDatabase.execSQL(INSERT_RECORD_SQL.format(name, age, breed))
        }
    }

    fun setSort(isSorted: Boolean, sortField: String, isDesc: Boolean) {
        val newSortString = getSortString(isSorted, sortField, isDesc)
        if (sortString != newSortString) {
            sortString = newSortString
            onChangeData()
        }
    }

    private fun deleteItem(item: Item) {
        writableDatabase.execSQL(DELETE_RECORD_SQL.format(item.id))
    }

    private fun updateItem(item: Item) {
        item.apply {
            val s = UPDATE_RECORD_SQL.format(name, age, breed, id)
            Log.d(TAG, s)
            writableDatabase.execSQL(s)
        }

        item.apply {}
    }

    private fun getItemFromCursor(cursor: Cursor): Item {
        return Item(
            id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID)),
            name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),
            age = cursor.getInt(cursor.getColumnIndex(COLUMN_AGE)),
            breed = cursor.getString(cursor.getColumnIndex(COLUMN_BREED))
        )
    }

    private fun getItemList(): List<Item> {
        val listOfItems = mutableListOf<Item>()
        getCursorForAll().use { cursor ->
            if (cursor.moveToFirst()) {
                do {
                    listOfItems.add(getItemFromCursor(cursor))
                } while (cursor.moveToNext())
            }
        }
        return listOfItems
    }

    fun getItems(): LiveData<List<Item>> {
        return itemListFromDB
    }

    private fun onChangeData() {
        updateChangeDataBaseCounter.postValue(++counterChangeDataBase)
    }

    fun add(item: Item) {
        insertItem(item)
        onChangeData()
    }

    fun delete(item: Item) {

        deleteItem(item)
        onChangeData()
    }

    fun update(item: Item) {
        updateItem(item)
        onChangeData()
    }
}