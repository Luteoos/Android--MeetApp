package io.github.luteoos.roxa.baseAbstract

import io.github.luteoos.mvvmbaselib.BaseActivityMVVM
import io.github.luteoos.mvvmbaselib.BaseViewModel

abstract class BaseActivity<T: BaseViewModel> : BaseActivityMVVM<T>() {
}