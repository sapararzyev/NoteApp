package com.example.noteapp.presentation.fragment.addition

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentSecondBinding
import com.example.noteapp.domain.model.Note
import com.example.noteapp.presentation.base.BaseFragment
import com.example.noteapp.presentation.exseption.ShowTost
import com.example.noteapp.presentation.fragment.notes.NotesFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditionFragment:
    BaseFragment<EditionViewModel, FragmentSecondBinding>(R.layout.fragment_second) {

    override val binding: FragmentSecondBinding by viewBinding(FragmentSecondBinding::bind)
    override val vm: EditionViewModel by viewModels()
    private var note: Note? = null

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun initialize() {
        if (arguments != null) {
            note = requireArguments().getSerializable(NotesFragment.ARG_ADD_EDIT, Note::class.java)
            setData()
        }
    }

    private fun setData() {
        with(binding){
            meSecond.setText(note!!.title)
            youSecond.setText(note!!.description)
        }
    }

    override fun listeners() {
        with(binding) {
            btnSecond.setOnClickListener {
                if (note != null)
                    vm.updateNote(
                        note!!.copy(
                            title = meSecond.text.toString(),
                            description =  youSecond.text.toString()
                        )
                    )
               else vm.create(
                    meSecond.text.toString(), youSecond.text.toString()
                )
            }
        }
    }

    override fun setupRequests() {
        vm.createNoteState.collectState(
            onLoading = {
                binding.notesBar.isVisible = true
            },
            onSuccess = {
                ShowTost(getString(R.string.create))
                binding.notesBar.isVisible = false
                controller.navigateUp()
                controller.navigate(R.id.notesFragment)
            },
            onError = {
                binding.notesBar.isVisible = false
                ShowTost(it)
                controller.navigateUp()
            }
        )
        vm.updateNoteState.collectState(
            onLoading = {
                binding.notesBar.isVisible = true
            },
            onSuccess = {
                ShowTost(getString(R.string.edit))
                binding.notesBar.isVisible = false
                controller.navigateUp()
                controller.navigate(R.id.notesFragment)
            },
            onError = {
                binding.notesBar.isVisible = false
                ShowTost(it)
                controller.navigateUp()
            }
        )
    }
}