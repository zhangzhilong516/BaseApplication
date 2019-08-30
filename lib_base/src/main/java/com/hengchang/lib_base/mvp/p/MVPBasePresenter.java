package com.hengchang.lib_base.mvp.p;

import com.hengchang.lib_base.mvp.m.MVPBaseModel;
import com.hengchang.lib_base.mvp.v.MVPBaseView;
import com.hengchang.lib_base.utils.ReflectUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author: zhangzhilong
 * @date: 2019/2/14
 * @des: Presenter 基类
 */
public abstract class MVPBasePresenter<V extends MVPBaseView, M extends MVPBaseModel> {
    private V mView;
    private M mModel;
    private V mProxyView;

    public MVPBasePresenter() {
        mModel = ReflectUtils.getActualTypeArguments(this, 1);
    }

    public MVPBasePresenter(V view, M model) {
        this.mModel = model;
        initProxyView(view);
    }

    public void attachView(V view) {
        initProxyView(view);
        mModel = ReflectUtils.getActualTypeArguments(this, 1);
    }


    public void detachView() {
        this.mView = null;
    }


    public void showLoadingView() {
        mProxyView.showLoadingView();
    }

    public void dismissLoadingView() {
        mProxyView.dismissLoadingView();
    }

    public M getModel() {
        return mModel;
    }

    public V getView() {
        return mProxyView;
    }

    public String getViewClassName(){
        return mProxyView.getClass().getSimpleName();
    }

    /**
     * 初始化代理View
     */
    private void initProxyView(V view) {
        this.mView = view;
        mProxyView = (V) Proxy.newProxyInstance(view.getClass().getClassLoader(),
                view.getClass().getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if (mView == null) {
                            return null;
                        }
                        return method.invoke(mView, args);
                    }
                });
    }

}
