package com.example.hw1_numbered_block_list

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import java.lang.Integer.max

class SquareTextView: AppCompatTextView {

    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet): super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int): super(context, attrs, defStyle)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
            val width = measuredWidth
            val height = measuredHeight
            val size = max(width, height)
            val widthSpec = MeasureSpec.makeMeasureSpec(size, MeasureSpec.EXACTLY)
            val heightSpec = widthSpec
            super.onMeasure(widthSpec, heightSpec)
    }
}