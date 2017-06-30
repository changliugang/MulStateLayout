package com.libs.chang.mulstatelayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.libs.chang.mulstatelayoutlib.MulStateLayoutHelper;
import com.libs.chang.mulstatelayoutlib.listener.OnStateViewShowListener;

/**
 * 可以专门制定一个多状态布局页面的基类，同意处理多种状态布局的配置，毕竟大多数情况下程序这方面是统一
 * 的，有特例的可以通过重写{@link #initMulStateLayoutHelper()}实现
 *
 * @author 2017/6/28 16:24 / changliugang
 */
public class BaseMulStateActivity extends AppCompatActivity {

    // 供子类使用
    protected MulStateLayoutHelper mMulStateLayoutHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_mul_state);
        // 把多状态布局加入基类布局
        RelativeLayout rootLayout = (RelativeLayout) findViewById(R.id.base_mul_state_root_layout_rl);
        rootLayout.addView(initMulStateLayoutHelper().getCoreFrameLayout());
    }

    /**
     * 这里进行MulStateLayoutHelper的创建、配置
     *
     * @return 指定的帮助类
     */
    protected MulStateLayoutHelper initMulStateLayoutHelper() {
        mMulStateLayoutHelper = MulStateLayoutHelper.newBuilder(this)
                .loadingLayoutResId(R.layout.loading_layout)
                .contentLayoutResId(R.layout.activity_normal)
                .emptyLayoutResId(R.layout.empty_layout)
                .errorLayoutResId(R.layout.error_layout)
                .netWorkErrorViewResId(R.layout.network_error_layout)
                .onStateViewShowListener(new OnStateViewShowListener() {
                    @Override
                    public void onViewShow(View view, int stateId) {
                        String text = "";
                        switch (stateId) {
                            case MulStateLayoutHelper.LAYOUT_CONTENT_ID:
                                text = "content";
                                break;
                            case MulStateLayoutHelper.LAYOUT_EMPTYDATA_ID:
                                text = "empty";
                                break;
                            case MulStateLayoutHelper.LAYOUT_ERROR_ID:
                                text = "error";
                                break;
                            case MulStateLayoutHelper.LAYOUT_LOADING_ID:
                                text = "loading";
                                break;
                            case MulStateLayoutHelper.LAYOUT_NETWORK_ERROR_ID:
                                text = "network error";
                                break;
                        }
                        Log.d("chang", text + "突然出现");
                        Toast.makeText(BaseMulStateActivity.this, text + "突然出现", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onViewHide(View view, int stateId) {
                        String text = "";
                        switch (stateId) {
                            case MulStateLayoutHelper.LAYOUT_CONTENT_ID:
                                text = "content";
                                break;
                            case MulStateLayoutHelper.LAYOUT_EMPTYDATA_ID:
                                text = "empty";
                                break;
                            case MulStateLayoutHelper.LAYOUT_ERROR_ID:
                                text = "error";
                                break;
                            case MulStateLayoutHelper.LAYOUT_LOADING_ID:
                                text = "loading";
                                break;
                            case MulStateLayoutHelper.LAYOUT_NETWORK_ERROR_ID:
                                text = "network error";
                                break;
                        }
                        Log.d("chang", text + "突然消失");
                        Toast.makeText(BaseMulStateActivity.this, text + "突然消失", Toast.LENGTH_SHORT).show();
                    }
                })
                .build();
        return mMulStateLayoutHelper;
    }

}
