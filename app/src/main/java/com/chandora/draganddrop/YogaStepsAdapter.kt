package com.chandora.draganddrop

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_custom_layout.view.*
import kotlin.collections.ArrayList


/**
 * Created by Ankit
 */


class YogaStepsAdapter(private val mYogaSteps: ArrayList<Int>)
    : RecyclerView.Adapter<YogaStepsAdapter.YogaStepViewHolder>(), OnItemTouchCallback{


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YogaStepViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_custom_layout,
            parent, false)
        return YogaStepViewHolder(view)
    }

    override fun getItemCount() = mYogaSteps.size

    override fun onBindViewHolder(holder: YogaStepViewHolder, position: Int) {
       holder.bind(mYogaSteps[position])
    }

    class YogaStepViewHolder(private val view: View): RecyclerView.ViewHolder(view),
        ItemTouchHelperViewHolder {

        fun bind(drawable: Int){
            view.stepIv.setImageDrawable(ContextCompat.getDrawable(view.context, drawable))
        }

        override fun onItemSelected() {
            //Set any background to the selected image.
        }

        override fun onItemClear() {
            //Clear the background
        }
    }

    //This method will update the recycler view when the user swap.
    override fun onItemMove(fromPos: Int, toPos: Int){
        val temp: Int = mYogaSteps[fromPos]
        mYogaSteps[fromPos] = mYogaSteps[toPos]
        mYogaSteps[toPos] = temp
        notifyItemChanged(fromPos)
        notifyItemChanged(toPos)
    }
}