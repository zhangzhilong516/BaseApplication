package com.hengchang.lib_base.rx;

import com.hengchang.lib_base.mvp.p.MVPBasePresenter;
import com.hengchang.lib_base.rx.RxSchedulers;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author zhangzhilong
 * @date 2019/8/30.
 * description：
 */
public abstract class BaseObserver<T> implements Observer<T> {

    private String mClassName;

    public BaseObserver(String viewClassName) {
        this.mClassName = viewClassName;
    }

    private Disposable mDisposable;

    @Override
    public void onSubscribe(Disposable d) {
        mDisposable = d;
        RxSchedulers.addDisposable(mClassName, d);
    }

    @Override
    public void onNext(T t) {
        OnSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        onError(e);
        dispose();
    }

    @Override
    public void onComplete() {
        dispose();
    }

    private void dispose() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
            RxSchedulers.removeDisposable(mClassName, mDisposable);
        }
    }

    public abstract void OnSuccess(T t);

    public abstract void OnError(Throwable e);
}
