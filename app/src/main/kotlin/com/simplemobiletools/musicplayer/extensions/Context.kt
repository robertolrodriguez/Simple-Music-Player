package com.simplemobiletools.musicplayer.extensions

import android.content.Context
import android.content.Intent
import android.util.TypedValue
import com.simplemobiletools.musicplayer.R
import com.simplemobiletools.musicplayer.helpers.*
import com.simplemobiletools.musicplayer.services.MusicService

fun Context.sendIntent(action: String) {
    Intent(this, MusicService::class.java).apply {
        this.action = action
        try {
            startService(this)
        } catch (ignored: Exception) {
        }
    }
}

val Context.config: Config get() = Config.newInstance(applicationContext)

val Context.dbHelper: DBHelper get() = DBHelper.newInstance(applicationContext)

fun Context.playlistChanged(newID: Int) {
    config.currentPlaylist = newID
    sendIntent(PAUSE)
    sendIntent(REFRESH_LIST)
    sendIntent(SETUP)
}

fun Context.getActionBarHeight(): Int {
    val textSizeAttr = intArrayOf(R.attr.actionBarSize)
    val attrs = obtainStyledAttributes(TypedValue().data, textSizeAttr)
    val actionBarSize = attrs.getDimensionPixelSize(0, -1)
    attrs.recycle()
    return actionBarSize
}
