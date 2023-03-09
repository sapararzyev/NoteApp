package com.example.noteapp.domain.usecase

import com.example.noteapp.domain.model.Note
import com.example.noteapp.domain.repo.NoteRepository

class CreateNoteUseCase(private val noteRepository: NoteRepository) {
    operator fun invoke(note: Note) = noteRepository.createNote(note)
}