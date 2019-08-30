package com.hengchang.lib_base.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.hengchang.lib_base.mvp.p.MVPBasePresenter;
import com.hengchang.lib_base.mvp.v.MVPBaseView;
import com.hengchang.lib_base.utils.ReflectUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @author zhangzhilong
 * @date 2019/4/17.
 * description：
 */
public abstract class MVPBaseFragment<P extends MVPBasePresenter> extends BaseFragment implements MVPBaseView {
    protected P mPresenter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = ReflectUtils.getActualTypeArguments(this, 0);
        mPresenter.attachView(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
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
}
