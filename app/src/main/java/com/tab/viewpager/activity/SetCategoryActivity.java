package com.tab.viewpager.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.tab.viewpager.Constants;
import com.tab.viewpager.R;
import com.tab.viewpager.adapter.CategoryAdapter;
import com.tab.viewpager.bean.Category;

import java.util.List;

public class SetCategoryActivity extends AppCompatActivity {

    private CategoryAdapter mCategoryAdapter;

    private RecyclerView mRecyclerView;
    private boolean isChanged;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_category);

         mRecyclerView = findViewById(R.id.rlv_category_list);

         mCategoryAdapter = new CategoryAdapter(this,Constants.categories);
        Log.d("tag","-----catesize---"+Constants.categories.size());
        mCategoryAdapter.setOnItemCheckListener(new CategoryAdapter.IOnItemCheckListener() {
            @Override
            public void onCheckedChanged(int position, boolean checked) {
                Constants.categories.get(position).setShow(checked);
                mCategoryAdapter.notifyDataSetChanged();
                isChanged = true;
            }
        });
         mRecyclerView.setAdapter(mCategoryAdapter);
         mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onBackPressed() {

        if(isChanged){
            setResult(RESULT_OK);
        }

        super.onBackPressed();
    }
}
