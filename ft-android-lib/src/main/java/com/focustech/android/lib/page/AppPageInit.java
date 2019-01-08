package com.focustech.android.lib.page;

import android.view.View;

/**
 * <基于MVVM的页面初始化>
 *
 * @author yanguozhu
 * @version [版本号, 2017-06-21]
 * @see [相关类/方法]
 * @since [V1]
 */

public interface AppPageInit {

    /**
     * 设置DataBinding的绑定数据
     */
    void initData();

    /**
     * 点击事件
     * @param view
     */
    void onClick(View view);

    /**
     * 初始化页面DataBinding
     */
    int getLayoutId();

}
