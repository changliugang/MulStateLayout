package com.libs.chang.mulstatelayoutlib;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import com.libs.chang.mulstatelayoutlib.listener.OnRetryListener;
import com.libs.chang.mulstatelayoutlib.listener.OnStateViewShowListener;

/**
 * 多状态布局使用帮助类，Builder模式
 *
 * @author 2017/6/26 10:50 / changliugang
 */
public class MulStateLayoutHelper {
    /**
     * loading 加载id
     */
    public static final int LAYOUT_LOADING_ID = 1;

    /**
     * 内容id
     */
    public static final int LAYOUT_CONTENT_ID = 2;

    /**
     * 异常id
     */
    public static final int LAYOUT_ERROR_ID = 3;

    /**
     * 网络异常id
     */
    public static final int LAYOUT_NETWORK_ERROR_ID = 4;

    /**
     * 空数据id
     */
    public static final int LAYOUT_EMPTYDATA_ID = 5;
    /*
     允许同包下使用，就不省掉了范围修饰关键字了
     */

    // 这个还用我说嘛
    Context mContext;

    // 网络错误视图
    ViewStub mNetWorkErrorViewStub;
    // 空视图
    ViewStub mEmptyViewStub;
    // 错误视图
    ViewStub mErrorViewStub;

    // 网络错误重试按钮资源Id，绑定点击事件用到
    int mNetWorkErrorRetryViewId;
    // 无数据重试按钮资源Id，绑定点击事件用到
    int mEmptyDataRetryViewId;
    // 请求错误重试按钮资源Id，绑定点击事件用到
    int mErrorRetryViewId;

    // 加载中布局id
    int mLoadingLayoutResId;
    // 内容布局id
    int mContentLayoutResId;

    // 无数据图标资源id
    int mEmptyDataIconImageId;
    // 无数据提示文字资源id
    int mEmptyDataTextTipId;

    // 错误图标资源id
    int mErrorIconImageId;
    // 错误提示文字资源id
    int mErrorTextTipId;

    // 多状态核心布局
    private CoreFrameLayout mCoreFrameLayout;
    // 多状态布局显示监听
    OnStateViewShowListener mOnStateViewShowListener;
    // 重试按钮点击监听
    OnRetryListener mOnRetryListener;

    public MulStateLayoutHelper(Builder builder) {
        mContext = builder.mContext;
        mNetWorkErrorViewStub = builder.mNetWorkErrorViewStub;
        mEmptyViewStub = builder.mEmptyViewStub;
        mErrorViewStub = builder.mErrorViewStub;
        mNetWorkErrorRetryViewId = builder.mNetWorkErrorRetryViewId;
        mEmptyDataRetryViewId = builder.mEmptyDataRetryViewId;
        mErrorRetryViewId = builder.mErrorRetryViewId;
        mLoadingLayoutResId = builder.mLoadingLayoutResId;
        mContentLayoutResId = builder.mContentLayoutResId;
        mEmptyDataIconImageId = builder.mEmptyDataIconImageId;
        mEmptyDataTextTipId = builder.mEmptyDataTextTipId;
        mErrorIconImageId = builder.mErrorIconImageId;
        mErrorTextTipId = builder.mErrorTextTipId;
        mOnStateViewShowListener = builder.mOnStateViewShowListener;
        mOnRetryListener = builder.mOnRetryListener;
        // MulStateLayoutHelper创建的同时，创建RootFrameLayout并绑定之
        mCoreFrameLayout = new CoreFrameLayout(mContext);
        mCoreFrameLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mCoreFrameLayout.setMulStateLayoutHelper(this);
    }

    public static Builder newBuilder(Context context) {
        return new Builder(context);
    }

    public CoreFrameLayout getCoreFrameLayout() {
        return mCoreFrameLayout;
    }

    /**
     * 根据状态布局ID查找布局
     *
     * @param id 当前状态布局ID
     * @retur
     */
    public View getViewByStateId(int id) {
        return mCoreFrameLayout.getViewByStateId(id);
    }

    /**
     * 获取当前显示界面的状态值
     * @return 当前状态布局ID
     */
    public int getCurState(){
        return mCoreFrameLayout.getCurState();
    }

    /**
     * 显示加载
     */
    public void showLoading() {
        mCoreFrameLayout.showLoadingLayout();
    }

    /**
     * 显示加载
     */
    public void showContent() {
        mCoreFrameLayout.showContentLayout();
    }

    /**
     * 显示网络错误
     */
    public void showNetWorkError() {
        mCoreFrameLayout.showNetWorkErrorLayout();
    }

    /**
     * 显示错误
     */
    public void showError() {
        mCoreFrameLayout.showErrorLayout();
    }

    /**
     * 显示错误
     *
     * @param iconResId 图标
     * @param tipsText  提示文字
     */
    public void showError(@DrawableRes int iconResId, String tipsText) {
        mCoreFrameLayout.showErrorLayout(iconResId, tipsText);
    }

    /**
     * 显示无数据
     */
    public void showEmpty() {
        mCoreFrameLayout.showEmptyLayout();
    }

    /**
     * 显示无数据
     *
     * @param iconResId 图标
     * @param tipsText  提示文字
     */
    public void showEmpty(@DrawableRes int iconResId, String tipsText) {
        mCoreFrameLayout.showEmptyLayout(iconResId, tipsText);
    }

    public static final class Builder {

        private Context mContext;

        private ViewStub mNetWorkErrorViewStub;
        private ViewStub mEmptyViewStub;
        private ViewStub mErrorViewStub;

        private int mNetWorkErrorRetryViewId;
        private int mEmptyDataRetryViewId;
        private int mErrorRetryViewId;

        private int mLoadingLayoutResId;
        private int mContentLayoutResId;

        private int mEmptyDataIconImageId;
        private int mEmptyDataTextTipId;

        private int mErrorIconImageId;
        private int mErrorTextTipId;

        private OnStateViewShowListener mOnStateViewShowListener;
        private OnRetryListener mOnRetryListener;

        public Builder(Context context) {
            this.mContext = context;
        }

        public MulStateLayoutHelper build() {
            return new MulStateLayoutHelper(this);
        }

        public Builder netWorkErrorViewResId(@LayoutRes int netWorkErrorViewId) {
            mNetWorkErrorViewStub = new ViewStub(mContext);
            mNetWorkErrorViewStub.setLayoutResource(netWorkErrorViewId);
            return this;
        }

        public Builder emptyLayoutResId(@LayoutRes int emptyViewId) {
            mEmptyViewStub = new ViewStub(mContext);
            mEmptyViewStub.setLayoutResource(emptyViewId);
            return this;
        }

        public Builder errorLayoutResId(@LayoutRes int errorViewId) {
            mErrorViewStub = new ViewStub(mContext);
            mErrorViewStub.setLayoutResource(errorViewId);
            return this;
        }

        public Builder netWorkErrorRetryViewId(@IdRes int netWorkErrorRetryViewId) {
            mNetWorkErrorRetryViewId = netWorkErrorRetryViewId;
            return this;
        }

        public Builder emptyDataRetryViewId(@IdRes int emptyDataRetryViewId) {
            mEmptyDataRetryViewId = emptyDataRetryViewId;
            return this;
        }

        public Builder errorRetryViewId(@IdRes int errorRetryViewId) {
            mErrorRetryViewId = errorRetryViewId;
            return this;
        }

        public Builder loadingLayoutResId(@LayoutRes int loadingLayoutResId) {
            mLoadingLayoutResId = loadingLayoutResId;
            return this;
        }

        public Builder contentLayoutResId(@LayoutRes int contentLayoutResId) {
            mContentLayoutResId = contentLayoutResId;
            return this;
        }

        public Builder emptyDataIconImageId(@IdRes int emptyDataIconImageId) {
            mEmptyDataIconImageId = emptyDataIconImageId;
            return this;
        }

        public Builder emptyDataTextTipId(@IdRes int emptyDataTextTipId) {
            mEmptyDataTextTipId = emptyDataTextTipId;
            return this;
        }

        public Builder errorIconImageId(@IdRes int errorIconImageId) {
            mErrorIconImageId = errorIconImageId;
            return this;
        }

        public Builder errorTextTipId(@IdRes int errorTextTipId) {
            mErrorTextTipId = errorTextTipId;
            return this;
        }

        public Builder onStateViewShowListener(OnStateViewShowListener onStateViewShowListener) {
            mOnStateViewShowListener = onStateViewShowListener;
            return this;
        }

        public Builder onRetryListener(OnRetryListener onRetryListener) {
            mOnRetryListener = onRetryListener;
            return this;
        }
    }

}
