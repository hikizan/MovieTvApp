package com.hikizan.movietvapp.core.utils.ext

fun Int?.orZero(): Int {
    return this ?: 0
}

fun Double?.orZero(): Double {
    return this ?: 0.0
}

fun String?.orEmptyString(): String {
    return this ?: ""
}