package com.naver.appLock

import android.app.AppOpsManager
import android.app.usage.UsageEvents
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() { @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val applist=packageManager.getInstalledPackages(0)
//        for (perm in permissions) {
//            val instargramPermission=packageManager.checkPermission(perm,"com.instagram.android")
//            Log.d("sua",instargramPermission.toString())
//        }
        //
        if (!hasUsageStatsPermission()) {
            val intent = Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS)
            startActivity(intent)
        }
        else{
            findForegroundEvents()
        }

//        for (appinfo in applist){
//            val appname=appinfo.packageName
//            val appPackage=appinfo.packageName
//            Log.d("sua",appPackage.toString())
//            if (appPackage=="com.instagram.android"){
//            }

    }
    private fun hasUsageStatsPermission(): Boolean {
        val appOpsManager = getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
        val mode = appOpsManager.checkOpNoThrow(
            AppOpsManager.OPSTR_GET_USAGE_STATS,
            android.os.Process.myUid(),
            packageName
        )
        return mode == AppOpsManager.MODE_ALLOWED
    }

    private fun findForegroundEvents() {
        val usageStatsManager = getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
        val endTime = System.currentTimeMillis()
        val beginTime = endTime - 1000*60*5 // 최근 5분 동안의 데이터 확인
        val usageEvents = usageStatsManager.queryEvents(beginTime, endTime)

        while (usageEvents.hasNextEvent()) {
            val event = UsageEvents.Event()
            usageEvents.getNextEvent(event)
            if (event.eventType == UsageEvents.Event.ACTIVITY_RESUMED ) { //&& event.packageName == "com.instagram.android"
                // 타겟 앱이 실행됨을 감지했을 때 임시 알람 화면으로 리다이렉트
                Log.d("sua", event.packageName)
                val intent = Intent(this, AlarmActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
        }
    }
}

