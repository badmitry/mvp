package com.badmitry.github.mvp.model.entity.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.badmitry.github.mvp.model.entity.room.RoomGithubRepos
import com.badmitry.github.mvp.model.entity.room.RoomGithubUsers
import com.badmitry.github.mvp.model.entity.room.dao.RepoDAO
import com.badmitry.github.mvp.model.entity.room.dao.UserDAO
import java.lang.RuntimeException

@Database(entities = [RoomGithubUsers::class, RoomGithubRepos::class], version = 1)
abstract class Database: RoomDatabase() {
    abstract val userDao: UserDAO
    abstract val repoDao: RepoDAO

    companion object {
        const val DB_NAME = "database.db"
        private var instance: com.badmitry.github.mvp.model.entity.room.database.Database? = null
        fun getInstance() = instance ?: throw RuntimeException("Database has not been created!")
        fun create(context: Context) {
            instance ?: let{
                instance = Room.databaseBuilder(context, com.badmitry.github.mvp.model.entity.room.database.Database::class.java, DB_NAME)
                    .build()
            }
        }
    }
}