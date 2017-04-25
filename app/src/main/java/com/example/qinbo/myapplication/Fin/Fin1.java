package com.example.qinbo.myapplication.Fin;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.qinbo.myapplication.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.qinbo.myapplication.Fin.liview.date;
import static com.example.qinbo.myapplication.MainActivity.listsql;

/**
 * Created by QinBo on 2017/4/13.
 */

public class Fin1 extends Fragment{
    private List<Map<String,Object>> datae;

     private  ListView li;
   private SearchView searchView;
private SimpleAdapter  simpleAdapter;
    private EditText editText;
    liview iview;
    SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, final Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);

        View view = View.inflate(getActivity(),R.layout.chenshi,null);
        li = (ListView) view.findViewById(R.id.li_view);
           searchView = (SearchView) view.findViewById(R.id.searview);
              //swipeRefreshLayout  = (SwipeRefreshLayout) view.findViewById(R.id.sw);
        //editText = (EditText) view.findViewById(R.id.ed_text);


//        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_dark);
//    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//        @Override
//        public void onRefresh() {
//
//
//              jsonjx js = new jsonjx(getActivity()).name();
//
//
//
//
//            swipeRefreshLayout.setRefreshing(false);
//        }
//    });


        //去掉分割线
        li.setDivider(null);
        li.setTextFilterEnabled(true);
        iview = new liview(getActivity(),listsql);

         li.setAdapter(iview);




li.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    //getDatae().get(position).get("name");

        //simpleAdapter.setViewText();
           Toast.makeText(getActivity(),date.get(position).getName(),Toast.LENGTH_LONG).show();
    }
});


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                Toast.makeText(getActivity(),query.toString(),Toast.LENGTH_LONG).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText))
                {
                    li.clearTextFilter();

                }
                else {

                  //li.setFilterText(newText);

                  iview.getFilter().filter(newText);
                }


                return true;
            }
        });

        return view;
    }
    public List<Map<String,Object>> getDatae(){


                for (int i = 0;i < listsql.size();i++)
                {
                    Map<String,Object> map = new HashMap<String, Object>();
                    map.put("name",listsql.get(i).getName());
                    map.put("pinyin",listsql.get(i).getPinyin());
                    datae.add(map);
                }

        return datae;
    }


}
