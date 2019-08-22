package com.study.business.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.study.projectsdk.imageloader.ImageLoaderManager;

import java.util.ArrayList;

import androidx.viewpager.widget.PagerAdapter;


/**
 * Created by renzhiqiang on 16/8/31.
 */
public class PhotoPagerAdapter extends PagerAdapter {

    private Context mContext;

    private boolean mIsMatch;
    private ArrayList<String> mData;
    private ImageLoaderManager mLoader;

    public PhotoPagerAdapter(Context context, ArrayList<String> list, boolean isMatch) {
        mContext = context;
        mData = list;
        mIsMatch = isMatch;
        mLoader = ImageLoaderManager.getInstance(mContext);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public View instantiateItem(ViewGroup container, int position) {
        ImageView photoView=null;
        if (mIsMatch) {
            photoView = new ImageView(mContext);
            photoView.setScaleType(ScaleType.FIT_XY);
            photoView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent = new Intent(mContext,
//                            CourseDetailActivity.class);
//                    mContext.startActivity(intent);
                }
            });
        } else {
            photoView = new ImageView(mContext);
        }
        mLoader.displayImage(photoView, mData.get(position));
        container.addView(photoView, LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        return photoView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
