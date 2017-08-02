package com.libs.chang.mulstatelayout;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.libs.chang.mulstatelayout.fragemnt.ContentFragment;

/**
 * 这里展示页面是自定义TopBar+Fragment的写法
 */
public class PageWithFragmentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_with_fragment);

        FragmentManager fm = getSupportFragmentManager();
        ContentFragment contentFragment = (ContentFragment) fm.findFragmentById(R.id.id_fragment_container);

        if(contentFragment == null )
        {
            contentFragment = new ContentFragment();
            fm.beginTransaction().add(R.id.id_fragment_container, contentFragment).commit();
        }

    }
}
