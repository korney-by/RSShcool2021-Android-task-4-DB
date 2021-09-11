package com.korneysoft.rsshcool2021_android_task_4_db.data

class DatabaseModel private constructor() {

    companion object {
         const val DATABASE_NAME = "Task4_DB"
         const val DATABASE_VERSION = 1

        const val TABLE_NAME = "essence"
        const val COLUMN_ID = "_id"
        const val COLUMN_NAME = "NAME"
        const val COLUMN_AGE = "AGE"
        const val COLUMN_BREED = "BEED"
    }

}
