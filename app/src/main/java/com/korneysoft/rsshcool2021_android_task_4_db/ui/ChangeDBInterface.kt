package com.korneysoft.rsshcool2021_android_task_4_db.ui

import com.korneysoft.rsshcool2021_android_task_4_db.data.ItemEssence

interface ChangeDBInterface {
  fun addItem(item:ItemEssence)
  fun deleteItem(item:ItemEssence)
}