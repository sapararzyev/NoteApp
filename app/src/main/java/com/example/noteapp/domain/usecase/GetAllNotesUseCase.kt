package com.example.noteapp.domain.usecase

import com.example.noteapp.domain.model.Note
import com.example.noteapp.domain.repo.NoteRepository
import com.example.noteapp.domain.utils.ResultStatus
import kotlinx.coroutines.flow.Flow

class GetAllNotesUseCase(private val noteRepository: NoteRepository) {
   operator fun invoke():Flow<ResultStatus<List<Note>>> = noteRepository.getAllNotes()
}