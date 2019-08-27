package kr.gdg.sooksook.base

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.ViewGroup

abstract class BaseRecyclerView {
    abstract class Adapter<ITEM : Any, VDB : ViewDataBinding>(
        @LayoutRes private val layoutResId: Int, private
        val bindingVariableId: Int? = null
    ) : androidx.recyclerview.widget.RecyclerView.Adapter<ViewHolder<VDB>>() {

        interface ACallback {
            fun onClick(position: Int)
        }

        private var callback: ACallback? = null

        fun setCallback(callback: ACallback) {
            this.callback = callback
        }

        private val items = mutableListOf<ITEM>()

        fun replaceAll(items: List<ITEM>?) {
            items?.let {
                this.items.run {
                    clear()
                    addAll(it)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            object : ViewHolder<VDB>(
                layoutResId = layoutResId,
                parent = parent,
                bindingVariableId = bindingVariableId
            ) {}

        override fun getItemCount(): Int = items.size

        override fun onBindViewHolder(holder: ViewHolder<VDB>, position: Int) {
            holder.bind(items[position], position, callback)
        }
    }

    abstract class ViewHolder<VDB : ViewDataBinding>(
        @LayoutRes layoutResId: Int, parent: ViewGroup,
        private val bindingVariableId: Int?
    ) : androidx.recyclerview.widget.RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)) {

        private val binding: VDB = DataBindingUtil.bind(itemView)!!

        fun bind(item: Any?, position: Int, callback: Adapter.ACallback?) {
            try {
                bindingVariableId?.let {
                    binding.setVariable(it, item)
                }

                callback?.let { callbackOnClick ->
                    binding.root.setOnClickListener {
                        callbackOnClick.onClick(position)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        fun getBinding() = binding
    }
}