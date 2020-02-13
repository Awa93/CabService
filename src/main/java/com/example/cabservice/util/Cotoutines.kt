package com.example.cabservice.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object Cotoutines {

    fun main(work:suspend (()->Unit))=
        CoroutineScope(Dispatchers.Main).launch {
            work()
        }
}