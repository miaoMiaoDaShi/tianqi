package com.example.qinbo.myapplication.javaBeme;

/**
 * Created by QinBo on 2017/4/12.
 */

public class SQLBeme {



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    @Override
    public String toString() {
        return "SQLBeme{" +
                "name='" + name + '\'' +
                ", pinyin='" + pinyin + '\'' +
                '}';
    }

    public SQLBeme(String name, String pinyin) {
        this.name = name;
        this.pinyin = pinyin;
    }

    String name;
    String pinyin;


}
