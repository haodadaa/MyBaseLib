package com.hao.mybaselib.util;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 *
 *
 * Created by hao on 2017/3/1.
 */

public class RxSchedulerUtils {

    /**
     * 简化线程
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<T, T> normalSchedulersTransformer() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
