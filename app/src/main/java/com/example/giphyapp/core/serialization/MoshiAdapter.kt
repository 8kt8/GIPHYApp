package com.example.giphyapp.core.serialization

import com.squareup.moshi.Moshi
import java.lang.reflect.ParameterizedType
import javax.inject.Inject

class MoshiAdapter @Inject constructor(private val moshi: Moshi) : JsonAdapter {

    override fun <T> stringToJson(string: String?, clazz: Class<T>): T? {
        return string?.let { moshi.adapter(clazz).fromJson(string) }
    }

    override fun <T> stringToJson(string: String?, type: ParameterizedType): T? {
        return string?.let { moshi.adapter<T>(type).fromJson(string) }
    }

    override fun <T> jsonToString(json: T?, clazz: Class<T>): String? {
        return json?.let { moshi.adapter(clazz).toJson(json) }
    }

    override fun <T> jsonToString(json: T?, type: ParameterizedType): String? {
        return json?.let { moshi.adapter<T>(type).toJson(json) }
    }
}