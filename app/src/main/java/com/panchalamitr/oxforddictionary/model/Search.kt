package com.panchalamitr.oxforddictionary.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
data class Search(
    @SerializedName("Title")
    @Expose
    var title: String,

    @SerializedName("Year")
    @Expose
    var year: String,

    @PrimaryKey
    @SerializedName("imdbID")
    @Expose
    var imdbID: String,

    @SerializedName("Type")
    @Expose
    var type: String,

    @SerializedName("Poster")
    @Expose
    var poster: String
)