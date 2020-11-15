package com.vshkl.epoxyhandledraganddropexample

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.EpoxyModelTouchCallback
import com.airbnb.epoxy.EpoxyViewHolder
import com.vshkl.epoxyhandledraganddropexample.epoxymodels.RowEpoxyModel
import com.vshkl.epoxyhandledraganddropexample.epoxymodels.RowEpoxyModel_

class MainEpoxyTouchCallback(
    controller: MainController,
    private val listener: OnRowMoveListener
) : EpoxyModelTouchCallback<RowEpoxyModel>(controller, RowEpoxyModel::class.java) {

    interface OnRowMoveListener {

        fun onMoved(movingRowId: String, shiftingRowId: String)

    }

    override fun onMoved(
        recyclerView: RecyclerView?,
        viewHolder: EpoxyViewHolder?,
        fromPos: Int,
        target: EpoxyViewHolder?,
        toPos: Int,
        x: Int,
        y: Int
    ) {
        val movingRowId = viewHolder?.model
            ?.takeIf { it is RowEpoxyModel_ }
            ?.let { (it as RowEpoxyModel_).rowId }
        val shiftingRowId = target?.model
            ?.takeIf { it is RowEpoxyModel_ }
            ?.let { (it as RowEpoxyModel_).rowId }

        if (movingRowId != null && shiftingRowId != null) {
            listener.onMoved(movingRowId, shiftingRowId)
        }

        super.onMoved(recyclerView, viewHolder, fromPos, target, toPos, x, y)
    }

    override fun getMovementFlagsForModel(model: RowEpoxyModel?, adapterPosition: Int) =
        makeMovementFlags(ItemTouchHelper.UP or ItemTouchHelper.DOWN, 0)

    override fun isLongPressDragEnabled() = false

}
