package com.korneysoft.rsshcool2021_android_task_4_db.ui.interfaces

import com.korneysoft.rsshcool2021_android_task_4_db.data.Item

interface ShowFragmentAddItemInterface {
    fun openItemDetailsFragment(item: Item?)
    fun openSettingsFragment()
}