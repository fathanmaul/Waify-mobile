package dev.rushia.final_project

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Waifu(
    val name: String,
    val animeName: String,
    val description: String,
    val vote: String,
    val photo: String
) : Parcelable
