package com.fire.todo1.database

import androidx.room.Database
import androidx.room.RoomDatabase



/**
 * The Room database for this app.
 *
 * This class defines the database configuration and serves as the main access point
 * for the underlying connection to the app's persisted data.
 *
 * @property todoDao The Data Access Object for the 'todos' table.
 */
@Database(entities = [TodoEntity::class], version = 1)
abstract class TodoDatabase: RoomDatabase() {

    abstract fun todoDao(): TodoDao
}
/**
 * Database class with a singleton INSTANCE object.

@Volatile
private var INSTANCE: TodoDatabase? = null
 just created or initialised a database with RoomDatabase and created the fun todoDao to be used in other file


 */