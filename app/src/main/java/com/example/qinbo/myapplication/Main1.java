package com.example.qinbo.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by QinBo on 2017/4/9.
 */

public class Main1 extends AppCompatActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置窗体全屏
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.zhu,null);

        setContentView(view);

        AlphaAnimation alphaanimation = new AlphaAnimation(0,1);
        alphaanimation.setDuration(4000);
        alphaanimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

                File  luji = new  File(getSDPath());
                if (!luji.exists())
                {
                    copyFilesFassets(Main1.this,"ce.db",getSDPath());
                    System.out.println("测试执行");
                }
                else {

                    System.out.println("测试执行");
                }

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                zhuye();
            }


            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.setAnimation(alphaanimation);

    }
    private void zhuye() {

        startActivity(new Intent(Main1.this,MainActivity.class));
        finish();

    }

    public void copyFilesFassets(Context context, String oldPath, String newPath) {

        try {
            String fileNames[] = Main1.this.getAssets().list(oldPath);
            //获取assets目录下的所有文件及目录名
            if (fileNames.length > 0) {
                //如果是目录
                File file = new File(newPath);
                file.mkdirs();
                //如果文件夹不存在，则递归
                for (String fileName : fileNames) {
                    copyFilesFassets(Main1.this, oldPath + "/" + fileName, newPath + "/" + fileName);
                }
            } else {
                //如果是文件
                InputStream is = Main1.this.getAssets().open(oldPath);

                FileOutputStream fos = new FileOutputStream(new File(newPath));
                byte[] buffer = new byte[1024];
                int byteCount = 0;
                while ((byteCount = is.read(buffer)) != -1) {
                    //循环从输入流读取 buffer字节
                    fos.write(buffer, 0, byteCount);//将读取的输入流写入到输出流
                }
                fos.flush();//刷新缓冲区
                is.close();
                fos.close();
                //zhuye();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            //如果捕捉到错误则通知UI线程
            System.out.println("错误" + e);
        }
    }
    public String getSDPath(){

        File file = new File("/data/data/"+Main1.this.getPackageName()+"/databases","ce.db");

               if (file.exists())
               {

                   file.mkdir();
               }



                   return file.toString();
    }
}
