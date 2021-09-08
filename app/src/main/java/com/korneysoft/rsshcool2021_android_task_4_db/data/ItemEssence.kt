package com.korneysoft.rsshcool2021_android_task_4_db.data

data class ItemEssence(
    val id: Int,
    var name: String,
    var age: Int,
    var breed: String
){

    override fun toString(): String = "$id. $name - $age - $breed"
}