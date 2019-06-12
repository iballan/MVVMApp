package com.mbh.mvvmapp.network

import com.mbh.mvvmapp.model.Category
import com.mbh.mvvmapp.model.Product
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * CreatedBy mbh on 2019-06-11.
 */
interface CatProdApi {
    /**
     * Get the list of the pots from the API
     */
    @GET("/category")
    fun getCategories(): Observable<List<Category>>

    @GET("/product")
    fun getProducts(): Observable<List<Product>>
}