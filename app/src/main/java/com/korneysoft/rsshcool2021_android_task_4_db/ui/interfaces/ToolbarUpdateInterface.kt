package com.korneysoft.rsshcool2021_android_task_4_db.ui.interfaces

interface ToolbarUpdateInterface {
    fun setToolbarTitle(title: String, subtitle: String = "")
    fun setToolbarHamburgerButton(iconResource: Int, action: () -> Unit)
    fun setToolBarMenu(menuResource: Int, actions: Array<() -> Unit>)
}