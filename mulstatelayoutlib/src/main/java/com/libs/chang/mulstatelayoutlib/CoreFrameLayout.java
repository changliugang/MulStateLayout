package com.libs.chang.mulstatelayoutlib;

import android.content.Context;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StyleRes;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.libs.chang.mulstatelayoutlib.MulStateLayoutHelper.LAYOUT_CONTENT_ID;
import static com.libs.chang.mulstatelayoutlib.MulStateLayoutHelper.LAYOUT_ERROR_ID;
import static com.libs.chang.mulstatelayoutlib.MulStateLayoutHelper.LAYOUT_LOADING_ID;
import static com.libs.chang.mulstatelayoutlib.MulStateLayoutHelper.LAYOUT_NETWORK_ERROR_ID;

/**
 * 多状态根布局，各种状态的显示表现在这里
 *
 * @author 2017/6/26 11:32 / changliugang
 */
public class CoreFrameLayout extends FrameLayout {


    /**
     * 存放状态布局集合
     */
    private SparseArray<View> mViewSparseArray = new SparseArray<>();
    /**
     * 布局管理帮助类,在MulStateLayoutHelper build的时候就已经关联上了，无需空判断
     */
    private MulStateLayoutHelper mMulStateLayoutHelper;

    public CoreFrameLayout(@NonNull Context context) {
        super(context);
    }

    public CoreFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CoreFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CoreFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setMulStateLayoutHelper(MulStateLayoutHelper mulStateLayoutHelper) {
        mMulStateLayoutHelper = mulStateLayoutHelper;
        addAllLayoutToRoot();
    }

    /**
     * 添加所有状态布局
     */
    private void addAllLayoutToRoot() {
        if (mMulStateLayoutHelper.mContentLayoutResId != 0)
            addLayoutResId(mMulStateLayoutHelper.mContentLayoutResId, LAYOUT_CONTENT_ID);
        if (mMulStateLayoutHelper.mLoadingLayoutResId != 0)
            addLayoutResId(mMulStateLayoutHelper.mLoadingLayoutResId, LAYOUT_LOADING_ID);

        if (mMulStateLayoutHelper.mEmptyViewStub != null)
            addView(mMulStateLayoutHelper.mEmptyViewStub);
        if (mMulStateLayoutHelper.mErrorViewStub != null)
            addView(mMulStateLayoutHelper.mErrorViewStub);
        if (mMulStateLayoutHelper.mNetWorkErrorViewStub != null)
            addView(mMulStateLayoutHelper.mNetWorkErrorViewStub);
    }

    /**
     * 添加指定状态布局
     *
     * @param layoutResId 布局资源id
     * @param id          指定的状态布局ID
     */
    private void addLayoutResId(@LayoutRes int layoutResId, int id) {
        View layoutView = LayoutInflater.from(mMulStateLayoutHelper.mContext).inflate(layoutResId, null);
        // 这里将非内容视图都隐藏下，避免试图叠加
        if (id != LAYOUT_CONTENT_ID) {
            layoutView.setVisibility(GONE);
        }
        mViewSparseArray.put(id, layoutView);
        addView(layoutView);
    }

    /**
     * 显示加载
     */
    public void showLoadingLayout() {
        if (mViewSparseArray.get(LAYOUT_LOADING_ID) != null) {
            showStateViewById(LAYOUT_LOADING_ID);
        } else {
            Toast.makeText(mMulStateLayoutHelper.mContext, "First of all , You need a loading layout", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 显示内容
     */
    public void showContentLayout() {
        if (mViewSparseArray.get(LAYOUT_CONTENT_ID) != null) {
            showStateViewById(LAYOUT_CONTENT_ID);
        } else {
            Toast.makeText(mMulStateLayoutHelper.mContext, "First of all , You need a content layout", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 显示网络错误
     */
    public void showNetWorkErrorLayout() {
        if (inflateLayout(LAYOUT_NETWORK_ERROR_ID)) {
            showStateViewById(LAYOUT_NETWORK_ERROR_ID);
        } else {
            Toast.makeText(mMulStateLayoutHelper.mContext, "First of all , You need a network error layout", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 显示请求错误布局
     */
    public void showErrorLayout() {
        showErrorLayout(0, "");
    }

    /**
     * 显示请求错误布局
     *
     * @param iconResId 图标
     * @param tipsText  提示文字
     */
    public void showErrorLayout(@DrawableRes int iconResId, String tipsText) {
        if (inflateLayout(LAYOUT_ERROR_ID)) {
            showStateViewById(LAYOUT_ERROR_ID);
            bindLayoutEle(LAYOUT_ERROR_ID, mMulStateLayoutHelper.mErrorIconImageId,
                    mMulStateLayoutHelper.mErrorTextTipId, iconResId, tipsText);
        } else {
            Toast.makeText(mMulStateLayoutHelper.mContext, "First of all , You need an error layout", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 在布局上绑定图标和提示文字
     *
     * @param stateId           指定的状态布局ID
     * @param iconViewResId     图标控件id
     * @param tipsTextViewResId 提示文字控件id
     * @param iconResId         图标
     * @param tipsText          提示文字
     */
    private void bindLayoutEle(int stateId, @IdRes int iconViewResId, @IdRes int tipsTextViewResId, @DrawableRes int iconResId, String tipsText) {
        if (iconResId == 0 && TextUtils.isEmpty(tipsText)) {
            return;
        }
        View view = mViewSparseArray.get(stateId);
        if (iconViewResId != 0) {
            View icon = view.findViewById(iconViewResId);
            if (icon != null) {
                ((ImageView) icon).setImageResource(iconResId);
            }
        }
        if (tipsTextViewResId != 0) {
            View tips = view.findViewById(tipsTextViewResId);
            if (tips != null) {
                ((TextView) tips).setText(tipsText);
            }
        }
    }

    /**
     * 显示空布局
     */
    public void showEmptyLayout() {
        showEmptyLayout(0, "");
    }

    /**
     * 显示空布局
     *
     * @param iconResId 图标
     * @param tipsText  提示文字
     */
    public void showEmptyLayout(@DrawableRes int iconResId, String tipsText) {
        if (inflateLayout(MulStateLayoutHelper.LAYOUT_EMPTYDATA_ID)) {
            showStateViewById(MulStateLayoutHelper.LAYOUT_EMPTYDATA_ID);
            bindLayoutEle(MulStateLayoutHelper.LAYOUT_EMPTYDATA_ID, mMulStateLayoutHelper.mEmptyDataIconImageId,
                    mMulStateLayoutHelper.mEmptyDataTextTipId, iconResId, tipsText);
        } else {
            Toast.makeText(mMulStateLayoutHelper.mContext, "First of all , You need an empty layout", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 根据状态布局ID显示状态布局
     *
     * @param id 当前状态布局ID
     */
    private void showStateViewById(int id) {
        for (int i = 0; i < mViewSparseArray.size(); i++) {
            int keyId = mViewSparseArray.keyAt(i);
            View valueView = mViewSparseArray.valueAt(i);
            if (keyId == id) {
                valueView.setVisibility(VISIBLE);
                if (mMulStateLayoutHelper.mOnStateViewShowListener != null)
                    mMulStateLayoutHelper.mOnStateViewShowListener.onViewShow(valueView, keyId);
            } else {
                if (valueView.getVisibility() != GONE) {
                    valueView.setVisibility(GONE);
                    if (mMulStateLayoutHelper.mOnStateViewShowListener != null)
                        mMulStateLayoutHelper.mOnStateViewShowListener.onViewHide(valueView, keyId);
                }
            }
        }
    }

    /**
     * 生成布局
     *
     * @param id 当前状态布局ID
     * @return 是否显示
     */
    private boolean inflateLayout(int id) {
        boolean isShow = true;
        // 避免重复加载布局
        if (mViewSparseArray.get(id) != null) return true;
        switch (id) {
            case LAYOUT_NETWORK_ERROR_ID:
                if (mMulStateLayoutHelper.mNetWorkErrorViewStub != null) {
                    View netWorkErrorView = mMulStateLayoutHelper.mNetWorkErrorViewStub.inflate();
                    onRetryClick(netWorkErrorView, mMulStateLayoutHelper.mNetWorkErrorRetryViewId);
                    mViewSparseArray.put(id, netWorkErrorView);
                    isShow = true;
                } else {
                    isShow = false;
                }
                break;
            case LAYOUT_ERROR_ID:
                isShow = handleView(mMulStateLayoutHelper.mErrorViewStub, mMulStateLayoutHelper.mErrorRetryViewId, id);
                break;
            case MulStateLayoutHelper.LAYOUT_EMPTYDATA_ID:
                isShow = handleView(mMulStateLayoutHelper.mEmptyViewStub, mMulStateLayoutHelper.mEmptyDataRetryViewId, id);
                break;
        }

        return isShow;
    }

    /**
     * 处理布局相关事件
     *
     * @param viewStub    该状态布局
     * @param retryViewId 该状态布局中重试按钮的资源id
     * @param id          状态id
     * @return 该状态布局是否显示
     */
    private boolean handleView(ViewStub viewStub, @IdRes int retryViewId, int id) {
        if (viewStub != null) {
            View view = viewStub.inflate();

            onRetryClick(view, retryViewId);
            mViewSparseArray.put(id, view);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 重试按钮点击
     *
     * @param view 当前状态布局
     * @param id   当前状态布局资源ID
     */
    private void onRetryClick(View view, int id) {
        View retryView = view.findViewById(id);
        if (retryView == null || mMulStateLayoutHelper.mOnRetryListener == null) return;
        retryView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mMulStateLayoutHelper.mOnRetryListener.onRetryClick();
            }
        });
    }

}
