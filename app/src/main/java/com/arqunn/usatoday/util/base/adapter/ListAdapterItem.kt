package com.arqunn.usatoday.util.base.adapter

/**
 * https://github.com/adessoTurkey/android-sample-app/blob/develop/app/src/main/kotlin/com/adesso/movee/base/ListAdapterItem.kt
 * */
interface ListAdapterItem {
    val id: Int

    override fun equals(other: Any?): Boolean
}
