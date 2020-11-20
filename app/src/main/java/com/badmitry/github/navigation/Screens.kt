package com.badmitry.github.navigation

import com.badmitry.github.ui.fragment.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class UserScreen(): SupportAppScreen() {
        override fun getFragment() = UsersFragment.newInstance()
    }
}