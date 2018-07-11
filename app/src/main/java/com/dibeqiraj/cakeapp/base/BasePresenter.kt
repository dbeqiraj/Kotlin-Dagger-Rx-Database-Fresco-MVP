package com.dibeqiraj.cakeapp.base

import com.dibeqiraj.cakeapp.mvp.view.BaseView
import rx.Observable
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

open class BasePresenter<V : BaseView> {

    @Inject
    protected lateinit var mView: V

    protected fun <T> subscribe(observable: Observable<T>, observer: Observer<T>) {
        observable.subscribeOn(Schedulers.newThread())
                .toSingle()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer)
    }
}