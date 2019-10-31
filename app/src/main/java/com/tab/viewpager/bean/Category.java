package com.tab.viewpager.bean;

/**
 * Created by fanlulin on 2019-07-18.
 */
public class Category {
    private String title;
    private boolean isShow = true;

    public Category(){}

    public Category(String title) {
        this(title,true);
    }
    public Category(String title, boolean isShow) {
        this.title = title;
        this.isShow = isShow;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }
}
