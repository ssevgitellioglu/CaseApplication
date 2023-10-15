package com.example.caseapplication.ui.post.repository

import com.example.caseapplication.networking.DataFetchResult
import com.example.caseapplication.response.PostsResponse
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

interface PostFragmentContract {

    interface Remote {
        fun getPosts(): Single<List<PostsResponse>>
    }

    interface Repository {
        val getPostsPublishSubject: PublishSubject<DataFetchResult<List<PostsResponse>>>
        val disposable: CompositeDisposable
        fun getPosts()

        fun <T> handleError(result: PublishSubject<DataFetchResult<T>>, error: Throwable)
    }
}