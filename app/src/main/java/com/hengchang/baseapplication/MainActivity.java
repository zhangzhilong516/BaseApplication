package com.hengchang.baseapplication;

import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;

import android.os.Bundle;
import android.view.View;

import com.hengchang.lib_base.mvp.p.MVPBasePresenter;
import com.hengchang.lib_base.mvp.v.MVPBaseView;
import com.hengchang.lib_base.net.http.ServiceManager;
import com.hengchang.lib_base.rx.BaseObserver;
import com.hengchang.lib_base.ui.activity.MVPBaseActivity;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

public class MainActivity extends MVPBaseActivity<MainPresenter> implements MVPBaseView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(0)         // (Optional) How many method line to show. Default 2
                .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
                .tag("MyTag")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));


    }

    public void getContacts(View view){
        getPresenter().showLoadingView();
        ServiceManager.getService(ApiService.class)
                .refreshToken(RequestBodyFactory.create("refreshToken"))
                .subscribeOn(Schedulers.io())
                .subscribe(new BaseObserver<Object>(mPresenter.getViewClassName()) {
                    @Override
                    public void OnSuccess(Object o) {

                    }

                    @Override
                    public void OnError(Throwable e) {
                    }
                });
    }
}
