package spinner.taindb.vn.tnspinner

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


/**
 * Created by Taindb
 * on 6/20/18.
 * QUOINE Pte. Ltd
 */
class SpinnerAdapter : RecyclerView.Adapter<SpinnerAdapter.ItemViewHolder>() {

    private var mItems = mutableListOf<String>()

    private var mFullItems = mutableListOf<String>()

    private lateinit var mListener: OnClickItemListener

    private var mOldPositionOld: Int = -1

    fun setListener(listener: OnClickItemListener) {
        mListener = listener
    }

    fun setItem(items: List<String>) {
        mFullItems.clear()
        mFullItems.addAll(items)
        mItems.clear()
        mItems.addAll(mFullItems)
        notifyDataSetChanged()
    }

    fun getFullItems(): List<String> {
        return mFullItems
    }

    fun notifyItemSelected(item: String, position: Int) {
        var correctIndex = position
        if (mOldPositionOld - 1 == correctIndex) {
            correctIndex--
        } else if (position >= itemCount) {
            correctIndex--
        } else if (position in 1..(itemCount - 1)) {
            correctIndex++
        } else if (position == 0) {
            if (!item.contentEquals(mFullItems[0])) {
                correctIndex++
            }
        }
        mItems.clear()
        mItems.addAll(mFullItems)
        mItems.removeAt(correctIndex)
        notifyDataSetChanged()
        mOldPositionOld = position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_spinner, parent, false), mListener)
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.setText(mItems[position], position)
    }


    /***
     * [ItemViewHolder]
     */
    class ItemViewHolder(itemView: View?, listener: OnClickItemListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private lateinit var mText: String

        private var mPosition: Int = 0

        private lateinit var mTextTv: TextView

        private var mListener: OnClickItemListener = listener

        fun setText(text: String, position: Int) {
            mText = text
            mTextTv.text = mText
            mPosition = position
        }

        fun setBackground(background: Int) {
            mTextTv.setBackgroundResource(background)
        }

        override fun onClick(v: View?) {
            mListener.onItemClick(mText, mPosition)
        }

        init {
            itemView?.run {
                mTextTv = findViewById(R.id.text_tv)
                mTextTv.setOnClickListener(this@ItemViewHolder)
            }
        }

    }


    interface OnClickItemListener {
        fun onItemClick(text: String, position: Int)
    }

}