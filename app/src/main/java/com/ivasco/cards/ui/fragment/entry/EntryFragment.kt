package com.ivasco.cards.ui.fragment.entry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.ivasco.cards.R
import com.ivasco.cards.data.model.Person
import com.ivasco.cards.databinding.FragmentEntryBinding
import com.ivasco.cards.ui.extensions.setError
import com.ivasco.cards.ui.extensions.view.getPersonFromSharedPreferences
import com.ivasco.cards.ui.extensions.view.storePersonIntoSharedPreferences
import com.ivasco.cards.ui.fragment.base.BaseFragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.koin.androidx.viewmodel.ext.android.viewModel

class EntryFragment : BaseFragment<FragmentEntryBinding>(R.layout.fragment_entry) {
    private val viewModel: EntryViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDataFromSharedPreferences()
        enterBtnClick(view)
    }

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ) = FragmentEntryBinding.inflate(inflater, container, attachToParent)

    private fun enterBtnClick(view: View) {
        with(binding) {
            enterBtn.setOnClickListener {
                val name = nameEditTxt.text.toString()
                val email = emailEditTxt.text.toString()

                val nameError = viewModel.isNameInvalid(name)
                val emailError = viewModel.isEmailInvalid(email)

                nameTxtInputlayout.setError(nameError, getString(R.string.invalid_name_error))
                emailTxtInputlayout.setError(emailError, getString(R.string.invalid_name_error))

                // check if this logic is working
                if (!nameError && !emailError) {
                    activity?.storePersonIntoSharedPreferences(Person(name, email))
                    Navigation.findNavController(view)
                        .navigate(R.id.action_entryFragment_to_categoriesListFragment)
                }
            }
        }
    }

    private fun getDataFromSharedPreferences() {
        activity?.getPersonFromSharedPreferences()?.let {
            with(binding) {
                nameEditTxt.setText(it.name)
                emailEditTxt.setText(it.email)
            }
        }
    }
}