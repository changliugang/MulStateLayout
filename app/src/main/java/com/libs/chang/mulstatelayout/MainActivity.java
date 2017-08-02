package com.libs.chang.mulstatelayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button normalBtn = (Button) findViewById(R.id.main_normal_btn);
        Button retryBtn = (Button) findViewById(R.id.main_retry_btn);
        Button customBtn = (Button) findViewById(R.id.main_custom_view_btn);
        Button fragmentBtn = (Button) findViewById(R.id.main_fragment_btn);

        normalBtn.setOnClickListener(this);
        retryBtn.setOnClickListener(this);
        customBtn.setOnClickListener(this);
        fragmentBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_normal_btn:
                startActivity(new Intent(MainActivity.this, NormalActivity.class));
                break;
            case R.id.main_custom_view_btn:
                startActivity(new Intent(MainActivity.this, CustomViewActivity.class));
                break;
            case R.id.main_retry_btn:
                startActivity(new Intent(MainActivity.this, StateWithRetryActivity.class));
                break;
            case R.id.main_fragment_btn:
                startActivity(new Intent(MainActivity.this, PageWithFragmentActivity.class));
                break;
        }
    }
}
