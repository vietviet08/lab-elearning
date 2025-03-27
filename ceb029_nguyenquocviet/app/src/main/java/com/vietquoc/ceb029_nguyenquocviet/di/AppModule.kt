package com.vietquoc.ceb029_nguyenquocviet.di

import android.app.Application
import androidx.room.Room
import com.vietquoc.ceb029_nguyenquocviet.db.ComputerDb
import com.vietquoc.ceb029_nguyenquocviet.repository.ComputerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDb(application: Application): ComputerDb {
        return Room.databaseBuilder(
            context = application,
            klass = ComputerDb::class.java,
            name = "computer_db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: ComputerDb): ComputerRepository {
        return ComputerRepository(db)
    }


}