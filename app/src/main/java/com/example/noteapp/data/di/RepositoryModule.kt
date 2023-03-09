package com.example.noteapp.data.di

import com.example.noteapp.data.repo.NoteRepositoryImpl
import com.example.noteapp.domain.repo.NoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun noteRepository(noteRepositoryImpl: NoteRepositoryImpl):NoteRepository
}