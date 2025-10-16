package com.fire.todo1

import android.app.Application
import androidx.room.Room
import com.fire.todo1.database.TodoDatabase
import com.fire.todo1.repositories.TodoRepoImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.bind
import org.koin.dsl.module

class KoinApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@KoinApp)
            modules(module {
                single {
                    Room.databaseBuilder(applicationContext, TodoDatabase::class.java, "db")
                        .build()
                }
                single {
                    TodoRepoImpl(database = get())
                } bind com.fire.todo1.repositories.TodoRepo::class

            })
            }
        }

    }
