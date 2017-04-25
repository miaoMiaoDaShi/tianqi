package com.example.qinbo.myapplication.Fin;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.qinbo.myapplication.R;
import com.example.qinbo.myapplication.javaBeme.SQLBeme;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by QinBo on 2017/4/15.
 */

class liview extends BaseAdapter implements Filterable{
    private Context context;
    Mfile mfile;
    List<SQLBeme> yuan;
    static List<SQLBeme> date;

    public  liview(Context context,List<SQLBeme> date)
    {
           this.context = context;
        this.date = date;
        yuan = date;


    }

    @Override
    public int getCount() {
        return date.size();
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

        View  view = null;
        TextView textView;

                if(convertView == null) {

                    view = View.inflate(context, R.layout.list, null);
                }
                else {
                      view  = convertView;
                }

            textView = (TextView) view.findViewById(R.id.lit_text);
            textView.setText(date.get(position).getName());


        return view;
    }


    @Override
    public Filter getFilter() {

        if (mfile == null)
        {
            mfile = new Mfile();

        }

        return mfile;
    }

    class Mfile extends Filter{


        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults filterResults = new FilterResults();
            List<SQLBeme> list;
            if (TextUtils.isEmpty(constraint))
            {
                             list = yuan;

            }
             else {
                list = new ArrayList<>();
                for (SQLBeme sqlBeme:yuan)
                {
                    Log.e("shuchu",sqlBeme.toString());

                    if (sqlBeme.getName().contains(constraint) || sqlBeme.getPinyin().contains(constraint)) {
                        list.add(sqlBeme);
                    }
                }

            }

            filterResults.values = list;
            filterResults.count = list.size();

            return filterResults;
        }


        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

              date = (List<SQLBeme>) results.values;

            if (results.count > 0)
            {
                notifyDataSetChanged();//通知数据发生了改变
               System.out.println("publishResults:notifyDataSetChanged");
            }
            else {

                notifyDataSetInvalidated();//通知数据失效
                System.out.println("publishResults:notifyDataSetInvalidated");
            }
        }
    }
}
