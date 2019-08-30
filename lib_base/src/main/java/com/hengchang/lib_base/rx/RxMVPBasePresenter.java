package com.hengchang.lib_base.rx;

import com.hengchang.lib_base.mvp.m.MVPBaseModel;
import com.hengchang.lib_base.mvp.p.MVPBasePresenter;
import com.hengchang.lib_base.mvp.v.MVPBaseView;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author zhangzhilong
 * @date 2019/8/29.
 * description：
 */
public abstract class RxMVPBasePresenter<V extends MVPBaseView, M extends MVPBaseModel> extends MVPBasePresenter<V, M> {

    @Override
    public void attachView(V view) {
        super.attachView(view);
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void detachView() {
        super.detachView();
        mCompositeDisposable.clear();
    }

    private CompositeDisposable mCompositeDisposable;

    abstract class NetCallback<T> implements Observer<T> {
        private Disposable mDisposable;

        @Override
        public void onSubscribe(Disposable d) {
            mDisposable = d;
            mCompositeDisposable.add(mDisposable);
        }

        @Override
        public void onNext(T t) {
            onSuccess(t);
        }

        @Override
        public void onError(Throwable e) {
            dispose();
        }

        @Override
        public void onComplete() {
            dispose();
        }

        private void dispose() {
            if (mDisposable != null) {
                mCompositeDisposable.remove(mDisposable);
            }
        }

        public abstract void onSuccess(T t);
    }
}
