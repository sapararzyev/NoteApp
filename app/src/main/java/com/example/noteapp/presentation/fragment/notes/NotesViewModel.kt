package com.example.noteapp.presentation.fragment.notes

import com.example.noteapp.domain.model.Note
import com.example.noteapp.domain.usecase.DeleteNoteUseCase
import com.example.noteapp.domain.usecase.GetAllNotesUseCase
import com.example.noteapp.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
) : BaseViewModel() {

    private val _notesState = MutableStateFlow<UiState<List<Note>>>(UiState.Empty())
    val noteState = _notesState.asStateFlow()

    private val _deleteNotesState = MutableStateFlow<UiState<Unit>>(UiState.Empty())
    val deleteNotesState = _deleteNotesState.asStateFlow()

    fun notesUseCase() {
        getAllNotesUseCase().collectFlow(_notesState)
    }

    fun deleteNotes(note: Note) {
        deleteNoteUseCase(note).collectFlow(_deleteNotesState)
    }
}