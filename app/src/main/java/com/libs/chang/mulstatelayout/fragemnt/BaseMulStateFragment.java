package com.libs.chang.mulstatelayout.fragemnt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.libs.chang.mulstatelayout.R;
import com.libs.chang.mulstatelayoutlib.MulStateLayoutHelper;
import com.libs.chang.mulstatelayoutlib.listener.OnStateViewShowListener;

/**
 * Fragment基类
 * @author 2017/8/2 10:20 / changliugang
 */
public class BaseMulStateFragment extends Fragment {
    // 供子类使用
    protected MulStateLayoutHelper mMulStateLayoutHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_base_mul_state, container, false);
        // 把多状态布局加入基类布局
        RelativeLayout rootLayout = (RelativeLayout) rootView.findViewById(R.id.fragment_base_mul_state_root_layout_rl);
        rootLayout.addView(initMulStateLayoutHelper().getCoreFrameLayout());
        return rootView;
    }

    /**
     * 这里进行MulStateLayoutHelper的创建、配置
     *
     * @return 指定的帮助类
     */
    protected MulStateLayoutHelper initMulStateLayoutHelper() {
        mMulStateLayoutHelper = MulStateLayoutHelper.newBuilder(getActivity())
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
                    }
                })
                .build();
        return mMulStateLayoutHelper;
    }
}
