package com.example.vlad.DI.screen.addcard

import com.example.vlad.presentation.fragment.addcard.view.AddCardFragment
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(AddCardModule::class))
interface AddCardComponent {
fun inject(addCardFragment: AddCardFragment)
}