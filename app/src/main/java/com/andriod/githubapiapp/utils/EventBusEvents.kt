package com.andriod.githubapiapp.utils

import com.andriod.githubapiapp.entity.User
import com.andriod.githubapiapp.eventbus.EventBus

class EventLike(user: User): EventBus.Event(user)
class EventDislike(user: User): EventBus.Event(user)