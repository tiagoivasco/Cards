package com.ivasco.cards.ui.fragment.cardlist

import android.os.Bundle
import android.util.Log
import android.util.Log.INFO
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ivasco.cards.R
import com.ivasco.cards.databinding.FragmentCardsListBinding
import com.ivasco.cards.ui.extensions.view.gone
import com.ivasco.cards.ui.extensions.view.visible
import com.ivasco.cards.ui.fragment.base.BaseFragment
import com.ivasco.cards.ui.fragment.carddetails.CardDetailsFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class CardListFragment : BaseFragment<FragmentCardsListBinding>(R.layout.fragment_cards_list) {
    private val viewModel: CardListViewModel by viewModel()
    private lateinit var cardsAdapter: CardsAdapter
    private lateinit var category: String

    companion object {
        const val CATEGORY_BUNDLE = "category_bundle"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter(view)
        getDataFromBundle()
        setRecyclerViewAdapter()
        observeViewModelResult()
        fetchViewModelResult()
    }

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ) = FragmentCardsListBinding.inflate(inflater, container, attachToParent)


    private fun initAdapter(view: View) {
        cardsAdapter = CardsAdapter { card ->
            val bundle = bundleOf(Pair(CardDetailsFragment.CARD_BUNDLE, card))
//            Log.i("testeeee", card.)
            Navigation.findNavController(view)
                .navigate(R.id.action_cardsListFragment_to_cardDetailsFragment, bundle)
        }
    }

    private fun setRecyclerViewAdapter() {
        with(binding.cardsRv) {
            adapter = cardsAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun setTryAgainBtnClick() {
        binding.tryAgainBtn.setOnClickListener {
            fetchViewModelResult()
        }
    }

    private fun getDataFromBundle() {
        val bundleCategory = arguments?.getString("category_bundle")
        bundleCategory?.let {
            category = it
        }
    }

    private fun observeViewModelResult() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            with(binding) {
                when (state) {
                    is UiState.Success -> {
                        cardsAdapter.cards = state.cards
                        cardsListLoading.gone()
                        tryAgainBtn.gone()
                        errorMessageTxt.gone()
                        binding.cardsRv.visible()
                    }
                    is UiState.Error -> {
                        binding.cardsListLoading.gone()
                        binding.cardsRv.gone()
                        binding.tryAgainBtn.visible()
                        setTryAgainBtnClick()
                        binding.errorMessageTxt.visible()
                        binding.errorMessageTxt.text = getString(state.messageResource)
                    }
                    is UiState.Loading -> {
                        binding.cardsRv.gone()
                        binding.tryAgainBtn.gone()
                        binding.errorMessageTxt.gone()
                        binding.cardsListLoading.visible()
                    }
                }
            }
        }
    }

    private fun fetchViewModelResult() {
        viewModel.fetchCardsByCategory(category)
    }
}