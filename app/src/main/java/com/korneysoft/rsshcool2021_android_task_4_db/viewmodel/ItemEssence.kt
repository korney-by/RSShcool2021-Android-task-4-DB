package com.korneysoft.rsshcool2021_android_task_4_db.viewmodel

data class ItemEssence(
    val id: Int,
    val name: String,
    val age: Int,
    val breed: String
){


    override fun toString(): String = "$id. $name - $age - $breed"
}