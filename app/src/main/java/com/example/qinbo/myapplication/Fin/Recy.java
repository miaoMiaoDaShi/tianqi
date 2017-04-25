package com.example.qinbo.myapplication.Fin;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.qinbo.myapplication.R;
import com.example.qinbo.myapplication.javaBeme.jaBeme;

import java.util.List;

/**
 * Created by QinBo on 2017/4/16.
 */

class Recy extends RecyclerView.Adapter<Recy.Testview>{
    private  Context context;
    private LayoutInflater myinflater;
    private List<jaBeme> list;

public  Recy(Context context,List<jaBeme> list){

    this.context = context;
    this.list = list;
     myinflater = LayoutInflater.from(context);

    System.out.println("C测试"+list.size());
}


    @Override
    public Testview onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = myinflater.inflate(R.layout.rlistview, parent,false);
           Testview testview = new Testview(view);

        return testview;
    }

    @Override
    public void onBindViewHolder(Testview holder, int position) {

                   holder.rq.setText(list.get(position).getDate());
                    holder.te1.setText(list.get(position).getHigh());
                  holder.zt.setText(list.get(position).getFengli());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class Testview extends RecyclerView.ViewHolder{

        TextView te1,rq,zt;


        public Testview(View itemView) {
            super(itemView);

              te1 = (TextView) itemView.findViewById(R.id.te_wed);
            rq = (TextView) itemView.findViewById(R.id.te_rq);
            zt = (TextView) itemView.findViewById(R.id.te_zt);

        }
    }
}
