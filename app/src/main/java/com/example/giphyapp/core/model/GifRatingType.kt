package com.example.giphyapp.core.model

enum class GifRatingType(val code: String) {
    G("g"),
    PG("pg"),
    PG13("pg-13"),
    R("r"),
    UNKNOWN("");

    companion object{
        fun fromCode(code: String) = values().associateBy(GifRatingType::code)[code] ?: UNKNOWN
    }

}