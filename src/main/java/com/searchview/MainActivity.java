package com.searchview;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    //这个写法有个弊端就是要点击搜索放大镜图标才能搜索

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //这样能够直接隐藏toolbar的title
        toolbar.setTitle("");
        setSupportActionBar(toolbar);                        //用toolbar替换原来的ActionBar
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//这句代码使启用Activity回退功能，并显示Toolbar上的左侧回退图标

    }


    public void bt(View view){
        Intent intent=new Intent(MainActivity.this,SecondActivity.class);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        /***
         *
         * showAsAction 是做什么用的？
         * showAsAction 主要是针对菜单的显示起作用的，它有三个可选项
         * always：总是显示在界面上
         * never：不显示在界面上，只让出现在右边的三个点中
         * ifRoom：如果有位置才显示，不然就出现在右边的三个点中
         *
         *
         */

        // android：orderInCategory="100"
        // 设置优先级，值越大优先级越低
        // 只写下面这个是基础款的实现方式
        //================实现方式1=================
       // getMenuInflater().inflate(R.menu.menu_main, menu);//指定Toolbar上的视图文件

        //=================实现方式2==================
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.ab_search).getActionView();



        //下面这两行代码暂时注释，好像是和转跳有关的2018.1.2
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        //searchView.setIconifiedByDefault(false);

        //一开始就显示下划线，个人的理解是一开始就处于展开状态然后让searchview失去焦点
        //就是打开Activity就是展开的状态
        //searchView.setIconified(false);//一开始处于展开状态
        //这个并不是智障选项，而是配合失去焦点，实现电商类应用的toolbar上的搜索栏
       // searchView.onActionViewExpanded();// 当展开无输入内容的时候，没有关闭的图标
        //searchView.clearFocus();
        //还有一个视觉效果不知道要不要实现，就是当用过一次搜索之后，但是对搜索结果不满意，把搜索结果删掉之后，软键盘还要不要显示出来2018.1.2
        //可以参考一下别的app是怎么实现的
        //好像并不用把软键盘给去掉，第三方的软键盘都有隐藏按钮 2018.1.2
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();
        switch (itemId){
            //这个id是安卓系统的id
            case android.R.id.home:
                this.finish();//真正实现回退功能的代码

            case R.id.ab_search:
                onSearchRequested();
                return true;

            default:
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
