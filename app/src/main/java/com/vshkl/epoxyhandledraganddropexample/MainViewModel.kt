package com.vshkl.epoxyhandledraganddropexample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.util.UUID
import kotlin.math.floor

class MainViewModel : ViewModel() {

    private val rowsData = MutableLiveData<MutableList<Row>>()
    val firstRowsData: LiveData<MutableList<Row>> = Transformations.map(rowsData) {
        it.subList(0, floor((it.size / 2).toDouble()).toInt())
    }
    val secondRowsData: LiveData<MutableList<Row>> = Transformations.map(rowsData) {
        it.subList(floor((it.size / 2).toDouble()).toInt(), it.size)
    }

    init {
        rowsData.value = MutableList(10) { Row(id = UUID.randomUUID().toString(), title = "Row #${it + 1}") }
    }

    fun moveRow(movingRowId: String, shiftingRowId: String) {
        val movingRow = rowsData.value?.find { it.id == movingRowId }
        val shiftingRow = rowsData.value?.find { it.id == shiftingRowId }

        if (movingRow != null && shiftingRow != null) {
            rowsData.value
                ?.indexOf(shiftingRow)
                ?.let { moveRowToPosition(it, movingRow) }
        }
    }

    private fun moveRowToPosition(position: Int, movingRow: Row) {
        rowsData.value?.remove(movingRow)
        rowsData.value?.add(position, movingRow)
        rowsData.notifyObserver()
    }

    private fun <T> MutableLiveData<T>.notifyObserver() {
        this.value = this.value
    }

    data class Row(
        val id: String,
        val title: String
    )

}
