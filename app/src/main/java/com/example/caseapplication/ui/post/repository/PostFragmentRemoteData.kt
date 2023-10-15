package com.example.caseapplication.ui.post.repository

import com.example.caseapplication.service.ApiService
import javax.inject.Inject

class PostFragmentRemoteData @Inject constructor(
    private val service: ApiService
) : PostFragmentContract.Remote {

    override fun getPosts() = service.getPosts()

}