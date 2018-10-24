package com.tab.viewpager.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.tab.viewpager.R;
import com.tab.viewpager.adapter.MyViewpaerAdapter;
import com.tab.viewpager.fragment.ContentFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TabLayout tabLayout;
    private ViewPager mViewPager;
    private MyViewpaerAdapter myViewpaerAdapter;

    //tab标题
    private List<String> titles = new ArrayList<>();

    //fragments
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initDatas();
        initEvents();
    }

    private void initViews() {


        mToolbar = findViewById(R.id.tb);
        tabLayout = findViewById(R.id.tl);
        mViewPager = findViewById(R.id.vp);
        mToolbar.setTitle("首页");
        setSupportActionBar(mToolbar);

    }

    private void initDatas() {



        titles.add("最新");
        titles.add("热门");
        titles.add("分类");
        titles.add("推荐");

        Fragment fragment1 = ContentFragment.newInstance("1","1");
        Fragment fragment2 = ContentFragment.newInstance("2","2");
        Fragment fragment3 = ContentFragment.newInstance("3","3");
        Fragment fragment4 = ContentFragment.newInstance("4","4");
        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);
        fragments.add(fragment4);



    }

    private void initEvents() {

        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_search:
                        //TODO
                        Toast.makeText(MainActivity.this,"搜索！",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_about:
                        //TODO
                        Toast.makeText(MainActivity.this,"关于！",Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });

        myViewpaerAdapter = new MyViewpaerAdapter(getSupportFragmentManager(), titles, fragments);

        mViewPager.setAdapter(myViewpaerAdapter);
        tabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }
}
