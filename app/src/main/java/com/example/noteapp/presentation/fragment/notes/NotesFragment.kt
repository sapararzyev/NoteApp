package com.example.noteapp.presentation.fragment.notes

import android.widget.Adapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentNotesBinding
import com.example.noteapp.domain.model.Note
import com.example.noteapp.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesFragment : BaseFragment<NotesViewModel, FragmentNotesBinding>(R.layout.fragment_notes) {

    override val binding: FragmentNotesBinding by viewBinding(FragmentNotesBinding::bind)
    override val vm: NotesViewModel by viewModels()
    private val adapter: NotesAdapter by lazy {
        NotesAdapter(this::listener)
    }

    override fun initialize() {
        binding.rvNotes.adapter = adapter
    }
    override fun setupRequests() {
        vm.noteState.collectState(
            onLoading = {

            },
            onSuccess = {
                adapter.submitList(it)
                Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
            },
            onError = {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        )
    }

    override fun listeners() {
        binding.btnAddNote.setOnClickListener {
            findNavController().navigateUp()
            controller.navigate(R.id.secondFragment)        }
    }

    private fun listener(model: Note) {
        Toast.makeText(requireContext(), "Work", Toast.LENGTH_SHORT).show()
            vm.deleteNotes(model)
            adapter.notifyItemRemoved(model.id)
        findNavController().navigateUp()
        findNavController().navigate(R.id.notesFragment)
    }
}
