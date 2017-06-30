package com.libs.chang.mulstatelayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.libs.chang.mulstatelayoutlib.MulStateLayoutHelper;

/**
 * 这里展示了修改图标和提示文字的方式，毕竟有时候同样是无数据，出于人性化考虑，不同业务界面展示
 * 的图标和文字还是会有差异的。当然，变化大的，还是重新写一个布局替换吧。
 */
public class CustomViewActivity extends BaseMulStateActivity {
    private MulStateLayoutHelper mMulStateLayoutHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMulStateLayoutHelper.showLoading();
    }

    @Override
    protected MulStateLayoutHelper initMulStateLayoutHelper() {
        /*
         * 这里需要注意，改变图标和提示文字需要这这里设置对应的资源id，之后代码中调用showXXX才行
         */
        mMulStateLayoutHelper = MulStateLayoutHelper.newBuilder(this)
                .loadingLayoutResId(R.layout.loading_layout)
                .contentLayoutResId(R.layout.activity_state_with_retry)
                .emptyLayoutResId(R.layout.empty_layout)
                .errorLayoutResId(R.layout.layout_custom_error)
                .netWorkErrorViewResId(R.layout.network_error_layout)
                .emptyDataIconImageId(R.id.empty_icon_iv)
                .emptyDataTextTipId(R.id.empty_tips_text_tv)
                .build();
        return mMulStateLayoutHelper;
    }

    /**
     * 点击menu按钮时
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.base_toolbar_menu, menu);
        return true;
    }

    /**
     * 点击menu菜单中某一个选项时
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_content:
                mMulStateLayoutHelper.showContent();
                break;
            case R.id.action_emptyData:
                mMulStateLayoutHelper.showEmpty(R.drawable.empty_data, "啥子都莫得");
                break;
            case R.id.action_loading:
                mMulStateLayoutHelper.showLoading();
                break;
            case R.id.action_error:
                mMulStateLayoutHelper.showError();
                break;
            case R.id.action_networkError:
                mMulStateLayoutHelper.showNetWorkError();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
