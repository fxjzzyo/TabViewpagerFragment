package com.tab.viewpager.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.tab.viewpager.Constants;
import com.tab.viewpager.R;
import com.tab.viewpager.adapter.MyViewpaerAdapter;
import com.tab.viewpager.bean.Category;
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
    ;

    //fragments
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Constants.init();
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

        titles.clear();
        fragments.clear();
        for (int i = 0; i < Constants.categories.size(); i++) {
            Category category = Constants.categories.get(i);
            if (category.isShow()) {
                titles.add(category.getTitle());
                fragments.add(ContentFragment.newInstance(category.getTitle(), "" + i));
            }
        }
        Log.d("tag","------fragmentsize--------"+fragments.size());
        Log.d("tag","------constantsize--------"+Constants.categories.size());
        Log.d("tag","------titlesize--------"+titles.size());

    }


    private void initEvents() {

//        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.menu_search:
//                        //TODO
//                        Toast.makeText(MainActivity.this, "搜索！", Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.menu_about:
//                        //TODO
//                        Toast.makeText(MainActivity.this, "关于！", Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.menu_set:
//                        Intent intent = new Intent(MainActivity.this, SetCategoryActivity.class);
//                        startActivityForResult(intent, 0);
//                        break;
//                }
//                return false;
//            }
//        });

        myViewpaerAdapter = new MyViewpaerAdapter(getSupportFragmentManager(), titles, fragments);

        mViewPager.setAdapter(myViewpaerAdapter);
        tabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_search:
                //TODO
                Toast.makeText(MainActivity.this, "搜索！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_about:
                //TODO
                Toast.makeText(MainActivity.this, "关于！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_set:
                Intent intent = new Intent(this, SetCategoryActivity.class);
                startActivityForResult(intent, 0);
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0 && resultCode == RESULT_OK) {
            refrushData();
            Log.d("tag", "-----------刷新");
        }
    }

    private void refrushData() {
        initDatas();
        myViewpaerAdapter.notifyDataSetChanged();
    }
}
