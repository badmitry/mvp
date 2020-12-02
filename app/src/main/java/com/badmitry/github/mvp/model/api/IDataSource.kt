package com.badmitry.github.mvp.model.api

import com.badmitry.github.mvp.model.entity.GithubUser
import com.badmitry.github.mvp.model.entity.GithubUserRepo
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface IDataSource {
    @GET ("/users")
    fun getUsers() : Single<List<GithubUser>>

    @GET
    fun getUserRepo(@Url url: String?): Single<List<GithubUserRepo>>
}