package com.hao.mybaselib.presenter;

import com.hao.mybaselib.view.BaseView;

/**
 * Presenter基类
 * Created by hao on 2017/2/14.
 */
public interface BasePresenter<T extends BaseView>{
    void attachView(T view);

    void onStop();

    void detachView();
}
