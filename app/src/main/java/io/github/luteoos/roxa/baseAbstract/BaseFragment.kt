package io.github.luteoos.roxa.baseAbstract

import io.github.luteoos.mvvmbaselib.BaseFragmentMVVM
import io.github.luteoos.mvvmbaselib.BaseViewModel

abstract class BaseFragment<T: BaseViewModel> : BaseFragmentMVVM<T>(), RefreshableFragment {
}