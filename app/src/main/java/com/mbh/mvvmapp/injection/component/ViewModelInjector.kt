package com.mbh.mvvmapp.injection.component

import com.mbh.mvvmapp.injection.module.NetworkModule
import com.mbh.mvvmapp.ui.category.CategoryListViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * CreatedBy mbh on 2019-06-11.
 */
@Singleton
@Component(modules = [NetworkModule::class])
interface ViewModelInjector {

    fun inject(categoryListViewModel: CategoryListViewModel)


    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}