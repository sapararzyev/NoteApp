package com.example.noteapp.presentation.fragment.notes

import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentNotesBinding
import com.example.noteapp.domain.model.Note
import com.example.noteapp.presentation.base.BaseFragment
import com.example.noteapp.presentation.exseption.ShowTost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesFragment : BaseFragment<NotesViewModel, FragmentNotesBinding>(R.layout.fragment_notes) {

    override val binding: FragmentNotesBinding by viewBinding(FragmentNotesBinding::bind)
    override val vm: NotesViewModel by viewModels()
    private val adapter: NotesAdapter by lazy { NotesAdapter(this::listener) }
    private var note: Note? = null

    override fun initialize() {
        binding.rvNotes.adapter = adapter
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
            binding.notesBar.isVisible = true
        }, onSuccess = {
            binding.notesBar.isVisible = false
            ShowTost(getString(R.string.delete))
        }, onError = {
            binding.notesBar.isVisible = false
            ShowTost(it)
        })
    }

    override fun listeners() {
        binding.btnAddNote.setOnClickListener {
            val bundle = bundleOf(ARG_ADD_EDIT to note)
            controller.navigateUp()
            controller.navigate(R.id.action_notesFragment_to_secondFragment, bundle)
        }
    }

    private fun listener(model: Note) {
        vm.deleteNotes(model)
        adapter.notifyItemRemoved(model.id)
        controller.navigateUp()
        controller.navigate(R.id.notesFragment)
    }

    companion object {
        const val ARG_ADD_EDIT = "arg_add_edit"
    }
}