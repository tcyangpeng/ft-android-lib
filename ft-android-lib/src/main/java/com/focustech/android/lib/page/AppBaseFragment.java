package com.focustech.android.lib.page;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * <基于MVVM的BaseFragment>
 *
 * @author yanguozhu
 * @version [版本号, 2017-06-21]
 * @see [相关类/方法]
 * @since [V1]
 */

public abstract class AppBaseFragment<T extends ViewDataBinding> extends Fragment implements AppPageInit {

    /**
     * DataBinding
     */
    protected T binding;

    public final String TAG = this.getClass().getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        // 初始化页面数据
        initData();
        return binding.getRoot();
    }
}
