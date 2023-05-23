package com.arqunn.usatoday.util.extensions

fun Boolean.letOnTrue(block: () -> Unit): Boolean {
    if (this) {
        block.invoke()
        return true
    }
    return false
}

fun Boolean.letOnFalse(block: () -> Unit): Boolean {
    if (!this) {
        block.invoke()
        return false
    }
    return true
}

suspend fun Boolean.letOnTrueOnSuspend(block: suspend () -> Unit): Boolean {
    if (this) {
        block.invoke()
        return true
    }
    return false
}

suspend fun Boolean.letOnFalseOnSuspend(block: suspend () -> Unit): Boolean {
    if (!this) {
        block.invoke()
        return false
    }
    return true
}

fun Boolean.toInt() = if (this) 1 else 0