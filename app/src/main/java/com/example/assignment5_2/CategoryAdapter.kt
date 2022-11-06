package com.example.assignment5_2

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment5_2.data.Product

class CategoryAdapter(val context: Context, val products: MutableList<Product>) :
    RecyclerView.Adapter<CategoryAdapter.AssignedTasksViewHolder?>() {

    private var mCallback: Callback? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): AssignedTasksViewHolder {
        val itemEvents: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_category, viewGroup, false)
        return AssignedTasksViewHolder(itemEvents)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(baseViewHolder: AssignedTasksViewHolder, position: Int) {
        val product = products[position]
        baseViewHolder.image?.setBackgroundResource(product.image)
        baseViewHolder.title?.text = product.title
        baseViewHolder.price?.text = product.price
        baseViewHolder.color?.text = product.color

        baseViewHolder.itemContainer?.setOnClickListener {
            mCallback?.onItemClicked(product)
            notifyDataSetChanged()
        }
    }

    fun setCallback(callback: Callback?) {
        mCallback = callback
    }

    override fun getItemCount(): Int = products.size

    interface Callback {
        fun onItemClicked(product: Product?)
    }

    inner class AssignedTasksViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView? = view.findViewById(R.id.rv_title)
        var image: ImageView? = view.findViewById(R.id.rv_image)
        var price: TextView? = view.findViewById(R.id.rv_price)
        var color: TextView? = view.findViewById(R.id.rv_color)
        var itemContainer: LinearLayout? = view.findViewById(R.id.item_container)
    }
}