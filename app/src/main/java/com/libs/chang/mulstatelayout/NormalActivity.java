package com.libs.chang.mulstatelayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * 这里展示最简单的用法，具体实现在基类
 */
public class NormalActivity extends BaseMulStateActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_normal);
        // 注意这里，我们需要在 MulStateLayoutHelper的 contentLayoutResId 函数中设置正常内容布局（like line 29），
        // 这里设置setContentView(R.layout.activity_normal)会覆盖我们的多状态布局
        mMulStateLayoutHelper.showLoading();
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
