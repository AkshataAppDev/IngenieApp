package com.example.ingenieapp.di

import com.example.ingenieapp.ui.CharacterDetailFragment
import com.example.ingenieapp.ui.CharacterListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {
    @ContributesAndroidInjector(modules = [MainViewModelModule::class])
    internal abstract fun contributeCharListFragment(): CharacterListFragment

    @ContributesAndroidInjector(modules = [MainViewModelModule::class])
    internal abstract fun contributeCharDetailFragment(): CharacterDetailFragment
}