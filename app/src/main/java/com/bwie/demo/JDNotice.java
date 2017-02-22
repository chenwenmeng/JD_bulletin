package com.bwie.demo;

import android.content.Context;


import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;


import android.view.Gravity;

import android.view.View;

import android.view.ViewGroup;
import android.view.animation.Animation;

import android.view.animation.AnimationUtils;

import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.List;

/**
 * 类描述:自定义京东快报无限轮播
 * 作者：陈文梦
 * 时间:2017/1/23 09:10
 * 邮箱:18310832074@163.com
 */

public
class
JDNotice extends ViewFlipper implements View.OnClickListener {

    private Context mContext;
    private List<String> mJDList;
    private OnNoticeClickLister mOnNoticeClickLister;

    public JDNotice(Context context) {
        super(context);
    }

    public JDNotice(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {

        mContext = context;
        //设置轮播时间
        setFlipInterval(3000);
        //设置边距
        setPadding(dp2px(5f), dp2px(5f), dp2px(5f), dp2px(5f));
        //设置进入退出动画
        setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.notiy_in));
        setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.notiy_out));

    }

    //添加需要展示的内容
    public void addNotice(List<String> notice) {
        mJDList = notice;
        removeAllViews();
        //遍历集合内容
        for (int i = 0; i < mJDList.size(); i++) {

            //根据每个内容创建textview
            String mNotice = mJDList.get(i);
            TextView textView = new TextView(mContext);
            //设置属性
            //单行
            textView.setSingleLine();
            //添加内容
            textView.setText(mNotice);
            //设置字体大小
            textView.setTextSize(13f);
            //设置省略位置
            textView.setEllipsize(TextUtils.TruncateAt.END);
            //字体颜色
            textView.setTextColor(Color.parseColor("#666666"));
            //设置位置
            textView.setGravity(Gravity.CENTER_VERTICAL);
            //设置tag值
            textView.setTag(i);
            //点击事件
            textView.setOnClickListener(this);
            //添加到ViewFlipper
            JDNotice.this.addView(textView, new ViewGroup.LayoutParams
                    (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
    }

    //转化字体大小
    private int dp2px(float dpValue) {

        return (int) TypedValue.applyDimension
                (TypedValue.COMPLEX_UNIT_DIP, dpValue, mContext.getResources().getDisplayMetrics());
    }


    @Override
    public void onClick(View v) {

        //获取标记
        int tag = (int) v.getTag();
        //获取内容
        String notice=mJDList.get(tag);
        //判断
        if(mOnNoticeClickLister!=null){

            mOnNoticeClickLister.onNoticeClick(tag,notice);
        }
    }
    //通知监听接口
    public interface OnNoticeClickLister{

        void onNoticeClick(int position,String notice);
    }

    public void setOnNoticeClickLister(OnNoticeClickLister onNoticeClickLister){

        mOnNoticeClickLister=onNoticeClickLister;
    }
}
