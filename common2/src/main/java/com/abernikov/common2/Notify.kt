package com.abernikov.multiplatformapp.android

import androidx.annotation.StringRes

sealed class Notify {
    abstract val message: String

    data class TextMessage(override val message: String, @StringRes val resId: Int? = null) :
        Notify()

    data class TextMessageError(
        override val message: String,
        @StringRes val resId: Int? = null,
        val title: String? = null,
        val textBtn: String? = null,
    ) :
        Notify()
}