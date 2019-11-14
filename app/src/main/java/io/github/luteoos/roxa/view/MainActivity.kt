package io.github.luteoos.roxa.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.github.luteoos.mvvmbaselib.BaseActivityMVVM
import io.github.luteoos.mvvmbaselib.BaseViewModel
import io.github.luteoos.roxa.R

class MainActivity : BaseActivityMVVM<BaseViewModel>() {

    override fun getLayoutID(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.viewModel = getViewModel(this)
    }
}