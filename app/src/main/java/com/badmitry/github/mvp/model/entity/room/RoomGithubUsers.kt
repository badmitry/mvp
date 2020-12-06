package com.badmitry.github.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RoomGithubUsers(
    @PrimaryKey var id: String,
    var login: String,
    var avatarUrl: String,
    var repoUrl: String?
)