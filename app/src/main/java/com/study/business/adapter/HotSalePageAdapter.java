package com.study.business.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.study.business.R;
import com.study.business.module.home.HomeBodyValue;
import com.study.projectsdk.imageloader.ImageLoaderManager;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

/**
 * created by lxw on 2019/8/22
 *
 * @function
 */
class HotSalePageAdapter extends PagerAdapter {
    private Context mContext;
    private ArrayList<HomeBodyValue> mData;

    private LayoutInflater mInflater;
    private ImageLoaderManager mLoaderManager;

    public HotSalePageAdapter(Context context, ArrayList<HomeBodyValue> list) {
        this.mContext= context;
        this.mData=list;
        this.mInflater= LayoutInflater.from(mContext);
        this.mLoaderManager=ImageLoaderManager.getInstance(mContext);
    }

    @Override
    public int getCount() {
        //无限循环
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        final HomeBodyValue value = mData.get(position % mData.size());
        View rootView = mInflater.inflate(R.layout.item_hot_product_pager_layout, null);
        TextView titleView = (TextView) rootView.findViewById(R.id.title_view);
        TextView infoView = (TextView) rootView.findViewById(R.id.info_view);
        TextView gonggaoView = (TextView) rootView.findViewById(R.id.gonggao_view);
        TextView saleView = (TextView) rootView.findViewById(R.id.sale_num_view);
        ImageView[] imageViews = new ImageView[3];
        imageViews[0] = (ImageView) rootView.findViewById(R.id.image_one);
        imageViews[1] = (ImageView) rootView.findViewById(R.id.image_two);
        imageViews[2] = (ImageView) rootView.findViewById(R.id.image_three);
//        rootView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(mContext, CourseDetailActivity.class);
//                intent.putExtra(CourseDetailActivity.COURSE_ID, value.adid);
//                mContext.startActivity(intent);
//            }
//        });

        titleView.setText(value.title);
        infoView.setText(value.price);
        gonggaoView.setText(value.info);
        saleView.setText(value.text);
        for (int i = 0; i < imageViews.length; i++) {
            mLoaderManager.displayImage(imageViews[i], value.url.get(i));
        }
        container.addView(rootView, 0);
        return rootView;
    }
}
