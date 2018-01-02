package com.searchview;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xiong on 2018/1/2.
 */

public class SecondActivity extends AppCompatActivity {

    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new2_view);
        ButterKnife.bind(this);
        String SearchContent = getIntent().getStringExtra(SearchManager.QUERY);
        tv.setText(SearchContent);

    }

    public void bt1(View view){
        Intent intent=new Intent(SecondActivity.this,MainActivity.class);
        startActivity(intent);

    }


}
