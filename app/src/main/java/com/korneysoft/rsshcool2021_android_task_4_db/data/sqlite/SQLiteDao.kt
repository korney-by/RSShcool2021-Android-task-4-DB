package com.korneysoft.rsshcool2021_android_task_4_db.data.sqlite

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.map
import com.korneysoft.rsshcool2021_android_task_4_db.data.RepositoryInterface
import com.korneysoft.rsshcool2021_android_task_4_db.data.DatabaseModel
import com.korneysoft.rsshcool2021_android_task_4_db.data.Item
import kotlinx.coroutines.flow.*
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

private const val DELETE_RECORD_SQL =
    "DELETE FROM $TABLE_NAME WHERE $COLUMN_ID=%d"

private const val SELECT_ONE_SQL = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID=%d"
private const val SELECT_ALL_SQL = "SELECT * FROM $TABLE_NAME"


class SQLiteDao(context: Context) : SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION
) {

    private var counterChangeDataBase = 0
    private val updateChangeDataBaseCounter = MutableLiveData<Int>()

    init {
        updateChangeDataBaseCounter.value = 0
    }

    private val listCatsFromDB: LiveData<List<Item>> =
        updateChangeDataBaseCounter.map { getItemList() }

    override fun onCreate(db: SQLiteDatabase) {
         try {
            db.execSQL(CREATE_TABLE_SQL)
            (1..15).forEach {
                db.execSQL(INSERT_RECORD_SQL.format("name $it", it, "breed $it"))
            }
        } catch (e: SQLException) {
            Log.e(TAG, "Exception while trying to create database", e)
        }
    }


    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        Log.d(TAG, "onUpgrade called")
    }

    private fun getCursorForAll(): Cursor {
        return readableDatabase.rawQuery(SELECT_ALL_SQL, null)
    }

    private fun getCursorForOne(id: Int): Cursor {
        return readableDatabase.rawQuery(SELECT_ONE_SQL.format(id), null)
    }


    private fun insertItem(item: Item) {
        item.apply {
            writableDatabase.execSQL(INSERT_RECORD_SQL.format(name, age, breed))
        }
    }

    private fun deleteItem(item: Item) {
        writableDatabase.execSQL(DELETE_RECORD_SQL.format(item.id))
    }

    private fun getItemFromCursor(cursor: Cursor): Item {
        return Item(
            id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID)),
            name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),
            age = cursor.getInt(cursor.getColumnIndex(COLUMN_AGE)),
            breed = cursor.getString(cursor.getColumnIndex(COLUMN_BREED))
        )
    }

    fun getItem(id: Int): LiveData<Item?> {
        var item: Item? = null
        val itemLiveData = MutableLiveData<Item?>()

        getCursorForOne(id).use { cursor ->
            if (cursor.moveToFirst()) {
                item = getItemFromCursor(cursor)
            }
        }
        itemLiveData.postValue(item)
        return itemLiveData
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
        return listCatsFromDB //.asFlow()
    }

    private suspend fun onChangeData() {
        updateChangeDataBaseCounter.postValue(++counterChangeDataBase)
    }

    suspend fun add(item: Item) {
        insertItem(item)
        onChangeData()
    }

    suspend fun delete(item: Item) {

        deleteItem(item)
        onChangeData()
    }

    suspend fun update(item: Item) {
        //updateItem(item)
        //    onChange()
    }

}