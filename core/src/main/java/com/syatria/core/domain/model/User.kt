package com.syatria.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val userId: String,
    val name: String,
    val status: String,
    val imageUser: String,
    val isFavorite: Boolean
) : Parcelable