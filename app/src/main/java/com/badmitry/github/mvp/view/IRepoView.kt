package com.badmitry.github.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface IRepoView : MvpView {
    fun setRepoName(name: String)
    fun setRepoIsFork(name: String)
}