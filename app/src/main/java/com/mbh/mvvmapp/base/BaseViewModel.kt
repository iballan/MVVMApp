package com.mbh.mvvmapp.base

import androidx.lifecycle.ViewModel
import com.mbh.mvvmapp.injection.component.DaggerViewModelInjector
import com.mbh.mvvmapp.injection.component.ViewModelInjector
import com.mbh.mvvmapp.injection.module.NetworkModule
import com.mbh.mvvmapp.ui.category.CategoryListViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * CreatedBy mbh on 2019-06-11.
 */

abstract class BaseViewModel : ViewModel() {
    private var disposables: CompositeDisposable? = null

    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is CategoryListViewModel -> injector.inject(this)
        }
    }

    protected fun addDisposable(disposable: Disposable) {
        compositeDisposables?.add(disposable)
    }

    private val compositeDisposables: CompositeDisposable?
        get() {
            if (disposables == null) {
                disposables = CompositeDisposable()
            }
            return disposables
        }


    override fun onCleared() {
        super.onCleared()
        if (disposables != null) {
            disposables!!.dispose()
            disposables = null
        }
    }
}