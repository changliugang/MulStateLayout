package com.libs.chang.mulstatelayoutlib.listener;

import android.support.annotation.IdRes;

/**
 * 点击重试监听器
 *
 * @author 2017/6/26 14:04 / changliugang
 */
public interface OnRetryListener {

    /**
     * 点击重试按钮回调
     * @param id 重试按钮资源id
     */
    void onRetryClick(@IdRes final int id);

}
