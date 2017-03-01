package com.hao.mybaselib.presenter;

import com.hao.mybaselib.view.BaseView;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by hao on 2017/3/1.
 */
public class BasePresenterImpl<T extends BaseView> implements BasePresenter<T>{

    protected T mView;
    protected CompositeSubscription mCompositeSubscription;


    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void onStop() {
        unSubscribe();
    }

    @Override
    public void detachView() {
        this.mView =null;
    }

    protected void addSubscrebe(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    protected void unSubscribe(){
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }
}
