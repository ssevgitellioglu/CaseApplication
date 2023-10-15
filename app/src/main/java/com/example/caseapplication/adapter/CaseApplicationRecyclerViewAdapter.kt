package com.example.caseapplication.adapter

import com.example.caseapplication.constants.RecyclerViewAdapterConstants
import com.example.caseapplication.recyclerview.DefaultDisplayItemComperator
import com.example.caseapplication.recyclerview.RecyclerViewAdapter

class CaseApplicationRecyclerViewAdapter {

    fun getAdapter() = _adapter

    private val _adapter = RecyclerViewAdapter(
        itemComparator = DefaultDisplayItemComperator(),
        viewBinderFactoryMap = RecyclerViewAdapterConstants().binderFactoryMap,
        viewHolderFactoryMap = RecyclerViewAdapterConstants().holderFactoryMap
    )
}