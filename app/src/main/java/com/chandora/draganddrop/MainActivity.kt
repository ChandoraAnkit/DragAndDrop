package com.chandora.draganddrop

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import kotlinx.android.synthetic.main.activity_main.*

private const val SPAN_COUNT = 3

class MainActivity : AppCompatActivity() {

    private val yogaSteps by lazy {
        arrayListOf(R.drawable.first, R.drawable.second, R.drawable.third, R.drawable.forth,
        R.drawable.fifth, R.drawable.sixth, R.drawable.seventh, R.drawable.eighth,
        R.drawable.ninth, R.drawable.tenth, R.drawable.eleventh, R.drawable.twelveth)
    }

    private val yogaAdapter by lazy {
        YogaStepsAdapter(yogaSteps.toList() as ArrayList<Int>)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setItemTouchCallback()

        recyclerView.layoutManager = GridLayoutManager(this, SPAN_COUNT).apply {
            orientation = GridLayoutManager.VERTICAL
        }
        recyclerView.adapter = yogaAdapter
    }

    private fun setItemTouchCallback(){
        val callback = ItemTouchCallback(yogaAdapter)
        val touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(recyclerView)
    }
}