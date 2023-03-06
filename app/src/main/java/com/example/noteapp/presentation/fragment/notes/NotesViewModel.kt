package com.example.noteapp.presentation.fragment.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.domain.model.Note
import com.example.noteapp.domain.usecase.GetAllNotesUseCase
import com.example.noteapp.domain.utils.ResultStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val getAllNotesUseCase: GetAllNotesUseCase
) : ViewModel() {

    private val _Notestate = MutableStateFlow<UiState<List<Note>?>>(UiState.Loading())
    val noteState = _Notestate.asStateFlow()

    init {
        NotesUseCase()
    }

    private fun NotesUseCase() {
        viewModelScope.launch {
            getAllNotesUseCase.getAllNotes().collect {
                when (it) {
                    is ResultStatus.Success -> {
                        if (it.data != null) {
                            _Notestate.value = UiState.Success(it.data)
                        }
                    }
                    is ResultStatus.Loading -> {
                        _Notestate.value = UiState.Loading()
                    }
                    is ResultStatus.Error -> {
                            _Notestate.value = UiState.Error(msg = "error")
                    }
                }
            }
        }
    }

}