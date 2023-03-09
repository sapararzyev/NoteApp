package com.example.noteapp.data.repo

import com.example.noteapp.data.base.BaseRepository
import com.example.noteapp.data.local.NoteDao
import com.example.noteapp.data.mapers.toNote
import com.example.noteapp.data.mapers.toNoteEntity
import com.example.noteapp.domain.model.Note
import com.example.noteapp.domain.repo.NoteRepository
import com.example.noteapp.domain.utils.ResultStatus
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
    ) : NoteRepository,BaseRepository() {

    override fun createNote(note: Note) = doRequest {
        noteDao.insert(note.toNoteEntity())
    }

    override fun editNote(note: Note) = doRequest {
        noteDao.update(note.toNoteEntity())
    }

    override fun deleteNote(note: Note)=doRequest {
        noteDao.deleteNote(note.toNoteEntity())
    }

    override fun getAllNotes():Flow<ResultStatus<List<Note>>> = doRequest {
        noteDao.getAllNotes().map { it.toNote() }
    }
}