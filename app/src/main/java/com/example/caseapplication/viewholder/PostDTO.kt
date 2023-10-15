package com.example.caseapplication.viewholder

import android.os.Parcelable
import com.example.caseapplication.constants.RecyclerViewAdapterConstants.TYPES.TYPE_POST
import com.example.caseapplication.recyclerview.DisplayItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostDTO(
    var userId: Int? = null,
    var id: Int? = null,
    var title: String? = null,
    var body: String? = null
) : Parcelable, DisplayItem(TYPE_POST)