package com.example.flixster

import com.google.gson.annotations.SerializedName

/**
 * The Model for storing a single book from the NY Times API
 *
 * SerializedName tags MUST match the JSON response for the
 * object to correctly parse with the gson library.
 */
class Movie {
//    @SerializedName("popularity")
//    var popularity = 0

    @JvmField
    @SerializedName("title")
    var title: String? = null

    @JvmField
    @SerializedName("release_date")
    var releaseDate: String? = null

    @SerializedName("poster_path")
    var posterPath: String? = null

    @SerializedName("overview")
    var overview: String? = null

//    @SerializedName("amazon_product_url")
//    var amazonUrl: String? = null
}