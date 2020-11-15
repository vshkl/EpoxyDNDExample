package com.vshkl.epoxyhandledraganddropexample

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vshkl.epoxyhandledraganddropexample.MainEpoxyTouchCallback.OnRowMoveListener
import com.vshkl.epoxyhandledraganddropexample.MainSimpleOnItemTouchListener.OnInterceptTouchEventListener
import com.vshkl.epoxyhandledraganddropexample.epoxymodels.RowEpoxyModel_
import kotlinx.android.synthetic.main.activity_main.erv_list

class MainActivity : AppCompatActivity(R.layout.activity_main),
    MainCallbackAdapter, OnInterceptTouchEventListener, OnRowMoveListener {

    private val viewModel by viewModels<MainViewModel>()
    private val controller = MainController(this)
    private var touchHelper: ItemTouchHelper? = null
    private var touchedPosition = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupList()
        observeRowsData()
    }

    /** @see MainCallbackAdapter */

    override fun onDragStart() {
        if (touchedPosition >= 0) {
            val touchedModel = controller.adapter.getModelAtPosition(touchedPosition)
            if (touchedModel is RowEpoxyModel_) {
                val touchedViewHolder = controller.adapter.boundViewHolders.getHolderForModel(touchedModel)
                if (touchedViewHolder != null) {
                    touchHelper?.startDrag(touchedViewHolder)
                }
            }
        }
    }

    /** @see OnInterceptTouchEventListener */

    override fun onInterceptTouchEvent(touchedPosition: Int) {
        this.touchedPosition = touchedPosition
    }

    /** @see OnRowMoveListener */

    override fun onMoved(movingRowId: String, shiftingRowId: String) {
        viewModel.moveRow(movingRowId, shiftingRowId)
    }

    private fun setupList() {
        val layoutManager = LinearLayoutManager(this)

        erv_list.layoutManager = layoutManager
        erv_list.setControllerAndBuildModels(controller)
        erv_list.addOnItemTouchListener(MainSimpleOnItemTouchListener(this))
        erv_list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        setupTouchHelper(erv_list)
    }

    private fun setupTouchHelper(recyclerView: RecyclerView) {
        touchHelper = ItemTouchHelper(MainEpoxyTouchCallback(controller, this))
            .apply { attachToRecyclerView(recyclerView) }
    }

    private fun observeRowsData() {
        viewModel.firstRowsData.observe(this, { controller.setFirstRowsData(it) })
        viewModel.secondRowsData.observe(this, { controller.setSecondRowsData(it) })
    }

}
