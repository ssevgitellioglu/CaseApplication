package com.example.caseapplication.ui.post

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.caseapplication.adapter.CaseApplicationRecyclerViewAdapter
import com.example.caseapplication.base.BaseFragment
import com.example.caseapplication.databinding.FragmentPostBinding
import com.example.caseapplication.extensions.setup
import com.example.caseapplication.networking.DataFetchResult
import com.example.caseapplication.ui.post.viewmodel.PostFragmentViewModel
import com.example.caseapplication.ui.postdetail.PostDetailFragment
import com.example.caseapplication.viewholder.PostDTO

import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PostFragment :
    BaseFragment<PostFragmentViewModel, FragmentPostBinding>(FragmentPostBinding::inflate) {

    override val viewModel: PostFragmentViewModel by viewModels()

    @Inject
    lateinit var adapter: CaseApplicationRecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        bindObserver()
        setupItemTouchHelper()
        adapter.getAdapter().itemClickListener = { item, position ->
            when (item) {
                is PostDTO -> {
                    val postDetail = PostDetailFragment(item) { _item ->
                        val adapterList = adapter.getAdapter().items.toMutableList()
                        val newList = adapter.getAdapter().items.toMutableList()

                        adapterList.mapIndexed { index, listItem ->
                            when (listItem) {
                                is PostDTO -> {
                                    if (listItem.id == _item.id) {
                                        newList.set(index = index, element = _item)
                                    }
                                }
                                else -> {
                                    return@PostDetailFragment
                                }
                            }
                        }

                        adapter.getAdapter().updateDiffItemsOnly(newList)
                    }

                    postDetail.show(parentFragmentManager, null)
                }
            }
        }
    }

    private fun setupView() {
        with(receiver = binding) {
            this?.recyclerView?.setup(adapter = adapter.getAdapter())
            this?.swipeRefreshLayout?.setOnRefreshListener {
                bindObserver()
                this.swipeRefreshLayout.isRefreshing = false
            }
        }
    }

    private fun setupItemTouchHelper() {
        val itemTouchHelper = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean = true

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adapter.getAdapter().remove(viewHolder.adapterPosition)
            }
        }
        ItemTouchHelper(itemTouchHelper).apply {
            attachToRecyclerView(binding?.recyclerView)
        }
    }

    private fun bindObserver() {
        viewModel.getPosts()
        viewModel.getPostsLiveData.observe(viewLifecycleOwner) { response ->
            when (response) {
                is DataFetchResult.Success -> {
                }
                is DataFetchResult.Progress -> {

                }
                is DataFetchResult.Failure -> {
                }
            }
        }
        viewModel.itemPublishSubject.subscribe {
            adapter.getAdapter().updateAllItems(it)
        }.isDisposed
    }

    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle) =
            PostFragment().apply {
                bundle.let {
                    arguments = bundle
                }
            }
    }
}