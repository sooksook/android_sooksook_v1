package kr.gdg.sooksook.view.activity

import kr.gdg.sooksook.R
import kr.gdg.sooksook.base.BaseActivity
import kr.gdg.sooksook.common.Dlog
import kr.gdg.sooksook.databinding.ActivityUserInfoBinding
import kr.gdg.sooksook.util.extensions.showToast

enum class UserInfoIndex {
    BOOK_MARK, CONTACT, LOGOUT
}

class UserInfoActivity: BaseActivity<ActivityUserInfoBinding>() {
    override val getContentView: Int
        get() = R.layout.activity_user_info

    override fun initView() {
        Dlog.i("initView")
        getBinding().activity = this
    }

    override fun onClick() {
        super.onClick()
        getBinding().userInfoTvBookmark.setOnClickListener { onClickMenu(UserInfoIndex.BOOK_MARK) }
        getBinding().userInfoTvContact.setOnClickListener { onClickMenu(UserInfoIndex.CONTACT) }
        getBinding().userInfoTvLogout.setOnClickListener { onClickMenu(UserInfoIndex.LOGOUT) }
    }

    fun onClickClose() {
        finish()
    }

    private fun onClickMenu(index: UserInfoIndex) {
        when(index) {
            UserInfoIndex.BOOK_MARK -> {
                showToast("즐겨찾기")
            }

            UserInfoIndex.CONTACT -> {
                showToast("문의하기")
            }

            UserInfoIndex.LOGOUT -> {
                showToast("로그아웃")
            }
        }
    }
}