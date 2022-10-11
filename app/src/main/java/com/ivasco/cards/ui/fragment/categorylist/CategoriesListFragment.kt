package com.ivasco.cards.ui.fragment.categorylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ivasco.cards.R
import com.ivasco.cards.databinding.FragmentCategoriesListBinding
import com.ivasco.cards.ui.extensions.view.gone
import com.ivasco.cards.ui.extensions.view.visible
import com.ivasco.cards.ui.fragment.base.BaseFragment
import com.ivasco.cards.ui.fragment.carddetails.CardDetailsFragment
import com.ivasco.cards.ui.fragment.cardlist.CardListFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoriesListFragment :
    BaseFragment<FragmentCategoriesListBinding>(R.layout.fragment_categories_list) {

    private val viewModel: CategoryListViewModel by viewModel()
    private lateinit var categoriesAdapter: CategoriesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter(view)
        setRecyclerViewAdapter()
        observeViewModelResult()
        fetchViewModelResult()
    }

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ) = FragmentCategoriesListBinding.inflate(inflater, container, attachToParent)


    private fun initAdapter(view: View) {
        categoriesAdapter = CategoriesAdapter { category ->
            val bundle = bundleOf(Pair(CardListFragment.CATEGORY_BUNDLE, category))
            Navigation.findNavController(view)
                .navigate(R.id.action_categoriesListFragment_to_cardsDetailsFragment, bundle)
        }
    }

    private fun setRecyclerViewAdapter() {
        with(binding.categoriesRv) {
            adapter = categoriesAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun setTryAgainBtnClick() {
        binding.tryAgainBtn.setOnClickListener {
            fetchViewModelResult()
        }
    }

    private fun observeViewModelResult() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            with(binding) {
                when (state) {
                    is UiState.Success -> {
                        categoriesAdapter.categories = state.categories
                        cardsListLoading.gone()
                        tryAgainBtn.gone()
                        errorMessageTxt.gone()
                        binding.categoriesRv.visible()
                    }
                    is UiState.Error -> {
                        binding.cardsListLoading.gone()
                        binding.categoriesRv.gone()
                        binding.tryAgainBtn.visible()
                        setTryAgainBtnClick()
                        binding.errorMessageTxt.visible()
                        binding.errorMessageTxt.text = getString(state.messageResource)
                    }
                    is UiState.Loading -> {
                        binding.categoriesRv.gone()
                        binding.tryAgainBtn.gone()
                        binding.errorMessageTxt.gone()
                        binding.cardsListLoading.visible()
                    }
                }
            }
        }
    }

    private fun fetchViewModelResult() {
        viewModel.fetchCategories()
    }
}