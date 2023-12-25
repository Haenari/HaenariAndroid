package com.haenari.haenari.presentation.util

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission

object Permissions {

    private val LOCATION_PERMISSIONS = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    fun isGranted(context: Context, vararg permissions: String): Boolean {
        permissions.map { permission ->
            if(context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED) return true
        }
        return false
    }

    // todo fix tedPermission
    fun requestLocationPermission(onPermissionGranted: (() -> Unit)?, onPermissionDenied: (() -> Unit)?) {
        TedPermission.create()
            .setPermissionListener(object : PermissionListener {
                override fun onPermissionGranted() {
                    onPermissionGranted?.invoke()
                }

                override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                    onPermissionDenied?.invoke()
                }
            })
            .setDeniedMessage("위치 권한을 허용하지 않으면 날씨 정보를 얻을 수 없습니다.")
            .setDeniedCloseButtonText("그냥 사용하기")
            .setGotoSettingButtonText("설정으로 이동")
            .setPermissions(*LOCATION_PERMISSIONS)
            .check()
    }
}