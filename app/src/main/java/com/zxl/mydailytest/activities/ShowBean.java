package com.zxl.mydailytest.activities;

/**
 * @author crazyZhangxl on 2018/10/24.
 * Describe:
 */
public class ShowBean {

    private String str;

    public String getStr_suffix() {
        return str_suffix;
    }

    public void setStr_suffix(String str_suffix) {
        this.str_suffix = str_suffix;
    }

    public boolean isShow_suffix() {
        return isShow_suffix;
    }

    public void setShow_suffix(boolean show_suffix) {
        isShow_suffix = show_suffix;
    }

    private String str_suffix;
    private boolean isShow;
    private boolean isShow_suffix;

    public ShowBean(String str, String str_suffix, boolean isShow, boolean isShow_suffix) {
        this.str = str;
        this.str_suffix = str_suffix;
        this.isShow = isShow;
        this.isShow_suffix = isShow_suffix;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public ShowBean(String str, boolean isShow) {
        this.str = str;
        this.isShow = isShow;
    }



}
