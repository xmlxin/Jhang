package com.xiaoxin.library

import android.app.Activity
import android.content.Intent

inline fun <reified T : Activity> Activity.jump(finish: Boolean = false) {
    startActivity(Intent(this, T::class.java))
    if (finish) {
        finish()
    }
}