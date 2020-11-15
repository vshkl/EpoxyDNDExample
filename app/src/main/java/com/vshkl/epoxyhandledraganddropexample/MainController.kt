package com.vshkl.epoxyhandledraganddropexample

import android.view.MotionEvent
import android.view.View
import com.airbnb.epoxy.EpoxyController
import com.vshkl.epoxyhandledraganddropexample.MainViewModel.Row
import com.vshkl.epoxyhandledraganddropexample.epoxymodels.header
import com.vshkl.epoxyhandledraganddropexample.epoxymodels.row

class MainController(
    private val callbackAdapter: MainCallbackAdapter
) : EpoxyController() {

    private var firstRows = emptyList<Row>()
    private var secondRows = emptyList<Row>()

    override fun buildModels() {
        header {
            id("header", "1")
            titleRes(R.string.txt_header_one)
        }

        firstRows.map { row ->
            row {
                id(row.id)
                rowId(row.id)
                title(row.title)
                onDragHandleTouchListener(this@MainController::onRowDragHandleTouched)
            }
        }

        header {
            id("header", "2")
            titleRes(R.string.txt_header_two)
        }

        secondRows.map { row ->
            row {
                id(row.id)
                rowId(row.id)
                title(row.title)
                onDragHandleTouchListener(this@MainController::onRowDragHandleTouched)
            }
        }
    }

    fun setFirstRowsData(firstRowsData: List<Row>) {
        this.firstRows = firstRowsData
        requestModelBuild()
    }

    fun setSecondRowsData(secondRowsData: List<Row>) {
        this.secondRows = secondRowsData
        requestModelBuild()
    }

    private fun onRowDragHandleTouched(view: View, event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            callbackAdapter.onDragStart()
        }

        return view.performClick()
    }

}
