package kr.gdg.sooksook.view.viewmodel

import android.view.View
import androidx.databinding.ObservableField
import kr.gdg.sooksook.R
import kr.gdg.sooksook.base.BaseViewModel

class SearchViewModel: BaseViewModel() {
    var searchImage1: ObservableField<Int> = ObservableField()
    var searchImage2: ObservableField<Int> = ObservableField()
    var searchImage3: ObservableField<Int> = ObservableField()

//    val name = ObservableField<String>()
//    val email = ObservableField<String>()
//    val score = ObservableInt()
//    val isValid = ObservableBoolean()

    override fun onCreate() {
        searchImage1.set(R.drawable.custom_search_box2)
        searchImage2.set(R.drawable.back)
        searchImage3.set(R.drawable.img_ss_main)

//        score.set(0)
//        isValid.set(false)
//
//        name.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
//            override fun onPropertyChanged(observable: Observable, i: Int) {
//                validation()
//            }
//        })

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
//        searchText.set(System.currentTimeMillis().toString())
    }

    var currentTime2ClickListener: View.OnClickListener = View.OnClickListener { showCurrentTime() }

    private fun validation() {
//        val isValidName = !TextUtils.isEmpty(name.get())
//        val isValidEmail = !TextUtils.isEmpty(email.get()) && Patterns.EMAIL_ADDRESS.matcher(email.get()).matches()
//        val isValidScore = score.get() > 0
//        isValid.set(isValidName && isValidEmail && isValidScore)
    }

//    var scoreChangeListener: RatingBar.OnRatingBarChangeListener =
//        RatingBar.OnRatingBarChangeListener { _, v, _ ->
//            score.set(v.toInt())
//            validation()
//        }

    fun onClickChangeData() {
//        searchText.set("score = ${score.get()}, name = ${name.get()}, email = ${email.get()}")
    }
}