package com.example.noteapp.presentation.fragment.notes

import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentNotesBinding
import com.example.noteapp.domain.model.Note
import com.example.noteapp.presentation.base.BaseFragment
import com.example.noteapp.presentation.exseption.ShowTost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesFragment :
    BaseFragment<NotesViewModel, FragmentNotesBinding>(FragmentNotesBinding::inflate) {

    override val vm: NotesViewModel by viewModels()
    private val adapter: NotesAdapter by lazy {
        NotesAdapter(this::onLongItemClikListener,
            this::onItemClikListener)
    }

    override fun initialize() {
        binding.rvNotes.adapter = adapter
        vm.notesUseCase()
    }

    override fun listeners() {
        binding.btnAddNote.setOnClickListener {
            controller.navigate(R.id.action_notesFragment_to_addFragment3)
        }
    }

    private fun onLongItemClikListener(position: Int) {
        val list = adapter.currentList.toMutableList()
        vm.deleteNotes(list[position])
        list.removeAt(position)
        adapter.submitList(list)
    }

    override fun setupRequests() {
        vm.noteState.collectState(onLoading = {
            binding.notesBar.isVisible = true
        }, onSuccess = {
            adapter.submitList(it)
            binding.notesBar.isVisible = false
        }, onError = {
            ShowTost(it)
            binding.notesBar.isVisible = false
        })

        vm.deleteNotesState.collectState(onLoading = {
            binding.notesBar.isVisible
        }, onSuccess = {
            binding.notesBar.isVisible = false
            ShowTost(getString(R.string.delete))
        }, onError = {
            binding.notesBar.isVisible = false
            ShowTost(it)
        })
    }

    private fun onItemClikListener(model: Note) {
        val bundle = bundleOf(ARG_ADD_EDIT to model)
        controller.navigate(R.id.action_notesFragment_to_addFragment3, bundle)
    }

    companion object {
        const val ARG_ADD_EDIT = "arg_add_edit"
    }
}