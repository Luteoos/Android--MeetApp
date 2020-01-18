package io.github.luteoos.roxa.baseAbstract

import io.github.luteoos.mvvmbaselib.BaseViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : BaseViewModel() {
    val disposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }

}