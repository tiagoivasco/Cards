package com.ivasco.cards.ui.fragment.categorylist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.ivasco.cards.R

class CategoriesAdapter(
    private val click: (categories: String) -> Unit
) : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {
    var categories: List<String> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            val category = categories[position]
            categoryName.text = category
            card.setOnClickListener { click(category) }
        }
    }

    override fun getItemCount() = categories.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val categoryName: TextView = view.findViewById(R.id.category_item_name)
        val card: ConstraintLayout = view.findViewById(R.id.category_item_layout)
    }
}