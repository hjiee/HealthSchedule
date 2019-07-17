package com.example.healthschedule.view.registration

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.content.res.TypedArray
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.core.widget.NestedScrollView
import com.example.healthschedule.R

import java.util.ArrayList

class ExpansionLayout : NestedScrollView {

    private val indicatorListeners = ArrayList<IndicatorListener>()
    private val listeners = ArrayList<Listener>()
    var isExpanded = false
        private set
    private var animator: Animator? = null

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        requestDisallowInterceptTouchEvent(true)

        if (attrs != null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.ExpansionLayout)
            if (a != null) {
                isExpanded = a.getBoolean(R.styleable.ExpansionLayout_expansion_expanded, isExpanded)
                a.recycle()
            }
        }
    }

    fun addListener(listener: Listener?) {
        if (listener != null && !listeners.contains(listener))
            listeners.add(listener)
    }

    fun removeListener(listener: Listener?) {
        if (listener != null) {
            listeners.remove(listener)
        }
    }

    fun addIndicatorListener(listener: IndicatorListener?) {
        if (listener != null && !indicatorListeners.contains(listener))
            indicatorListeners.add(listener)
    }

    fun removeIndicatorListener(listener: IndicatorListener?) {
        if (listener != null) {
            indicatorListeners.remove(listener)
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (!isExpanded) {
            setHeight(0f)
        }
    }

    override fun addView(child: View) {
        if (childCount > 0) {
            throw IllegalStateException("ExpansionLayout can host only one direct child")
        }

        super.addView(child)
        onViewAdded()
    }

    override fun addView(child: View, index: Int) {
        if (childCount > 0) {
            throw IllegalStateException("ExpansionLayout can host only one direct child")
        }

        super.addView(child, index)
        onViewAdded()
    }

    override fun addView(child: View, params: ViewGroup.LayoutParams) {
        if (childCount > 0) {
            throw IllegalStateException("ExpansionLayout can host only one direct child")
        }

        super.addView(child, params)
        onViewAdded()
    }

    override fun addView(child: View, index: Int, params: ViewGroup.LayoutParams) {
        if (childCount > 0) {
            throw IllegalStateException("ExpansionLayout can host only one direct child")
        }

        super.addView(child, index, params)
        onViewAdded()
    }

    private fun onViewAdded() {
        if (childCount != 0) {
            val childView = getChildAt(0)
            childView.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    childView.viewTreeObserver.removeOnPreDrawListener(this)

                    //now we have a size
                    if (isExpanded) {
                        expand(false)
                    }

                    childView.addOnLayoutChangeListener { view, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom ->
                        if (isExpanded && animator == null) {
                            val height = bottom - top
                            post { setHeight(height.toFloat()) }
                        }
                    }

                    return true
                }
            })
        }
    }

    fun collapse(animated: Boolean) {
        if (!isEnabled || !isExpanded) {
            return
        }
        pingIndicatorListeners(false)
        if (animated) {
            val valueAnimator = ValueAnimator.ofFloat(1f * height, 0f)
            valueAnimator.addUpdateListener { valueAnimator -> setHeight(valueAnimator.animatedValue as Float) }
            valueAnimator.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                    animator = null
                    pingListeners()
                }
            })
            isExpanded = false
            animator = valueAnimator
            valueAnimator.start()
        } else {
            setHeight(0f)
            isExpanded = false
            pingListeners()
        }
    }

    private fun pingIndicatorListeners(willBeExpanded: Boolean) {
        for (indicatorListener in indicatorListeners) {
            indicatorListener?.onStartedExpand(this, willBeExpanded)
        }
    }

    private fun pingListeners() {
        for (listener in listeners) {
            listener?.onExpansionChanged(this, isExpanded)
        }
    }

    fun expand(animated: Boolean) {
        if (!isEnabled || isExpanded) {
            return
        }

        pingIndicatorListeners(true)
        if (animated) {
            val valueAnimator = ValueAnimator.ofFloat(0f, getChildAt(0).height.toFloat())
            valueAnimator.addUpdateListener { valueAnimator -> setHeight(valueAnimator.animatedValue as Float) }
            valueAnimator.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                    animator = null
                    pingListeners()
                }
            })
            isExpanded = true
            animator = valueAnimator
            valueAnimator.start()
        } else {
            setHeight(getChildAt(0).height.toFloat())
            isExpanded = true
            pingListeners()
        }
    }

    private fun setHeight(height: Float) {
        val layoutParams = layoutParams
        if (layoutParams != null) {
            layoutParams.height = height.toInt()
            setLayoutParams(layoutParams)
        }
    }

    fun toggle(animated: Boolean) {
        if (isExpanded) {
            collapse(animated)
        } else {
            expand(animated)
        }
    }

    override fun onSaveInstanceState(): Parcelable? {
        val savedInstance = Bundle()
        savedInstance.putParcelable("super", super.onSaveInstanceState())
        savedInstance.putBoolean("expanded", isExpanded)
        return savedInstance
    }

    override fun onRestoreInstanceState(state: Parcelable) {
        if (state is Bundle) {
            val expanded = state.getBoolean("expanded")
            if (expanded) {
                expand(false)
            } else {
                collapse(false)
            }
            super.onRestoreInstanceState(state.getParcelable("super"))
        } else {
            super.onRestoreInstanceState(state)
        }
    }

    interface Listener {
        fun onExpansionChanged(expansionLayout: ExpansionLayout, expanded: Boolean)
    }

    interface IndicatorListener {
        fun onStartedExpand(expansionLayout: ExpansionLayout, willExpand: Boolean)
    }
}
