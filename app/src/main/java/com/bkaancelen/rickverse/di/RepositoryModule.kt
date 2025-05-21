package com.bkaancelen.rickverse.di

import com.bkaancelen.rickverse.data.remote.api.MultiverseApi
import com.bkaancelen.rickverse.data.repository.CharacterRepositoryImpl
import com.bkaancelen.rickverse.domain.repository.CharacterRepository
import com.bkaancelen.rickverse.domain.useCase.GetCharactersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCharacterRepository(api: MultiverseApi): CharacterRepository {
        return CharacterRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideGetCharactersUseCase(repository: CharacterRepository): GetCharactersUseCase {
        return GetCharactersUseCase(repository)
    }
}
