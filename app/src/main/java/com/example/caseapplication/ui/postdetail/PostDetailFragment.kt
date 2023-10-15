package com.example.caseapplication.ui.postdetail

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.caseapplication.base.BaseBottomSheetDialog
import com.example.caseapplication.constants.AppConstants.IMAGE_URL
import com.example.caseapplication.databinding.FragmentPostDetailBinding
import com.example.caseapplication.extensions.loadImage
import com.example.caseapplication.viewholder.PostDTO
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PostDetailFragment(
    private val item: PostDTO,
    private val update: (item: PostDTO) -> Unit
) : BaseBottomSheetDialog<FragmentPostDetailBinding>(FragmentPostDetailBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
    }

    private fun setupView() {
        with(receiver = binding) {
            this?.edtTitle?.setText(item.title)
            this?.edtBody?.setText(item.body).toString().replaceFirstChar {
                it.uppercase()
            }
            this?.ivPostLogo?.loadImage(url = "${IMAGE_URL}${item.id}")
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)

        val copyItem = item.copy()
        copyItem.title = binding?.edtTitle?.text?.toString()
        copyItem.body = binding?.edtBody?.text?.toString().toString().replaceFirstChar {
            it.uppercase()
        }
        update(copyItem)
    }
}