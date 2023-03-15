package com.example.noteapp.presentation.fragment.second

import com.example.noteapp.domain.model.Note
import com.example.noteapp.domain.usecase.CreateNoteUseCase
import com.example.noteapp.domain.usecase.EditNoteUseCase
import com.example.noteapp.presentation.base.BaseViewModel
import com.example.noteapp.presentation.fragment.notes.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class SecondViewModel @Inject constructor(
    private val createNoteUseCase: CreateNoteUseCase,
    private val editNoteUseCase: EditNoteUseCase,
) : BaseViewModel() {

    private val _createNoteState = MutableStateFlow<UiState<Unit>>(UiState.Empty())
    val createNoteState = _createNoteState.asStateFlow()

    private val _editNoteUseCase = MutableStateFlow<UiState<Unit>>(UiState.Empty())
    val editNoteState = _editNoteUseCase.asStateFlow()

    fun create(title: String, dest: String) {
        if (title.isNotEmpty() && title.isNotBlank()) {
            createNoteUseCase(Note(
                title = title,
                description = dest,
                createAt = System.currentTimeMillis()
            )).collectFlow(_createNoteState)
        } else {
            _createNoteState.value = UiState.Error("title is emty")
        }
    }

    fun editNote(note: Note) {
        if (note.title.isNotEmpty() && note.title.isNotBlank()) {
            editNoteUseCase(
                note
            ).collectFlow(_createNoteState)
        } else {
            _createNoteState.value = UiState.Error("title is emty")
        }
    }

}