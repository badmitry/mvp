package com.badmitry.github.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubUserRepo(
    @Expose val id: String,
    @Expose val name: String,
    @Expose val fork: String
) : Parcelable