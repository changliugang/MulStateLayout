package com.libs.chang.mulstatelayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.libs.chang.mulstatelayoutlib.MulStateLayoutHelper;
import com.libs.chang.mulstatelayoutlib.listener.OnRetryListener;

/**
 * 这里展示添加重试功能的写法
 */
public class StateWithRetryActivity extends BaseMulStateActivity {

    private MulStateLayoutHelper mMulStateLayoutHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMulStateLayoutHelper.showNetWorkError();
    }

    @Override
    protected MulStateLayoutHelper initMulStateLayoutHelper() {
        /*
         * 加入点击重试功能，需要指定RetryView的Id，并实现OnRetryListener监听，onRetryClick函数中
         * 编写重新请求的逻辑。RetryView的Id需要是对应布局里的控件，否则你懂的。
         */
        mMulStateLayoutHelper = MulStateLayoutHelper.newBuilder(this)
                .loadingLayoutResId(R.layout.loading_layout)
                .contentLayoutResId(R.layout.activity_state_with_retry)
                .emptyLayoutResId(R.layout.empty_layout)
                .errorLayoutResId(R.layout.error_layout)
                .netWorkErrorViewResId(R.layout.network_error_layout)
                .emptyDataRetryViewId(R.id.empty_retry_btn)
                .errorRetryViewId(R.id.error_retry_btn)
                .netWorkErrorRetryViewId(R.id.net_error_retry_btn)
                .onRetryListener(new OnRetryListener() {
                    @Override
                    public void onRetryClick() {
                        mMulStateLayoutHelper.showLoading();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        mMulStateLayoutHelper.showContent();
                                    }
                                });
                            }
                        }).start();

                    }
                })
                .build();
        return mMulStateLayoutHelper;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.base_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_content:
                mMulStateLayoutHelper.showContent();
                break;
            case R.id.action_emptyData:
                mMulStateLayoutHelper.showEmpty();
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
