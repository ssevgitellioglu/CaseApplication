package com.example.caseapplication.ui.post.viewmodel


import androidx.lifecycle.map
import com.example.caseapplication.base.BaseViewModel
import com.example.caseapplication.extensions.toLiveData
import com.example.caseapplication.networking.DataFetchResult
import com.example.caseapplication.recyclerview.DisplayItem
import com.example.caseapplication.response.PostsResponse
import com.example.caseapplication.ui.post.repository.PostFragmentRepository
import com.example.caseapplication.viewholder.PostDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

@HiltViewModel
class PostFragmentViewModel @Inject constructor(
    private val repository: PostFragmentRepository
) : BaseViewModel() {

    val itemPublishSubject = PublishSubject.create<List<DisplayItem>>()

    val getPostsLiveData = repository.getPostsPublishSubject.toLiveData(disposables).map {
            response ->
        when (response) {
            is DataFetchResult.Success -> {
                consumeData(response.data)
            }
            is DataFetchResult.Progress -> {
            }
            is DataFetchResult.Failure -> {
            }
        }

          response
    }



    private fun consumeData(data: List<PostsResponse>) {
        val list = mutableListOf<DisplayItem>()

        data.map { getPostsResponse ->
            list.add(
                PostDTO(
                    title = getPostsResponse.title,
                    id = getPostsResponse.id,
                    userId = getPostsResponse.userId,
                    body = getPostsResponse.body
                )
            )
        }

        itemPublishSubject.onNext(list)
    }

    fun getPosts() = repository.getPosts()
}