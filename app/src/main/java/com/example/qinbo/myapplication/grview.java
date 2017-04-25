package com.example.qinbo.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import static com.example.qinbo.myapplication.HTTP.jsonjx.nu;

/**
 * Created by QinBo on 2017/4/10.
 */

class grview extends BaseAdapter {

    Context context;

    public  grview (Context context)
    {
        this.context = context;

    }

    @Override
    public int getCount() {
        return nu.size();
    }


    @Override
    public Object getItem(int position) {
        return null;
    }


    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = View.inflate(context,R.layout.gibuju,null);

           TextView ext1 = (TextView) view.findViewById(R.id.te1);

              ext1.setText("晴天");


        return null;
    }
}
