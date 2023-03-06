package com.example.noteapp.domain.usecase

import com.example.noteapp.domain.repo.NoteRepository

class GetAllNotesUseCase(private val noteRepository: NoteRepository) {
    fun getAllNotes() = noteRepository.getAllNotes()
}