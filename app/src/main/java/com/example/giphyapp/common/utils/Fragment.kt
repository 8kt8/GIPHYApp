package com.example.giphyapp.common.utils

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.showToast(message: String, length: Int){
    Toast.makeText(requireContext(), message, length).show()
}

fun Fragment.showToastShort(message: String){
    showToast(message, Toast.LENGTH_SHORT)
}

 fun Fragment.showToastLong(message: String ){
    showToast(message, Toast.LENGTH_LONG)
}