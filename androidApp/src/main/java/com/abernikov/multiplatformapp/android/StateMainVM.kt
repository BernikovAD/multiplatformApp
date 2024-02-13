package com.abernikov.multiplatformapp.android

import com.example.example.ExampleJson2KtKotlin

sealed class StateMainVM: IViewModelState {
    data object Loading:StateMainVM()
    data class Success(val data: ExampleJson2KtKotlin):StateMainVM()
    data object Error:StateMainVM()
}