package com.hengchang.lib_base.rx;

import org.reactivestreams.Publisher;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

/**
 * @author zhangzhilong
 * @date 2019/8/29.
 * description：
 */
public class RxSchedulers {

    static { //RxJava2 取消订阅后，抛出的异常无法捕获，导致程序崩溃
        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable e) throws Exception {
                e.printStackTrace();
            }
        });
    }

    public static <T> ObservableTransformer<T, T> obs_io_main() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


    public static <T> FlowableTransformer<T, T> flo_io_main() {
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(Flowable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    private static final Map<String, CompositeDisposable> sDisposableMap = new ConcurrentHashMap<>();

    public static void addDisposable(String className, Disposable disposable) {
        CompositeDisposable compositeDisposable = sDisposableMap.get(className);
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
            compositeDisposable.add(disposable);
            sDisposableMap.put(className, compositeDisposable);
        }
        compositeDisposable.add(disposable);
    }

    public static void removeDisposable(String className, Disposable disposable) {
        CompositeDisposable compositeDisposable = sDisposableMap.get(className);
        if (compositeDisposable != null) {
            compositeDisposable.remove(disposable);
            if (compositeDisposable.size() == 0) {
                sDisposableMap.remove(className);
            }
        }
    }

    public static void dispose(String className) {
        CompositeDisposable compositeDisposable = sDisposableMap.get(className);
        if (compositeDisposable != null) {
            compositeDisposable.clear();
            sDisposableMap.remove(className);
        }
    }
}
