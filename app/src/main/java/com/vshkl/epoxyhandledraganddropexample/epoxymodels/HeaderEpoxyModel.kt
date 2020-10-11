package com.vshkl.epoxyhandledraganddropexample.epoxymodels

import android.widget.TextView
import androidx.annotation.StringRes
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.vshkl.epoxyhandledraganddropexample.R
import com.vshkl.epoxyhandledraganddropexample.epoxymodels.HeaderEpoxyModel.HeaderViewHolder
import com.vshkl.epoxyhandledraganddropexample.helpers.KotlinEpoxyHolder

@EpoxyModelClass(layout = R.layout.item_model_header)
abstract class HeaderEpoxyModel : EpoxyModelWithHolder<HeaderViewHolder>() {

    @EpoxyAttribute @StringRes var titleRes = 0

    override fun bind(holder: HeaderViewHolder) {
        holder.tvTitle.setText(titleRes)
    }

    class HeaderViewHolder : KotlinEpoxyHolder() {
        val tvTitle by bind<TextView>(R.id.tv_title)
    }

}
