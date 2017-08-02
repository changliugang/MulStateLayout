package com.libs.chang.mulstatelayout.fragemnt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.libs.chang.mulstatelayout.R;
import com.libs.chang.mulstatelayoutlib.MulStateLayoutHelper;
import com.libs.chang.mulstatelayoutlib.listener.OnRetryListener;

import static com.libs.chang.mulstatelayoutlib.MulStateLayoutHelper.LAYOUT_CONTENT_ID;
import static com.libs.chang.mulstatelayoutlib.MulStateLayoutHelper.LAYOUT_EMPTYDATA_ID;

/**
 * 这里是Activity中嵌入的Fragment
 * @author 2017/8/2 10:01 / changliugang
 */
public class ContentFragment extends BaseMulStateFragment {

    private MulStateLayoutHelper mMulStateLayoutHelper;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // 这里的fragment布局是根据LAYOUT_CONTENT_ID获取的
        View contentView = mMulStateLayoutHelper.getViewByStateId(LAYOUT_CONTENT_ID);
        Button switchBtn = (Button) contentView.findViewById(R.id.frag_content_switch_state_btn);
        switchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchState(LAYOUT_EMPTYDATA_ID);
            }
        });
    }

    @Override
    protected MulStateLayoutHelper initMulStateLayoutHelper() {
        mMulStateLayoutHelper = MulStateLayoutHelper.newBuilder(getActivity())
                .loadingLayoutResId(R.layout.loading_layout)
                .contentLayoutResId(R.layout.fragment_content)
                .emptyLayoutResId(R.layout.empty_layout)
                .errorLayoutResId(R.layout.error_layout)
                .netWorkErrorViewResId(R.layout.network_error_layout)
                .emptyDataRetryViewId(R.id.empty_retry_btn)
                .errorRetryViewId(R.id.error_retry_btn)
                .netWorkErrorRetryViewId(R.id.net_error_retry_btn)
                .onRetryListener(new OnRetryListener() {
                    @Override
                    public void onRetryClick(final int id) {
                    switchState(id);
                    }
                })
                .build();
        return mMulStateLayoutHelper;
    }

    /**
     * 这里根据状态值和retry Button Id切换布局,以模拟状态变换
     * @param id 状态值和retry Button Id
     */
    private void switchState(final int id) {
        mMulStateLayoutHelper.showLoading();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        switch (id) {
                            case LAYOUT_EMPTYDATA_ID:
                                mMulStateLayoutHelper.showEmpty();
                                break;
                            case R.id.empty_retry_btn:
                                mMulStateLayoutHelper.showError();
                                break;
                            case R.id.error_retry_btn:
                                mMulStateLayoutHelper.showNetWorkError();
                                break;
                            case R.id.net_error_retry_btn:
                                mMulStateLayoutHelper.showContent();
                                break;
                        }
                        // 这里可以由getCurState函数获取当前的状态
                        Log.d("chang",""+mMulStateLayoutHelper.getCurState());
                    }
                });


            }
        }).start();
    }
}
