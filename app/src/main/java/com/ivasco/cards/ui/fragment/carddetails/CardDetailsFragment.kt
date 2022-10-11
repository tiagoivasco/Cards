package com.ivasco.cards.ui.fragment.carddetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.ivasco.cards.R
import com.ivasco.cards.data.model.Card
import com.ivasco.cards.databinding.FragmentCardDetailsBinding
import com.ivasco.cards.ui.fragment.base.BaseFragment
import com.ivasco.cards.ui.glide.ImgLoader
import org.koin.androidx.viewmodel.ext.android.viewModel

class CardDetailsFragment :
    BaseFragment<FragmentCardDetailsBinding>(R.layout.fragment_card_details) {
    private val viewModel: CardDetailsViewModel by viewModel()
    private lateinit var card: Card

    companion object {
        const val CARD_BUNDLE = "card_bundle"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBackFabClick(view)
        getDataFromBundle()
        observeViewModelResult()
    }

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ) = FragmentCardDetailsBinding.inflate(inflater, container, attachToParent)

    private fun setBackFabClick(view: View) {
        binding.cardInfoBackFab.setOnClickListener {
            Navigation.findNavController(view).popBackStack()
        }
    }

    private fun getDataFromBundle() {
        val bundleCard = arguments?.getParcelable<Card>(CARD_BUNDLE)
        bundleCard?.let {
            card = it
            val imgLoader = ImgLoader(Glide.with(this))
            imgLoader.loadImage(
                imgUrl = card.img ?: "",
                imgView = binding.cardImg
            )

            with(binding) {
                cardName.text = card.name
            }
        }
    }

    private fun observeViewModelResult() {
    }
}