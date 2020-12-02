package com.badmitry.github.mvp.model.image

interface IImageLoader<T> {
    fun loadInto(url: String?, container: T)
}