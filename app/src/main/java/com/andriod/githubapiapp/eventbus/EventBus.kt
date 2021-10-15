package com.andriod.githubapiapp.eventbus

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class EventBus {
    open class Event

    private val _eventObservable = PublishSubject.create<Event>()
    val eventObservable: Observable<Event> get() = _eventObservable

    fun post(event: Event) {
        _eventObservable.onNext(event)
    }
}