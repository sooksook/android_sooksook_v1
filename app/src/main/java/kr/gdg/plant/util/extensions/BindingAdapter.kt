package kr.gdg.plant.util.extensions

import androidx.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

object BindingAdapters {

    @BindingAdapter("app:loadImage")
    @JvmStatic fun ImageView.loadImage(imageUrl: Int) {
        Glide.with(this.context)
            .load(imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.SOURCE)
            .into(this)
    }

    @BindingAdapter("app:loadImageDrawable")
    @JvmStatic fun ImageView.loadImageDrawable(resId: Int) {
        this.setBackgroundResource(resId)
    }
}