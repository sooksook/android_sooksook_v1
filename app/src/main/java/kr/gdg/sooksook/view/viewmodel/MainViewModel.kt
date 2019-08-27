package kr.gdg.sooksook.view.viewmodel

import androidx.databinding.*
import android.view.View
import kr.gdg.sooksook.base.BaseViewModel
import kr.gdg.sooksook.R

class MainViewModel: BaseViewModel() {
    var mainText: ObservableField<Int> = ObservableField()
    var mainImage1: ObservableField<Int> = ObservableField()
    var mainImage2: ObservableField<Int> = ObservableField()
    var mainImage3: ObservableField<Int> = ObservableField()

//    val name = ObservableField<String>()
//    val email = ObservableField<String>()
//    val score = ObservableInt()
//    val isValid = ObservableBoolean()

    override fun onCreate() {
        mainText.set(R.string.main_title)
        mainImage1.set(R.drawable.custom_search_box)
        mainImage2.set(R.drawable.top_search)
        mainImage3.set(R.drawable.img_ss_main)

//        score.set(0)
//        isValid.set(false)
//
//        name.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
//            override fun onPropertyChanged(observable: Observable, i: Int) {
//                validation()
//            }
//        })
//
//        email.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
//            override fun onPropertyChanged(observable: Observable, i: Int) {
//                validation()
//            }
//        })
    }

    override fun onResume() {}

    override fun onPause() {}

    override fun onDestroy() {}

    fun showCurrentTime() {
//        mainText.set(System.currentTimeMillis().toString())
    }

    var currentTime2ClickListener: View.OnClickListener = View.OnClickListener { showCurrentTime() }

//    private fun validation() {
//        val isValidName = !TextUtils.isEmpty(name.get())
//        val isValidEmail = !TextUtils.isEmpty(email.get()) && Patterns.EMAIL_ADDRESS.matcher(email.get()).matches()
//        val isValidScore = score.get() > 0
//        isValid.set(isValidName && isValidEmail && isValidScore)
//    }
//
//    var scoreChangeListener: RatingBar.OnRatingBarChangeListener =
//        RatingBar.OnRatingBarChangeListener { _, v, _ ->
//            score.set(v.toInt())
//            validation()
//        }

    fun onClickChangeData() {
//        mainText.set("score = ${score.get()}, name = ${name.get()}, email = ${email.get()}")
    }
}