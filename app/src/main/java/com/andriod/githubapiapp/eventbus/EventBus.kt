package com.andriod.githubapiapp.eventbus

import com.andriod.githubapiapp.entity.User
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

object EventBus {
    open class Event(val user: User)

    private val _eventObservable = PublishSubject.create<Event>()
    val eventObservable: Observable<Event> get() = _eventObservable

    fun post(event: Event) {
        _eventObservable.onNext(event)
    }
}