package com.bwie.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.widget.Toast;


import java.util.ArrayList;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {

        JDNotice jdNotice= (JDNotice) findViewById(R.id.JDNotice);
        List<String> notices=new ArrayList<>();
        notices.add("大促销下单拆福袋，亿万新年红包随便拿");
        notices.add("家电五折团，抢十亿无门槛现金红包");
        notices.add("星球大战剃须刀首发送200元代金劵");
        jdNotice.addNotice(notices);
        jdNotice.startFlipping();
        jdNotice.setOnNoticeClickLister(new JDNotice.OnNoticeClickLister() {


            @Override
            public void onNoticeClick(int position, String notice) {

                Toast.makeText(MainActivity.this,notice,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
