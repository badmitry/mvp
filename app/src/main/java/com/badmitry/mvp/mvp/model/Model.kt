package com.badmitry.mvp.mvp.model

class Model : IModel {
    private val counters = mutableListOf(0, 0, 0)

    override fun plusValue(index: Int): Int {
        return ++counters[index]
    }
}