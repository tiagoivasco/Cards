package com.ivasco.cards.ui.fragment.cardlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.ivasco.cards.R
import com.ivasco.cards.data.model.Card

class CardsAdapter(
    private val click: (card: Card) -> Unit
) : RecyclerView.Adapter<CardsAdapter.ViewHolder>() {
    var cards: List<Card> = emptyList()
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
            val cards = cards[position]
            cardName.text = cards.name
            card.setOnClickListener { click(cards) }
        }
    }

    override fun getItemCount() = cards.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cardName: TextView = view.findViewById(R.id.category_item_name)
        val card: ConstraintLayout = view.findViewById(R.id.category_item_layout)
    }
}