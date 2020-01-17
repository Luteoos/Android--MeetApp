package io.github.luteoos.roxa.baseAbstract

import android.os.Bundle
import io.github.luteoos.mvvmbaselib.BaseActivityMVVM
import io.github.luteoos.mvvmbaselib.BaseViewModel

abstract class BaseActivity<T: BaseViewModel> : BaseActivityMVVM<T>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setPortraitOrientation(true)
    }
}