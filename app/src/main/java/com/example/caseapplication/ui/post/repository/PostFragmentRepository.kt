package com.example.caseapplication.ui.post.repository

import com.example.caseapplication.extensions.*
import com.example.caseapplication.networking.DataFetchResult
import com.example.caseapplication.networking.Scheduler
import com.example.caseapplication.response.PostsResponse
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import timber.log.Timber
import javax.inject.Inject

class PostFragmentRepository @Inject constructor(
    private val remote: PostFragmentRemoteData,
    private val scheduler: Scheduler
) : PostFragmentContract.Repository {

    override val getPostsPublishSubject = PublishSubject.create<DataFetchResult<List<PostsResponse>>>()
    override val disposable: CompositeDisposable = CompositeDisposable()

    override fun getPosts() {
        getPostsPublishSubject.loading(isLoading = true)
        remote.getPosts()
            .performOnBackOutOnMain(scheduler)
            .subscribe(
                { response ->
                    getPostsPublishSubject.success(response)
                },
                { error ->
                    handleError(getPostsPublishSubject, error)
                }
            ).addTo(disposable)
    }

    override fun <T> handleError(result: PublishSubject<DataFetchResult<T>>, error: Throwable) {
        result.failed(error)
        Timber.e(error)
    }
}