package com.naver.appLock

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.naver.appLock.R
import com.naver.appLock.databinding.ActivityAlarmBinding

class AlarmActivity : AppCompatActivity() {
    private var wasInBackground = false
    private lateinit var binding:ActivityAlarmBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityAlarmBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.gotohome.setOnClickListener {
            // 홈 화면으로
            val home = Intent(Intent.ACTION_MAIN).apply {
                addCategory(Intent.CATEGORY_HOME)
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            startActivity(home)
        }

        //todo 잠금 푸는거 추가
    }

    override fun onStop() {
        super.onStop()
        wasInBackground = true
    }
    override fun onResume() {
        super.onResume()

        if (wasInBackground) {
            wasInBackground = false // 플래그 초기화

            // MainActivity로 이동
            val intent = Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            startActivity(intent)

            // AlarmActivity 종료
            finish()
        }
    }
}