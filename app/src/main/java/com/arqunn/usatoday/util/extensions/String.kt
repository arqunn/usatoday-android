package com.arqunn.usatoday.util.extensions

fun String?.splitByDelimiter(delimiter: String) = this?.split(delimiter)?.first() ?: ""