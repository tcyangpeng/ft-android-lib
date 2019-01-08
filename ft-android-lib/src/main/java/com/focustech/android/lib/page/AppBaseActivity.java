package com.focustech.android.lib.page;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * <基于MVVM的BaseActivity>
 *
 * @author yanguozhu
 * @version [版本号, 2017-06-20]
 * @see [相关类/方法]
 * @since [V1]
 */

public abstract class AppBaseActivity<T extends ViewDataBinding> extends AppCompatActivity implements AppPageInit {

    /**
     * DataBinding
     */
    protected T binding;

    protected final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, getLayoutId());
        initData();
    }

}
