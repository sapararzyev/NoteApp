package com.example.noteapp.presentation.fragment.addition

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.ui.navigateUp
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentAddBinding
import com.example.noteapp.domain.model.Note
import com.example.noteapp.presentation.base.BaseFragment
import com.example.noteapp.presentation.exseption.ShowTost
import com.example.noteapp.presentation.fragment.notes.NotesFragment
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class AddFragment :
    BaseFragment<AddViewModel, FragmentAddBinding>(FragmentAddBinding::inflate) {

    override val vm: AddViewModel by viewModels()
    private val note by lazy { arguments?.getSerializable(NotesFragment.ARG_ADD_EDIT) as Note? }

    override fun initialize() {
        if (arguments != null) {
            binding.meSecond.setText(note!!.title)
            binding.youSecond.setText(note!!.description)
        }
    }

    override fun listeners() {
        with(binding) {
            btnSecond.setOnClickListener {
                if (arguments != null) {
                    vm.updateNote(
                        id = note?.id!!,
                        meSecond.text.toString(),
                        youSecond.text.toString()
                    )
                } else vm.create(
                    meSecond.text.toString(),
                    youSecond.text.toString()
                )
            }
        }
    }


    override fun setupRequests() {
        vm.updateNoteState.collectState(
            onLoading = {
                binding.notesBar.isVisible = true
            },
            onSuccess = {
                ShowTost(getString(R.string.edit))
                controller.navigateUp()
                binding.notesBar.isVisible = false
            },
            onError = {
                binding.notesBar.isVisible = false
                ShowTost(it)
            }
        )

        vm.createNoteState.collectState(
            onLoading = {
                binding.notesBar.isVisible = true
            },
            onSuccess = {
                ShowTost(getString(R.string.create))
                controller.navigateUp()
                binding.notesBar.isVisible = false
            },
            onError = {
                binding.notesBar.isVisible = false
                ShowTost(it)
            }
        )

    }
}