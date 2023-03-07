package com.example.noteapp.presentation.fragment.notes

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.noteapp.R
import com.example.noteapp.base.BaseFragment
import com.example.noteapp.databinding.FragmentNotesBinding
import kotlinx.coroutines.launch


class NotesFragment : BaseFragment<FragmentNotesBinding>(FragmentNotesBinding::inflate) {

    private lateinit var adapter: NotesAdapter
    private val viewModel: NotesViewModel by viewModels()

    override fun setubIU() {
        adapter = NotesAdapter()
        binding.rvNotes.adapter = adapter
        binding.btnAddNote.setOnClickListener {
            findNavController().navigate(R.id.secondFragment)
        }
    }
    private fun listeners() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.noteState.collect {
                    when (it) {
                        is UiState.Empty -> {}
                        is UiState.Error -> {
                            Toast.makeText(requireContext(), it.msg, Toast.LENGTH_SHORT).show()
                        }
                        is UiState.Loading -> {}
                        is UiState.Success -> {
                                UiState.Success(it.data)
                        }
                    }
                }
            }
        }
    }
}
