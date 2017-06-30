package com.libs.chang.mulstatelayoutlib.listener;

import android.view.View;

/**
 * 状态布局显示/隐藏监听器
 *
 * @author 2017/6/26 14:06 / changliugang
 */
public interface OnStateViewShowListener {
    /**
     * 状态布局显示监听
     *
     * @param view 显示的布局
     * @param stateId   显示的布局id 是一下五个中的一个{@link com.libs.chang.mulstatelayoutlib.MulStateLayoutHelper#LAYOUT_LOADING_ID}、
     *             {@link com.libs.chang.mulstatelayoutlib.MulStateLayoutHelper#LAYOUT_CONTENT_ID}、
     *             {@link com.libs.chang.mulstatelayoutlib.MulStateLayoutHelper#LAYOUT_ERROR_ID}、
     *             {@link com.libs.chang.mulstatelayoutlib.MulStateLayoutHelper#LAYOUT_NETWORK_ERROR_ID}、
     *             {@link com.libs.chang.mulstatelayoutlib.MulStateLayoutHelper#LAYOUT_EMPTYDATA_ID}
     */
    void onViewShow(View view, int stateId);

    /**
     * 状态布局隐藏监听
     *
     * @param view 显示的布局
     * @param stateId   显示的布局id 是一下五个中的一个{@link com.libs.chang.mulstatelayoutlib.MulStateLayoutHelper#LAYOUT_LOADING_ID}、
     *             {@link com.libs.chang.mulstatelayoutlib.MulStateLayoutHelper#LAYOUT_CONTENT_ID}、
     *             {@link com.libs.chang.mulstatelayoutlib.MulStateLayoutHelper#LAYOUT_ERROR_ID}、
     *             {@link com.libs.chang.mulstatelayoutlib.MulStateLayoutHelper#LAYOUT_NETWORK_ERROR_ID}、
     *             {@link com.libs.chang.mulstatelayoutlib.MulStateLayoutHelper#LAYOUT_EMPTYDATA_ID}
     */
    void onViewHide(View view, int stateId);

}
