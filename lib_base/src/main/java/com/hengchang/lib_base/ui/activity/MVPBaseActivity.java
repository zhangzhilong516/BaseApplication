package com.hengchang.lib_base.ui.activity;

import android.os.Bundle;

import com.hengchang.lib_base.mvp.p.MVPBasePresenter;
import com.hengchang.lib_base.mvp.v.MVPBaseView;
import com.hengchang.lib_base.utils.ReflectUtils;


import androidx.annotation.Nullable;

/**
 * @author: zhangzhilong
 * @date: 2019/2/14
 * @des: Mvp 基类
 */
public abstract class MVPBaseActivity<P extends MVPBasePresenter> extends BaseActivity implements MVPBaseView {
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = ReflectUtils.getActualTypeArguments(this, 0);

        mPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Override
    public void showLoadingView() {

    }

    @Override
    public void dismissLoadingView() {

    }

    public P getPresenter() {
        return mPresenter;
    }

}
