package com.example.vlad.DI.screen.entries

import com.example.vlad.presentation.fragment.entries.view.EntriesFragment
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(EntriesModule::class))
interface EntriesComponent {
    fun inject(entriesFragment: EntriesFragment)
}