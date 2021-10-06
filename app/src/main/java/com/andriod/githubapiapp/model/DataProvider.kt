package com.andriod.githubapiapp.model

import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.util.Log
import com.andriod.githubapiapp.entity.User

abstract class DataProvider {
    protected val _users = mutableListOf<User>()
    val users: List<User>
        get() = _users

    private val handlerThread = HandlerThread("handlerThread").apply { isDaemon = true;start() }
    protected val dataHandler = Handler(handlerThread.looper)

    private val handler = Handler(Looper.getMainLooper())
    private val subscribers = HashSet<SubscriberType>()

    abstract fun readData()
    fun subscribe(subscriber: SubscriberType) {
        subscribers.add(subscriber)
        Log.d(TAG, "subscribe() called with: subscriber = $subscriber numOfSubscribers: ${subscribers.size}")
    }

    fun unSubscribe(subscriber: SubscriberType) {
        subscribers.remove(subscriber)
        Log.d(TAG, "unSubscribe() called with: subscriber = $subscriber numOfSubscribers: ${subscribers.size}")
    }

    protected fun notifySubscribers() {
        Log.d(TAG, "notifySubscribers() called numOfSubscribers: ${subscribers.size}")
        subscribers.forEach { handler.post(it) }
    }

    companion object {
        const val SLEEP_TIME = 1000L
        const val TAG = "@@DataProvider"
    }
}

typealias SubscriberType = ()->Unit