package spinner.taindb.vn.tnspinner

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.WindowManager
import android.widget.PopupWindow


/**
 * Created by Taindb
 * on 6/20/18.
 * QUOINE Pte. Ltd
 */
class TNSpinner : AppCompatTextView, SpinnerAdapter.OnClickItemListener {

    private val SELECTED_INDEX = "selected_index"

    private val NOTHING_SELECTED = "nothing_selected"

    private val IS_POPUP_SHOWING = "is_popup_showing"

    private lateinit var mPopupWindow: PopupWindow

    private lateinit var mListView: RecyclerView

    private lateinit var mArrowDrawable: Drawable

    private var mDropDownHeight: Int = 0

    private var mDropDownMaxHeight: Int = 0

    private var mNothingSelected: Boolean = false

    private var mSelectedIndex: Int = -1

    private var mHideArrow: Boolean = false

    private var mHeightWrapContent: Boolean = false

    private lateinit var mAdapter: SpinnerAdapter

    private val mOnDismissListener = PopupWindow.OnDismissListener {
        //        if (mNothingSelected && onNothingSelectedListener != null) {
//            onNothingSelectedListener.onNothingSelected(this@MaterialSpinner)
//        }
        if (!mHideArrow) {
            animateArrow(false)
        }
    }

    constructor(context: Context?) : super(context) {
        init(context, null)
    }

    constructor(context: Context?, attrRes: AttributeSet?) : super(context, attrRes) {
        init(context, attrRes)
    }

    constructor(context: Context?, attrRes: AttributeSet?, defStyleAttr: Int) : super(context, attrRes, defStyleAttr) {
        init(context, attrRes)
    }

    fun setListItems(items: List<String>) {
        mAdapter.setItem(items)
        mSelectedIndex = 0
        text = items[mSelectedIndex]
    }

    fun setDropDownHeight(height: Int) {
        mDropDownHeight = height
        mPopupWindow.height = calculatePopupWindowHeight()
    }

    fun setDropDownMaxHeight(height: Int) {
        mDropDownMaxHeight = height
        mPopupWindow.height = calculatePopupWindowHeight()
    }

    fun expand() {
        if (!mHideArrow) animateArrow(true)
        mNothingSelected = true
        mPopupWindow.showAsDropDown(this)
    }

    fun collapse() {
        if (!mHideArrow) animateArrow(false)
        mPopupWindow.dismiss()
    }

    private fun init(context: Context?, attrRes: AttributeSet?) {
        mNothingSelected = true
        context?.obtainStyledAttributes(attrRes, R.styleable.TNSpinner)?.run {
            mDropDownHeight = getLayoutDimension(R.styleable.TNSpinner_dropdown_height, WindowManager.LayoutParams.WRAP_CONTENT)
            mDropDownMaxHeight = getDimensionPixelSize(R.styleable.TNSpinner_dropdown_max_height, 0)
            mArrowDrawable = getDrawable(R.styleable.TNSpinner_arrow_icon).mutate()
            mHeightWrapContent = getBoolean(R.styleable.TNSpinner_height_wrap_content, false)
            setCompoundDrawablesWithIntrinsicBounds(null, null, mArrowDrawable, null)
            this.recycle()
        }

        // init adapter
        mAdapter = SpinnerAdapter()

        // create recycler view (list view)
        mListView = createListView(context)

        // create popup window
        mPopupWindow = createPopupWindow(context)
    }

    private fun createListView(context: Context?) = RecyclerView(context).apply {
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter = mAdapter
        mAdapter.setListener(this@TNSpinner)
    }

    private fun createPopupWindow(context: Context?) = PopupWindow(context).apply {
        this.contentView = mListView
        this.isOutsideTouchable = true
        this.isFocusable = true
        this.setOnDismissListener(mOnDismissListener)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.elevation = 16f
            this.setBackgroundDrawable(context?.getDrawable(R.drawable.drop_down))
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.setBackgroundDrawable(context?.getDrawable(R.drawable.drop_down_shadow))
        }
    }

    private fun calculatePopupWindowHeight(): Int {
        var itemHeight = resources.getDimensionPixelSize(R.dimen.item_height)
        var listViewHeight = mAdapter.itemCount * itemHeight

        if (mHeightWrapContent) return listViewHeight
        else if (mDropDownMaxHeight > 0 && listViewHeight > mDropDownMaxHeight) {
            return mDropDownMaxHeight
        } else if (mDropDownHeight != WindowManager.LayoutParams.MATCH_PARENT
                && mDropDownHeight != WindowManager.LayoutParams.WRAP_CONTENT
                && mDropDownHeight <= listViewHeight) {
            return mDropDownHeight
        } else if (listViewHeight == 0 && mAdapter.itemCount == 1) {
            return itemHeight
        }

        return listViewHeight
    }

    private fun animateArrow(shouldRotateUp: Boolean) {
        val start = if (shouldRotateUp) 0 else 1000
        val end = if (shouldRotateUp) 1000 else 0
        ObjectAnimator.ofInt(mArrowDrawable, "level", start, end).start()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        mPopupWindow.width = MeasureSpec.getSize(widthMeasureSpec)
        mPopupWindow.height = calculatePopupWindowHeight()

        if (mAdapter != null) {
            var currentText = text
            var longestItem = text.toString()

            mAdapter.getItems().forEach {
                if (it.length > longestItem.length) {
                    longestItem = it
                }
            }
            text = longestItem
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
            text = currentText
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_UP) {
            if (isEnabled && isClickable) {
                if (!mPopupWindow.isShowing) {
                    expand()
                } else {
                    collapse()
                }
            }
        }

        return super.onTouchEvent(event)
    }

    private lateinit var mTextPrev: String

    private var mPosPrev: Int = -1

    override fun onItemClick(text: String, position: Int) {
        setText(text)
        collapse()
    }

}