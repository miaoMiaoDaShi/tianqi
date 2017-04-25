package com.example.qinbo.myapplication.HTTP;

import android.content.ContentValues;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.example.qinbo.myapplication.SQL.SQL;
import com.example.qinbo.myapplication.javaBeme.jaBeme;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.example.qinbo.myapplication.Fin.Fin2.swipeRefreshLayout;


/**
 * Created by QinBo on 2016/11/21.
 */

public  class jsonjx {
    SQL sql;
    public static List<jaBeme> nu = new ArrayList<jaBeme>();


    public Context mContext;
    public  String URLname;

    public jsonjx(Context mContext,String urlname) {

        this.mContext = mContext;
        this.URLname = urlname;
    }



    public Handler handler = new  Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch(msg.what){
                case 1:
                        //tishi("哼(ˉ(∞)ˉ)唧 网路不能用鸟");
                    tishi("IO流错误");
                            ce();
                break;
                case 2:

                    tishi("矮油！编码错误");
                    break;

                case 3:

                    tishi("URL地址错误");
                    break;

                case 4:

                    swipeRefreshLayout.setRefreshing(false);
                    System.out.println("执行了！");
                    break;
            }

        }
    };

    public static String mversionName,
            mdescription,
            downloadUrl,
            myshijian ;

    public static String mversionCode;
    public static int ces;


    public jsonjx name() {

        final Message msg = new Message();

        new Thread() {

            @Override
            public void run() {

                StringBuilder sb = new StringBuilder();
                try {
                    URL g = new URL("http://wthrcdn.etouch.cn/weather_mini?city="+URLname);
                    BufferedReader br = new BufferedReader(new InputStreamReader(g.openStream(), "utf-8"));


                  String li = null;

                    while ((li = br.readLine()) != null) {

                        sb.append(li);
                    }

                    System.out.println("主"+sb.toString());

                    br.close();


                } catch (MalformedURLException e) {
                    msg.what = 3;
                    handler.sendMessage(msg);

                } catch (UnsupportedEncodingException e) {

                    msg.what = 2;
                    handler.sendMessage(msg);

                } catch (IOException e) {


                    msg.what = 1;
                    handler.sendMessage(msg);

                    System.out.println("报错"+e);

                }
                JSONObject jo;
                JSONObject jn;
                ContentValues valuer;

                valuer = new ContentValues();
                try {

                    jo = new JSONObject(sb.toString());

                    System.out.println("下标"+jo.getString("desc"));

                        jn = new JSONObject(jo.getString("data"));


                               JSONArray jsn = jn.getJSONArray("forecast");

                    for (int i = 0; i < jsn.length(); i++) {
                        String s = jsn.get(i).toString();
                        jo = new JSONObject(s);
                        jaBeme  ja = new jaBeme();

                        ja.setFengxiang(jo.getString("fengxiang"));
                        ja.setDate(jo.getString("date"));
                        ja.setFengli(jo.getString("fengli"));
                        ja.setHigh(jo.getString("high"));
                        ja.setType(jo.getString("type"));
                        ja.setLow(jo.getString("low"));

                        nu.add(ja);

                        System.out.println("哈哈哈");
                    }


                } catch (JSONException e1) {
                    System.out.println("JSONObject解析错误");

                    System.out.println("解析报错"+e1);

                }
                msg.what = 4;
                handler.sendMessage(msg);

            }


        }.start();

        return null;
    }



    public void tishi(String leir){

        Toast.makeText(mContext,leir, Toast.LENGTH_LONG).show();

    }

    public void ce(){

            new Thread(){

                @Override
                public void run() {

                    try {
                        Thread.sleep(9000);
                        name();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();

        }
}
