package kr.gdg.plant.view.activity

import android.content.Intent
import android.os.Handler
import kr.gdg.plant.R
import kr.gdg.plant.base.BaseActivity
import kr.gdg.plant.common.Dlog
import kr.gdg.plant.data.RemoteSource
import kr.gdg.plant.databinding.ActivitySplashBinding

class SplashActivity: BaseActivity<ActivitySplashBinding>() {
    override val getContentView: Int
        get() = R.layout.activity_splash

    override fun initView() {
        Dlog.i("initView")
        RemoteSource.readOnlySpreadSheet()
        RemoteSource.setCallback(object : RemoteSource.OCallback {
            override fun getDataFromSpeadSheet(isGetData: Boolean) {
                Dlog.i("isGetData = $isGetData")
                if(isGetData) {
                    Handler().postDelayed({
                        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                        finish()
                    }, 1000)
                }
            }
        })
    }
}