package com.example.qinbo.myapplication.Fin;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qinbo.myapplication.HTTP.jsonjx;
import com.example.qinbo.myapplication.R;

import static com.example.qinbo.myapplication.HTTP.jsonjx.nu;
import static com.example.qinbo.myapplication.R.id.sw;

/**
 * Created by QinBo on 2017/4/16.
 */

public class Fin2 extends Fragment{
    @Nullable

    jsonjx jx = null;
   public static SwipeRefreshLayout swipeRefreshLayout;

    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);


        if (jx == null)
        {
              jx = new jsonjx(getActivity(),"成都");
        }

        View  view = View.inflate(getActivity(), R.layout.tianqijm,null);
                  swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(sw);
        RecyclerView rlist = (RecyclerView) view.findViewById(R.id.li_tq);


        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            //setOnRefreshListene
            @Override
            public void onRefresh() {

                //nu.clear();

                    jx.name();


          }
        });


        Recy recy = new Recy(getActivity(),nu);
        rlist.setAdapter(recy);
        LinearLayoutManager linerLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rlist.setLayoutManager(linerLayoutManager);

        return view;
    }
}