package com.badmitry.github.mvp.model.entity.room.dao

import androidx.room.*
import com.badmitry.github.mvp.model.entity.room.RoomGithubUsers

@Dao
interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: RoomGithubUsers)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg users: RoomGithubUsers)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<RoomGithubUsers>)

    @Update
    fun update(user: RoomGithubUsers)

    @Update
    fun update(vararg users: RoomGithubUsers)

    @Update
    fun update(users: List<RoomGithubUsers>)

    @Delete
    fun delete(user: RoomGithubUsers)

    @Delete
    fun delete(vararg users: RoomGithubUsers)

    @Delete
    fun delete(users: List<RoomGithubUsers>)

    @Query("SELECT * FROM RoomGithubUsers")
    fun getAll(): List<RoomGithubUsers>

    @Query("SELECT * FROM RoomGithubUsers WHERE login = :login LIMIT 1")
    fun findByLogin(login: String): RoomGithubUsers?

}