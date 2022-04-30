package com.andriod.githubapiapp.utils

import android.os.Handler

fun Handler.postDelayed(delayMillis: Long, r: Runnable) = postDelayed(r, delayMillis)