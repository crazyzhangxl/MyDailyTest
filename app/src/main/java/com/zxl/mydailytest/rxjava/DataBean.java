package com.zxl.mydailytest.rxjava;

/**
 * Created by apple on 2020-03-05.
 * description:
 */
public class DataBean {
    private String name;
    private String age;

    public DataBean(String name, String age) {
        this.name = name;
        this.age = age;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
