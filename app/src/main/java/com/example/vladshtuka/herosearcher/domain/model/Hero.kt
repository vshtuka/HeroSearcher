package com.example.vladshtuka.herosearcher.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "hero_table")
data class Hero(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String,
    val birthday: String,
    val status: String,
    val nickname: String,
    val portrayed: String,
    val category: String,
    val img: String,
    var isLiked: Boolean = false
) : Parcelable
