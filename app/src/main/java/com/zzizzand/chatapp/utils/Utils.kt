package com.zzizzand.chatapp.utils

import android.app.Activity
import android.graphics.Color
import android.view.View
import android.view.WindowManager
import com.zzizzand.chatapp.R


fun Activity.makeStatusBarTransparent() {
    window.apply {
        clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        statusBarColor = Color.TRANSPARENT
        navigationBarColor = getColor(R.color.colorPrimary)
    }
}

fun String.isValidEmail() =
    this.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
