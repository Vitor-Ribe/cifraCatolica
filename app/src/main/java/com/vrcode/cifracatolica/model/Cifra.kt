package com.vrcode.cifracatolica.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cifras")
data class Cifra(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val titulo: String,
    val cantor: String,
    val letra: String,
    val compositor: String,
    val categoria: String,
    val image: String
)
