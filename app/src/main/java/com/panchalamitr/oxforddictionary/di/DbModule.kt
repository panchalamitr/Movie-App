package com.panchalamitr.oxforddictionary.di

import android.content.Context
import androidx.room.Room
import com.panchalamitr.oxforddictionary.db.MovieDao
import com.panchalamitr.oxforddictionary.db.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DbModule {

    @Provides
    @Singleton
    fun provideMovieDatabase(@ApplicationContext appContext: Context): MovieDatabase {
        return MovieDatabase.invoke(appContext)
    }

    @Provides
    fun provideMovieDao(movieDatabase: MovieDatabase): MovieDao {
        return movieDatabase.getMovieDao()
    }


}