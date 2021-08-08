package com.example.giphyapp.common.utils

@JvmSynthetic
inline fun <reified R> Any?.ifNotNullAs(
    ifNotNull: (R) -> Unit
): Any? {

    return ifNotNullAs(
        ifNotNull = ifNotNull,
        ifNull = { }
    )
}

@JvmSynthetic
inline fun <reified R> Any?.ifNotNullAs(
    ifNotNull: (R) -> Unit,
    ifNull: () -> Unit
): Any? {

    if (this != null && this is R) {
        ifNotNull(this as R)
        return this
    }
    ifNull()
    return this
}