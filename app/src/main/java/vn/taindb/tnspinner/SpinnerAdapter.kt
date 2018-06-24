package  vn.taindb.tnspinner

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

    private lateinit var mListener: OnClickItemListener

    private var mItemSelected: Int = -1

    private var mHintTextEnable: Boolean = false


    fun setListener(listener: OnClickItemListener) {
        mListener = listener
    }

    fun enableHintText(enable: Boolean) {
        mHintTextEnable = enable
    }

    fun setItem(items: List<String>) {
        mItems.clear()
        mItems.addAll(items)
        notifyDataSetChanged()
    }

    fun getFullItems(): List<String> {
        return mItems
    }

    fun notifyItemSelected(position: Int) {
        mItemSelected = position
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_spinner, parent, false), mListener)
    }

    override fun getItemCount(): Int {
        return if (mHintTextEnable)
            mItems.size
        else mItems.size - 1
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        if (position >= mItemSelected && mItemSelected != -1) {
            holder.setText(mItems[position + 1], position + 1)
        } else {
            holder.setText(mItems[position], position)
        }
    }


    /***
     * [ItemViewHolder]
     */
    class ItemViewHolder(itemView: View?, listener: OnClickItemListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private lateinit var mText: String

        private var mPosition: Int = 0

        lateinit var mTextTv: TextView

        private var mListener: OnClickItemListener = listener

        fun setText(text: String, position: Int) {
            mText = text
            mTextTv.text = mText
            mPosition = position
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