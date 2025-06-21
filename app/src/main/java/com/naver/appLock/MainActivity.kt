package com.naver.appLock

import android.accessibilityservice.AccessibilityService
import android.annotation.TargetApi
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.health.connect.datatypes.AppInfo
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.naver.appLock.databinding.ActivityMainBinding
import com.naver.appLock.databinding.AppListItemBinding

class MainActivity : AppCompatActivity() { @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private lateinit var binding:ActivityMainBinding
    var defaultmode=false
    var onoff = defaultmode
    data class AppInfo(
        val name: String,
        val icon: Drawable,
        var isChecked: Boolean = false
    )

    // binding 최소 api 가 33 이라며 오류 뜨길래 TargetApi 추가
    @TargetApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // 사용자가 권한을 확인하면, 화면에 뭔가 바뀔 때 마다 생기는 이벤트를 받아서 그 앱 이름을 확인함
        // 앱 이름 확인하고 잠금 대상이면 종료하는 로직은 AppLockAccessibilityService 에 있음

        // ACTION_ACCESSIBILITY_SETTINGS 권한 확인
        if (!isAccessibilityServiceEnabled(this, AppLockAccessibilityService::class.java)) {
            val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
            startActivity(intent)
        }

        binding.onoffbtn.setOnClickListener {
            val pref = getSharedPreferences("AppPref",Context.MODE_PRIVATE)
            val edit = pref.edit()
            edit.putBoolean("onoff",!onoff) // 기존 상태 반전
            edit.apply()
            onoff=!onoff

            // 버튼 글자 바꾸기
            if(onoff){
                binding.onoffbtn.text="끄기"
            }else{
                binding.onoffbtn.text="켜기"
            }
        }

        binding.editlockappsbtn.setOnClickListener {
            val intent=Intent(this,EditLockAppActivity::class.java)
            startActivity(intent)
        }
    }

    // ACTION_ACCESSIBILITY_SETTINGS 권한 확인 함수
    fun isAccessibilityServiceEnabled(context: Context, serviceClass: Class<out AccessibilityService>): Boolean {
        val expectedComponentName = ComponentName(context, serviceClass)
        val enabledServices = Settings.Secure.getString(
            context.contentResolver,
            Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
        ) ?: return false

        return enabledServices.split(":").any {
            ComponentName.unflattenFromString(it) == expectedComponentName
        }
    }

    /*
            val applist=packageManager.getInstalledPackages(0)
        for (perm in permissions) {
            val instargramPermission=packageManager.checkPermission(perm,"com.instagram.android")
            Log.d("sua",instargramPermission.toString())
        }
        if (!hasUsageStatsPermission()) {
            val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
//            val intent = Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS)
            startActivity(intent)
        }
        else{
            Log.d("sua","permission ok")
//            findForegroundEvents()
        }
        for (appinfo in applist){
            val appname=appinfo.packageName
            val appPackage=appinfo.packageName
            Log.d("sua",appPackage.toString())
            if (appPackage=="com.instagram.android"){
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
    * */
}

