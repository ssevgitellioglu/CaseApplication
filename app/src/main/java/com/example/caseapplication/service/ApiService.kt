package com.example.caseapplication.service

import com.example.caseapplication.response.PostsResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET(value = "posts")
    fun getPosts(): Single<List<PostsResponse>>
}