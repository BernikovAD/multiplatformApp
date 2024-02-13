package com.abernikov.multiplatformapp.android

class Event<out E>(private val content: E) {
    private var hasBeenHandled = false

    /***
     * возвращает контент который еще не был обработан иначе null
     */
    fun getContentIfNotHandled(): E? {
        return if (hasBeenHandled) null
        else {
            hasBeenHandled = true
            content
        }
    }

    fun peekContent(): E = content
}