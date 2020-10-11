package com.vshkl.epoxyhandledraganddropexample.epoxymodels

import android.annotation.SuppressLint
import android.view.View.OnTouchListener
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyAttribute.Option.DoNotHash
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.vshkl.epoxyhandledraganddropexample.R
import com.vshkl.epoxyhandledraganddropexample.epoxymodels.RowEpoxyModel.RowViewHolder
import com.vshkl.epoxyhandledraganddropexample.helpers.KotlinEpoxyHolder

@EpoxyModelClass(layout = R.layout.item_model_header)
abstract class RowEpoxyModel : EpoxyModelWithHolder<RowViewHolder>() {

    @EpoxyAttribute var title = ""
    @EpoxyAttribute(DoNotHash) var onDragHandleTouchListener: OnTouchListener? = null

    @SuppressLint("ClickableViewAccessibility")
    override fun bind(holder: RowViewHolder) {
        with(holder) {
            tvTitle.text = title
            ivDragHandle.setOnTouchListener(onDragHandleTouchListener)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun unbind(holder: RowViewHolder) {
        with(holder) {
            ivDragHandle.setOnTouchListener(onDragHandleTouchListener)
        }
    }

    class RowViewHolder : KotlinEpoxyHolder() {
        val tvTitle by bind<TextView>(R.id.tv_title)
        val ivDragHandle by bind<ImageView>(R.id.iv_drag_handle)
    }

}
