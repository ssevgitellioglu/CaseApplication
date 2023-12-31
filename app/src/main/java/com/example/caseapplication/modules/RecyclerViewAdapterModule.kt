package com.example.caseapplication.modules

import com.example.caseapplication.adapter.CaseApplicationRecyclerViewAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RecyclerViewAdapterModule {

    @Provides
    fun provideAdapter(): CaseApplicationRecyclerViewAdapter {
        return CaseApplicationRecyclerViewAdapter()
    }
}