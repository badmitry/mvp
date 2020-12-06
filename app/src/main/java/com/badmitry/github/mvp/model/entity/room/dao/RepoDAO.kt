package com.badmitry.github.mvp.model.entity.room.dao

import androidx.room.*
import com.badmitry.github.mvp.model.entity.room.RoomGithubRepos

@Dao
interface RepoDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repository: RoomGithubRepos)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg repository: RoomGithubRepos)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repositories: List<RoomGithubRepos>)

    @Delete
    fun delete(repository: RoomGithubRepos)

    @Delete
    fun delete(vararg repository: RoomGithubRepos)

    @Delete
    fun delete(repositories: List<RoomGithubRepos>)

    @Update
    fun update(repository: RoomGithubRepos)

    @Update
    fun update(vararg repository: RoomGithubRepos)

    @Update
    fun update(repositories: List<RoomGithubRepos>)

    @Query("SELECT * FROM RoomGithubRepos")
    fun getAll(): List<RoomGithubRepos>

    @Query("SELECT * FROM RoomGithubRepos WHERE userId = :userId")
    fun findForUser(userId: String): List<RoomGithubRepos>
}