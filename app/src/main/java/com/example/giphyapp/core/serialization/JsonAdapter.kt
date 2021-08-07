package com.example.giphyapp.core.serialization

import java.lang.reflect.ParameterizedType

interface JsonAdapter {
    fun <T> stringToJson(string: String?, clazz: Class<T>): T?

    fun <T> stringToJson(string: String?, type: ParameterizedType): T?

    fun <T> jsonToString(json: T?, clazz: Class<T>): String?

    fun <T> jsonToString(json: T?, type: ParameterizedType): String?
}