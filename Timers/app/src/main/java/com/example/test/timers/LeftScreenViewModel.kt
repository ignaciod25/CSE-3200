package com.example.test.timers

import android.icu.util.GregorianCalendar
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class LeftScreenViewModel : ViewModel() {

    private val myCounter =  mutableStateOf(0)
    val counter: State<Int> get() = myCounter

    public fun increment() {
        myCounter.value++
    }

    public fun decrement() {
        myCounter.value--
    }
}