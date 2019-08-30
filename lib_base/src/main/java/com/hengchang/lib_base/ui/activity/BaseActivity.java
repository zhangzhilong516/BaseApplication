package com.hengchang.lib_base.ui.activity;

import android.app.Activity;
import android.os.Bundle;

import com.hengchang.lib_base.common.ActivityManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author zhangzhilong
 * @date 2019/8/29.
 * description：
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
