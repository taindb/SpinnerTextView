package spinner.taindb.vn.tnspinner

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup


/**
 * Created by Taindb
 * on 6/20/18.
 * QUOINE Pte. Ltd
 */
class SpinnerAdapter : RecyclerView.Adapter<SpinnerAdapter.ItemViewHolder>() {

    private var mItems = mutableListOf<String>()

    fun setItem(items: List<String>) {
        mItems = items.toMutableList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    class ItemViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    }
}