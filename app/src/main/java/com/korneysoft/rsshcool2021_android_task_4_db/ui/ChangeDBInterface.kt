package com.korneysoft.rsshcool2021_android_task_4_db.ui

import com.korneysoft.rsshcool2021_android_task_4_db.data.Item

interface ChangeDBInterface {

  var onChangeData: (() -> Unit)?

  fun addItem(item:Item)
  fun deleteItem(item:Item)
}