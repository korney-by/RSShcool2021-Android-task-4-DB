package com.korneysoft.rsshcool2021_android_task_4_db.data

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.korneysoft.rsshcool2021_android_task_4_db.viewmodel.ItemEssence
import java.sql.SQLException

private const val TAG = "T4-SQLiteHelper"

private const val DATABASE_NAME = "Task4_DB"
private const val DATABASE_VERSION = 1
private const val TABLE_NAME = "essence"
internal const val COLUMN_ID = "_id"
internal const val COLUMN_NAME = "NAME"
internal const val COLUMN_AGE = "AGE"
internal const val COLUMN_BREED = "BEED"
private const val CREATE_TABLE_SQL =
    "CREATE TABLE IF NOT EXISTS $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "$COLUMN_NAME VARCHAR(30), $COLUMN_AGE INTEGER, $COLUMN_BREED VARCHAR(20));"
private const val INSERT_RECORD_SQL =
    "INSERT INTO $TABLE_NAME ($COLUMN_NAME, $COLUMN_AGE, $COLUMN_BREED) VALUES ('%s', %d, '%s');"

private const val SELECT_RECORD_SQL = "SELECT * FROM $TABLE_NAME;"

class SQLiteHelper(context: Context) : SQLiteOpenHelper (
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION
),ReciclerViewAdapterInterface {

    override fun onCreate(db: SQLiteDatabase) {
        try {
            db.execSQL(CREATE_TABLE_SQL)
            (10..15).forEach {
                db.execSQL(INSERT_RECORD_SQL.format("name $it", it, "breed $it"))
            }
        } catch (e: SQLException) {
            Log.e(TAG, "Exception while trying to create database", e)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        Log.d(TAG, "onUpgrade called")
    }

    fun getCursorWithItem(): Cursor {
        return readableDatabase.rawQuery(SELECT_RECORD_SQL, null)
    }

    fun insertItem(itemEssence: ItemEssence) {
        itemEssence.apply {
            writableDatabase.execSQL(INSERT_RECORD_SQL.format(name, age, breed))
        }
    }


    private fun getItemFromCursor(cursor: Cursor): ItemEssence {
        return ItemEssence(
            id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID)),
            name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),
            age = cursor.getInt(cursor.getColumnIndex(COLUMN_AGE)),
            breed = cursor.getString(cursor.getColumnIndex(COLUMN_BREED))
        )
    }

    fun getListOfItemEssences(): List<ItemEssence> {

        val listOfItems = mutableListOf<ItemEssence>()
        getCursorWithItem().use { cursor ->
            if (cursor.moveToFirst()) {
                do {
                    listOfItems.add(getItemFromCursor(cursor))
                } while (cursor.moveToNext())
            }
        }
        return listOfItems
    }

    override fun getItemEssence(position: Int): ItemEssence {
        return getListOfItemEssences()[position]
    }

    override fun getItemCount(): Int {
        return getListOfItemEssences().size
    }

    override fun getItemList(): List<ItemEssence> =  getListOfItemEssences()

}