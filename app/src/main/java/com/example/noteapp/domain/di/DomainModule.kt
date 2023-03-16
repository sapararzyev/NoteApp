package com.example.noteapp.domain.di

import com.example.noteapp.domain.repo.NoteRepository
import com.example.noteapp.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideCreateNoteUseCase(repo: NoteRepository): CreateNoteUseCase {
        return CreateNoteUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideDeleteNoteUseCase(repo: NoteRepository): DeleteNoteUseCase {
        return DeleteNoteUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideEditNoteUseCase(repo: NoteRepository): UpdateNoteUseCase {
        return UpdateNoteUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideGetAllNotesUseCase(repo: NoteRepository): GetAllNotesUseCase {
        return GetAllNotesUseCase(repo)
    }
}