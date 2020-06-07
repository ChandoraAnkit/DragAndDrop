package com.chandora.draganddrop

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView


/**
 * Created by Ankit
 */


class ItemTouchCallback(private val moveListener: OnItemTouchCallback)
    : ItemTouchHelper.Callback() {

    private var dragFromPosition = -1
    private var dragToPosition = -1

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN or
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        return makeMovementFlags(dragFlags, 0)
    }

    override fun isLongPressDragEnabled() = true

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                        target: RecyclerView.ViewHolder
    ): Boolean {
        dragToPosition = target.adapterPosition
        return false
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {

        when (actionState) {
            ItemTouchHelper.ACTION_STATE_DRAG -> {
                viewHolder?.apply {
                    if (viewHolder is ItemTouchHelperViewHolder) {
                        dragFromPosition = viewHolder.adapterPosition
                        viewHolder.onItemSelected()
                    }
                }

            }
            ItemTouchHelper.ACTION_STATE_IDLE -> {
                if (dragFromPosition != -1 && dragToPosition != -1
                    && dragFromPosition != dragToPosition) {
                    moveListener.onItemMove(dragFromPosition, dragToPosition)
                    dragFromPosition = -1
                    dragToPosition = -1
                }
            }
        }

        super.onSelectedChanged(viewHolder, actionState)
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        if (viewHolder is ItemTouchHelperViewHolder) {
            viewHolder.onItemClear()
        }
        super.clearView(recyclerView, viewHolder)
    }


    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}
}