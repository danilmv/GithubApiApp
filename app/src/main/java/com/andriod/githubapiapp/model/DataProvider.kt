package com.andriod.githubapiapp.model

import android.os.Handler
import android.os.HandlerThread
import com.andriod.githubapiapp.entity.User

abstract class DataProvider {
    protected val _users = mutableListOf<User>()
    val users: List<User>
        get() = _users

    private val handlerThread = HandlerThread("handlerThread").apply { isDaemon = true;start() }
    protected val handler = Handler(handlerThread.looper)

    private val subscribers = mutableSetOf<() -> Unit>()

    abstract fun readData()
    fun subscribe(subscriber: () -> Unit) {
        subscribers.add(subscriber)
    }

    fun unSubscribe(subscriber: () -> Unit) {
        subscribers.remove(subscriber)
    }

    protected fun notifySubscribers() {
        subscribers.forEach { it.invoke() }
    }

    companion object {
        const val SLEEP_TIME = 1000L
    }
}