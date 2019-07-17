package com.example.healthschedule.view.registration

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Context
import android.content.res.TypedArray
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.FrameLayout
import com.example.healthschedule.R

class ExpansionHeader : FrameLayout {

    internal var headerIndicatorId = 0
    internal var expansionLayoutId = 0
    var isToggleOnClick = true
    var headerIndicator: View? = null
        internal set
    internal var expansionLayout: ExpansionLayout? = null
    internal var indicatorAnimator: Animator? = null
    private var headerRotationExpanded = 270
    private var headerRotationCollapsed = 90
    private var expansionLayoutInitialised = false

    val isExpanded: Boolean
        get() = expansionLayout != null && expansionLayout!!.isExpanded

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        if (attrs != null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.ExpansionHeader)
            if (a != null) {
                setHeaderRotationExpanded(
                    a.getInt(
                        R.styleable.ExpansionHeader_expansion_headerIndicatorRotationExpanded,
                        headerRotationExpanded
                    )
                )
                setHeaderRotationCollapsed(
                    a.getInt(
                        R.styleable.ExpansionHeader_expansion_headerIndicatorRotationCollapsed,
                        headerRotationCollapsed
                    )
                )
                setHeaderIndicatorId(
                    a.getResourceId(
                        R.styleable.ExpansionHeader_expansion_headerIndicator,
                        headerIndicatorId
                    )
                )
                setExpansionLayoutId(a.getResourceId(R.styleable.ExpansionHeader_expansion_layout, expansionLayoutId))
                isToggleOnClick = a.getBoolean(R.styleable.ExpansionHeader_expansion_toggleOnClick, isToggleOnClick)
                a.recycle()
            }
        }
    }

    fun setHeaderRotationExpanded(headerRotationExpanded: Int) {
        this.headerRotationExpanded = headerRotationExpanded
    }

    fun setHeaderRotationCollapsed(headerRotationCollapsed: Int) {
        this.headerRotationCollapsed = headerRotationCollapsed
    }

    fun setHeaderIndicatorId(headerIndicatorId: Int) {
        this.headerIndicatorId = headerIndicatorId
        if (headerIndicatorId != 0) {
            headerIndicator = findViewById(headerIndicatorId)
            setExpansionHeaderIndicator(headerIndicator)
        }
    }

    fun setExpansionHeaderIndicator(headerIndicator: View?) {
        this.headerIndicator = headerIndicator

        //if not, the view will clip when rotate
        if (headerIndicator != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            headerIndicator.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        }

        setup()
    }

    fun setExpansionLayout(expansionLayout: ExpansionLayout?) {
        this.expansionLayout = expansionLayout
        setup()
    }

    fun setExpansionLayoutId(expansionLayoutId: Int) {
        this.expansionLayoutId = expansionLayoutId

        if (expansionLayoutId != 0) {
            val parent = parent
            if (parent is ViewGroup) {
                val view = parent.findViewById<View>(expansionLayoutId)
                if (view is ExpansionLayout) {
                    setExpansionLayout(view as ExpansionLayout)
                }
            }
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        setHeaderIndicatorId(this.headerIndicatorId) //setup or update
        setExpansionLayoutId(this.expansionLayoutId) //setup or update
        setup()
    }

    private fun setup() {
        if (expansionLayout != null && !expansionLayoutInitialised) {
            expansionLayout!!.addIndicatorListener(object : ExpansionLayout.IndicatorListener {
                override fun onStartedExpand(expansionLayout: ExpansionLayout, willExpand: Boolean) {
                    onExpansionModifyView(willExpand)
                }
            })

            setOnClickListener {
                if (isToggleOnClick) {
                    expansionLayout!!.toggle(true)
                }
            }

            initialiseView(expansionLayout!!.isExpanded)
            expansionLayoutInitialised = true
        }
    }

    //can be overriden
    protected fun initialiseView(isExpanded: Boolean) {
        if (headerIndicator != null) {
            headerIndicator!!.rotation = (if (isExpanded) headerRotationExpanded else headerRotationCollapsed).toFloat()
        }
    }

    //can be overriden
    protected fun onExpansionModifyView(willExpand: Boolean) {
        isSelected = willExpand
        if (headerIndicator != null) {
            if (indicatorAnimator != null) {
                indicatorAnimator!!.cancel()
            }
            if (willExpand) {
                indicatorAnimator = ObjectAnimator.ofFloat<View>(headerIndicator, View.ROTATION, headerRotationExpanded.toFloat())
            } else {
                indicatorAnimator =
                    ObjectAnimator.ofFloat<View>(headerIndicator, View.ROTATION, headerRotationCollapsed.toFloat())
            }

            indicatorAnimator!!.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator, isReverse: Boolean) {
                    indicatorAnimator = null
                }
            })

            if (indicatorAnimator != null) {
                indicatorAnimator!!.start()
            }
        }
    }

    fun addListener(listener: ExpansionLayout.Listener) {
        if (expansionLayout != null) {
            expansionLayout!!.addListener(listener)
        }
    }

    fun removeListener(listener: ExpansionLayout.Listener) {
        if (expansionLayout != null) {
            expansionLayout!!.removeListener(listener)
        }
    }

    override fun onSaveInstanceState(): Parcelable? {
        val savedInstance = Bundle()
        savedInstance.putParcelable("super", super.onSaveInstanceState())

        savedInstance.putInt("headerIndicatorId", headerIndicatorId)
        savedInstance.putInt("expansionLayoutId", expansionLayoutId)
        savedInstance.putBoolean("toggleOnClick", isToggleOnClick)
        savedInstance.putInt("headerRotationExpanded", headerRotationExpanded)
        savedInstance.putInt("headerRotationCollapsed", headerRotationCollapsed)

        return savedInstance
    }

    override fun onRestoreInstanceState(state: Parcelable) {
        if (state is Bundle) {

            headerIndicatorId = state.getInt("headerIndicatorId")
            expansionLayoutId = state.getInt("expansionLayoutId")
            isToggleOnClick = state.getBoolean("toggleOnClick")
            setHeaderRotationExpanded(state.getInt("headerRotationExpanded"))
            setHeaderRotationCollapsed(state.getInt("headerRotationCollapsed"))
            //setup(); will wait to onAttachToWindow

            expansionLayoutInitialised = false

            super.onRestoreInstanceState(state.getParcelable("super"))
        } else {
            super.onRestoreInstanceState(state)
        }
    }
}
