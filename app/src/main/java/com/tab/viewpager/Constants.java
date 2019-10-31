package com.tab.viewpager;

import com.tab.viewpager.bean.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanlulin on 2019-07-18.
 */
public class Constants {

    public static List<String> titles = new ArrayList<>();
    public static List<Category> categories = new ArrayList<>();


    public static void init() {
        categories.add(new Category("首页"));
        categories.add(new Category("最新"));
        categories.add(new Category("热门"));
        categories.add(new Category("分类"));
        categories.add(new Category("推荐"));

    }

    {
        titles.add("首页");
        titles.add("最新");
        titles.add("热门");
        titles.add("分类");
        titles.add("推荐");
    }


}
