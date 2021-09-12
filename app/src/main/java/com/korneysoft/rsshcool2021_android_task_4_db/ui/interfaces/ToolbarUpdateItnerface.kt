package com.korneysoft.rsshcool2021_android_task_4_db.ui.interfaces

import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.fragment.app.Fragment

interface ToolbarUpdateItnerface {
    fun setToolBarSettings(
        fragmentName: String,
        menuResource: Int,
        menuIconResource: Int,
        iconResource: Int,
        action: () -> Unit
    )
}