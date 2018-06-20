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

    private lateinit var mItems: List<String>

    private lateinit var mListener: OnClickItemListener

    private var mPrevPosition: Int = -1

    private var mPrevItems = mutableListOf<String>()

    fun setListener(listener: OnClickItemListener) {
        mListener = listener
    }

    fun setItem(items: List<String>) {
        mItems = items.toMutableList()
        notifyDataSetChanged()
    }

    fun getItems(): List<String> {
        return mItems
    }

    fun notifyItemSelected(position: Int) {
        if (position <= -1 || position >= itemCount) return
        mPrevPosition = position
        if (mPrevItems.isEmpty()) {
             notifyItemRemoved(position)
        } else {

        }


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