package com.korneysoft.rsshcool2021_android_task_4_db.data.room

import com.korneysoft.rsshcool2021_android_task_4_db.data.DatabaseModel

internal const val DATABASE_NAME = DatabaseModel.DATABASE_NAME
internal const val DATABASE_VERSION = DatabaseModel.DATABASE_VERSION
internal const val TABLE_NAME = DatabaseModel.TABLE_NAME
internal const val COLUMN_ID = DatabaseModel.COLUMN_ID
internal const val COLUMN_NAME = DatabaseModel.COLUMN_NAME
internal const val COLUMN_AGE = DatabaseModel.COLUMN_AGE
internal const val COLUMN_BREED = DatabaseModel.COLUMN_BREED

internal const val SQL_GET_ALL = "SELECT * FROM $TABLE_NAME"
internal const val SQL_GET_ONE = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID=(:id)"