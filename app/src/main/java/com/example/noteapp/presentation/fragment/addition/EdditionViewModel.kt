package com.example.noteapp.presentation.fragment.addition

import com.example.noteapp.domain.model.Note
import com.example.noteapp.domain.usecase.CreateNoteUseCase
import com.example.noteapp.domain.usecase.UpdateNoteUseCase
import com.example.noteapp.presentation.base.BaseViewModel
import com.example.noteapp.presentation.fragment.notes.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class EditionViewModel @Inject constructor(
    private val createNoteUseCase: CreateNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
) : BaseViewModel() {

    private val _createNoteState = MutableStateFlow<UiState<Unit>>(UiState.Empty())
    val createNoteState = _createNoteState.asStateFlow()

    private val _updateNoteUseCase = MutableStateFlow<UiState<Unit>>(UiState.Empty())
    val updateNoteState = _updateNoteUseCase.asStateFlow()

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

    fun updateNote(note: Note) {
        if (note.title.isNotEmpty() && note.title.isNotBlank()) {
            updateNoteUseCase(
                note
            ).collectFlow(_createNoteState)
        } else {
            _createNoteState.value = UiState.Error("title is emty")
        }
    }

}