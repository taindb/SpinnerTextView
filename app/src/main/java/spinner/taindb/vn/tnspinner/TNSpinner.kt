package spinner.taindb.vn.tnspinner

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.WindowManager
import android.widget.PopupWindow


/**
 * Created by Taindb
 * on 6/20/18.
 * QUOINE Pte. Ltd
 */
class TNSpinner : AppCompatTextView {

    private val SELECTED_INDEX = "selected_index"

    private val NOTHING_SELECTED = "nothing_selected"

    private val IS_POPUP_SHOWING = "is_popup_showing"

    private lateinit var mPopupWindow: PopupWindow

    private lateinit var mListView: RecyclerView

    private lateinit var mArrowDrawable: Drawable

    private var mDropDownHeight: Int = 0

    private var mDropDownMaxHeight: Int = 0

    private var mNothingSelected: Int = 0

    private var mSelectedIndex: Int = 0

    constructor(context: Context?) : super(context) {
        init(context, null)
    }

    constructor(context: Context?, attrRes: AttributeSet?) : super(context, attrRes) {
        init(context, attrRes)
    }

    constructor(context: Context?, attrRes: AttributeSet?, defStyleAttr: Int) : super(context, attrRes, defStyleAttr) {
        init(context, attrRes)
    }

    private fun init(context: Context?, attrRes: AttributeSet?) {
        context?.obtainStyledAttributes(attrRes, R.styleable.TNSpinner)?.run {
            mDropDownHeight = getLayoutDimension(R.styleable.TNSpinner_dropdown_height, WindowManager.LayoutParams.WRAP_CONTENT)
            mDropDownMaxHeight = getDimensionPixelSize(R.styleable.TNSpinner_dropdown_max_height, 0)
            mArrowDrawable = getDrawable(R.styleable.TNSpinner_arrow_icon)
            this.recycle()
        }

        // create recycler view (list view)
        mListView = createListView(context)

        // create popup window
        mPopupWindow = createPopupWindow(context)
    }

    private fun createListView(context: Context?) = RecyclerView(context).apply {
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    private fun createPopupWindow(context: Context?) = PopupWindow(context).apply {
        contentView = mListView
        isOutsideTouchable = true
        isFocusable = true
    }.apply {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            elevation = 16f
            setBackgroundDrawable(context?.getDrawable(R.drawable.drop_down))
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setBackgroundDrawable(context?.getDrawable(R.drawable.drop_down_shadow))
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        mPopupWindow.width = MeasureSpec.getSize(widthMeasureSpec)


                super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

}