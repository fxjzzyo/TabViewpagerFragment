package com.tab.viewpager.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.widget.PullRefreshLayout;
import com.tab.viewpager.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link ContentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContentFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private TextView textView;
    private ListView mListView;
    private ArrayAdapter<String> mAdapter;
    private List<String> datas = new ArrayList<>();

    private PullRefreshLayout layout;
    private View loadMoreView;

    public int last_index;
    public int total_index;
    public boolean isLoading = false;//表示是否正处于加载状态
    private Object initdatas;


    public ContentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContentFragment.
     */
    public static android.support.v4.app.Fragment newInstance(String param1, String param2) {
        ContentFragment fragment = new ContentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_content, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {

        View view = getView();
        mListView = view.findViewById(R.id.lv);
        layout = view.findViewById(R.id.swipeRefreshLayout);
        textView = view.findViewById(R.id.tv);

        loadMoreView = getActivity().getLayoutInflater().inflate(R.layout.load_more_layout, null);
        loadMoreView.setVisibility(View.VISIBLE);

    }


    private void initData() {

        textView.setText("This is fragment "+mParam1);

        //初始化datas
        getInitdatas(10);


        //设置适配器
        mAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, datas);
        mListView.setAdapter(mAdapter);

        //设置滑动监听，为了上拉加载
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (last_index == total_index && (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE)) {
                    //表示此时需要显示刷新视图界面进行新数据的加载(要等滑动停止)
                    if (!isLoading) {

                        //不处于加载状态的话对其进行加载
                        isLoading = true;

                        //设置刷新界面可见
                        loadMoreView.setVisibility(View.VISIBLE);

                        //获取更多数据
                        getMoreDatas(last_index,5);
                    }
                }

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                last_index = firstVisibleItem + visibleItemCount;
                total_index = totalItemCount;
                System.out.println("last:  " + last_index);
                System.out.println("total:  " + total_index);

            }
        });

        //添加上拉加载视图到listview的底部
        mListView.addFooterView(loadMoreView, null, false);


        // listen refresh event
        layout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // start refresh
                layout.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        //刷新，先清除数据
                        datas.clear();

                        //再重新请求数据，通常是请求网络，获取最新数据
                        getInitdatas(10);

                        //刷新适配器
                        mAdapter.notifyDataSetChanged();

                        //刷新完成
                        Toast.makeText(getActivity(),"刷新成功！",Toast.LENGTH_SHORT).show();
                        layout.setRefreshing(false);
                    }
                }, 2000);

            }
        });


    }

    /**
     * 上拉加载更多数据
     * @param start 开始索引
     * @param count 加载的个数
     */
    public void getMoreDatas(final int start, final int count) {

        //模拟耗时操作，2秒钟后执行
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = start; i < start+count; i++) {
                    datas.add("this is item " + i);
                }
                //刷新适配器
                mAdapter.notifyDataSetChanged();
                //加载完成
                loadComplete();
            }
        }, 2000);


    }

    /**
     * 初始化datas（这里通常是网络请求获取初始数据）
     */
    public void getInitdatas(int count) {
        for (int i = 1; i < count+1; i++) {
            datas.add("this is item " + i);
        }

    }
    /**
     * 加载完成
     */
    public void loadComplete()
    {
        loadMoreView.setVisibility(View.GONE);//设置刷新界面不可见
        isLoading = false;//设置正在刷新标志位false
//        mListView.removeFooterView(loadMoreView);//如果是最后一页的话，则将其从ListView中移出
    }
}
