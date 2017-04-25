package com.example.qinbo.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.qinbo.myapplication.Fin.Fin1;
import com.example.qinbo.myapplication.Fin.Fin2;
import com.example.qinbo.myapplication.SQL.SQL;
import com.example.qinbo.myapplication.javaBeme.SQLBeme;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Button button;
    ImageView imageView;
    GridView gridView;
    private Cursor s;
    public static List<SQLBeme> listsql = new ArrayList<SQLBeme>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //查询数据库
        ceshi();

        button =(Button) findViewById(R.id.butt);

        imageView = (ImageView) findViewById(R.id.imageView);
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                //System.out.println("测试");
//                //System.out.println("哈哈"+ceshi.get(1).getName());
//
//            }
//        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               drawer.openDrawer(Gravity.START);
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }
    public void ceshi(){

         new Thread(){
             @Override
             public void run() {
                 super.run();

    SQL sql = new SQL(MainActivity.this, "ce.db", null, 1);

    SQLiteDatabase db = sql.getWritableDatabase();

    s = db.query("person", new String[]{"area_name","city_name"}, null, null, null, null, null);

    while (s.moveToNext()) {
        SQLBeme sqlBeme = new SQLBeme(s.getString(0).toString(),s.getString(1).toString());

        listsql.add(sqlBeme);
    }


}}.start();

    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
////        if (id == R.id.action_settings) {
////            return true;
////        }
//
//        return super.onOptionsItemSelected(item);


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

            Fin1 fin1 = null;
            if (fin1 == null)
            {
                 fin1 = new Fin1();
            }

                   android.app.FragmentManager fm = getFragmentManager();
                 android.app.FragmentTransaction ft = fm.beginTransaction();
                         ft.replace(R.id.ce_fr,fin1);
                        ft.commit();



        } else if (id == R.id.nav_gallery) {

            Fin2 fin2 = null;
            if (fin2 == null)
            {
                fin2 = new Fin2();
            }

            android.app.FragmentManager fm = getFragmentManager();
            android.app.FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.ce_fr,fin2);
            ft.commit();

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
