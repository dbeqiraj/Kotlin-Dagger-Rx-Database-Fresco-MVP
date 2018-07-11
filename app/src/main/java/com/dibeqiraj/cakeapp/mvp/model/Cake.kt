package com.dibeqiraj.cakeapp.mvp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Cake(

        @SerializedName("id")
        val id: Int,

        @SerializedName("title")
        val title: String,

        @SerializedName("previewDescription")
        val previewDescription: String,

        @SerializedName("detailDescription")
        val detailDescription: String,

        @SerializedName("imageUrl")
        val imageUrl: String
) : Serializable