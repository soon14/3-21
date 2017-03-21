package com.qiansong.msparis.app.mine.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.activity.MainActivity;
import com.qiansong.msparis.app.commom.bean.BannerBean;
import com.qiansong.msparis.app.commom.util.AndroidUtil;
import com.qiansong.msparis.app.commom.util.ExclusiveUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 引导页 图片适配器
 *
 * @author jiawei.xue
 * @date 2015-12-24
 */
public class MyCardAdapter extends PagerAdapter {

    /**
     * banner数据列表
     */
    private List<String> images = new ArrayList<String>();
    /**
     * 图片view列表
     */
    private ArrayList<SimpleDraweeView> viewList = new ArrayList<SimpleDraweeView>();
    /**
     * 布局参数
     */
    private FrameLayout.LayoutParams layoutParams;

    private   SimpleDraweeView imageView;

    /**
     * 上下文
     */
    private Context context;

    public MyCardAdapter(Activity context) {
        this.context = context;
        int screenWidth = AndroidUtil.getDisplayMetrics(context).widthPixels;
        layoutParams = new FrameLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    /**
     * @param
     */
    public void setBannerList(List<String> images) {
        this.images = images;

        //t
        LayoutInflater inflater =  LayoutInflater.from(context);
        //第一个参数为xml文件中view的id，第二个参数为此view的父组件，可以为null，android会自动寻找它是否拥有父组件
        View view = inflater.inflate(R.layout.item_my_card, null);
        imageView = (SimpleDraweeView) view.findViewById(R.id.card_image);


        for (int i = 0; i < images.size(); i++) {
            imageView = new SimpleDraweeView(context);
            imageView.setScaleType(ScaleType.FIT_CENTER);
            imageView.setLayoutParams(layoutParams);
            viewList.add(imageView);
        }
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return images == null ? 0 : images.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewList.get(position));
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final int pos = position;
        container.addView(viewList.get(position));
        final SimpleDraweeView imageView = viewList.get(position);
        /** 设置会员的信息***/
        ExclusiveUtils.setImageUrl(imageView, images.get(pos), -1);
        imageView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (pos == 3) {
                    context.startActivity(new Intent(context, MainActivity.class));
                    ((Activity) context).finish();

                }
            }
        });

        return imageView;
    }

}
