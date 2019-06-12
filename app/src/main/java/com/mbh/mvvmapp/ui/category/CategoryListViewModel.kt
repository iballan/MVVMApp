package com.mbh.mvvmapp.ui.category

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.mbh.mvvmapp.R
import com.mbh.mvvmapp.base.BaseViewModel
import com.mbh.mvvmapp.network.CatProdApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * CreatedBy mbh on 2019-06-11.
 */
class CategoryListViewModel : BaseViewModel() {

    @Inject
    lateinit var catProdApi: CatProdApi

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadCategories() }


    fun loadCategories() {
        addDisposable(
            catProdApi.getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrieveCategoryListStart() }
                .doOnTerminate { onRetrieveCategoryListFinish() }
                .subscribe(
                    { onRetrieveCategoryListSuccess() },
                    { onRetrieveCategoryListError() }
                )
        )
    }

    private fun onRetrieveCategoryListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveCategoryListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveCategoryListSuccess() {

    }

    private fun onRetrieveCategoryListError() {
        errorMessage.value = R.string.category_error
    }
}