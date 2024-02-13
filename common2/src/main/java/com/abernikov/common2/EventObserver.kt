package com.abernikov.multiplatformapp.android

import androidx.lifecycle.Observer

/***
 * в качестве аргумента конструктора принимает лямбда выражение обработчик в аргумент которой передается
 * необработанное ранее событие получаемое в реализации метода Observer`a onChanged
 */
class EventObserver<E>(private val onEventUnhandledContent: (E) -> Unit) : Observer<Event<E>> {

    override fun onChanged(value: Event<E>) {
        //если есть необработанное событие (контент) передай в качестве аргумента в лямбду
        // onEventUnhandledContent
        value.getContentIfNotHandled()?.let {
            onEventUnhandledContent(it)
        }
    }
}