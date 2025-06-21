package com.naver.appLock

import android.accessibilityservice.AccessibilityService
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.accessibility.AccessibilityEvent

class AppLockAccessibilityService : AccessibilityService() {
    companion object {
        var lockedPackageList: List<String> = emptyList()
    }

    override fun onServiceConnected() {
        super.onServiceConnected()
        //todo  pref에서 lockedPackageList 값 가져오기
        val pref = getSharedPreferences("AppPref", Context.MODE_PRIVATE)
        lockedPackageList=loadCheckedApps(this).toList()

        Log.d("sua", "서비스 연결됨")
    }
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        Log.d("sua","onAccessibilityEvent 실행됨")
        if (event?.eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            val packageName = event.packageName?.toString()
            Log.d("sua", "Foreground package: $packageName")

            // 차단 대상 앱이면 차단 알람 화면으로 전환
            if (packageName in lockedPackageList) {
                val intent = Intent(this, AlarmActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
        }
    }
    override fun onInterrupt() {
        // override 용
    }

    fun loadCheckedApps(context: Context): Set<String> {
        val prefs = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
        return prefs.getStringSet("locked_apps", emptySet()) ?: emptySet()
    }
}

