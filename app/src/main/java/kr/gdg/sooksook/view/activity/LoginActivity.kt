package kr.gdg.sooksook.view.activity

import android.content.Intent
import kr.gdg.sooksook.R
import kr.gdg.sooksook.base.BaseActivity
import kr.gdg.sooksook.common.Dlog
import kr.gdg.sooksook.databinding.ActivityLoginBinding
import kr.gdg.sooksook.util.extensions.showToast

enum class LoginIndex {
    KAKAO, GOOGLE
}

class LoginActivity: BaseActivity<ActivityLoginBinding>() {
    override val getContentView: Int
        get() = R.layout.activity_login

    override fun initView() {
        Dlog.i("initView")

    }

    fun onClickSns(index: LoginIndex) {
        when(index) {
            LoginIndex.KAKAO -> {
                showToast("카카오 로그인")
                startActivity(Intent(this, MainActivity::class.java))
            }

            LoginIndex.GOOGLE -> {
                showToast("구글 로그인")
            }
        }
    }
}