package com.arttttt.profirumvp.utils

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import com.arttttt.profirumvp.base.SingletonHolder

class PermissionsManager private constructor() {
    companion object : SingletonHolder<PermissionsManager>(::PermissionsManager)

    fun checkPermission(context: Context, permission: String): Boolean {
        return (ContextCompat.checkSelfPermission(context, permission)
                == PackageManager.PERMISSION_GRANTED)
    }

    fun requestPermissions(activity: Activity, permissions: Array<String>, requestCode: Int, shouldShowRequestPermissionRationaleListener: OnShouldShowRequestPermissionRationaleListener?) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permissions[0]) && shouldShowRequestPermissionRationaleListener != null) {
            shouldShowRequestPermissionRationaleListener.callback()
        } else {
            ActivityCompat.requestPermissions(activity, permissions, requestCode)
        }
    }

    fun requestPermissions(fragment: Fragment, permissions: Array<String>, requestCode: Int, shouldShowRequestPermissionRationaleListener: OnShouldShowRequestPermissionRationaleListener?) {
        if (fragment.shouldShowRequestPermissionRationale(permissions[0]) && shouldShowRequestPermissionRationaleListener != null) {
            shouldShowRequestPermissionRationaleListener.callback()
        } else {
            fragment.requestPermissions(permissions, requestCode)
        }
    }

    interface OnShouldShowRequestPermissionRationaleListener {
        fun callback()
    }
}