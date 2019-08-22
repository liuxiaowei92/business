package com.study.business.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.study.business.R;
import com.study.business.module.home.HomeBodyValue;
import com.study.business.utils.Util;
import com.study.projectsdk.imageloader.ImageLoaderManager;
import com.study.projectsdk.utils.Utils;

import java.util.ArrayList;

import androidx.viewpager.widget.ViewPager;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * created by lxw on 2019/8/21
 *
 * @function
 */
public class CourseAdapter extends BaseAdapter {
    /**
     * 不同类型的item标识
     */
    private static final int CARD_COUNT=4;
    private static final int VIDOE_TYPE=0x00;
    private static final int CARD_SINGLE_PIC=0x02;
    private static final int CARD_MULTI_PIC=0x01;
    private static final int CARD_VIEW_PAGER=0x03;

    private Context mContext;
    private ViewHolder mViewHolder;
    private LayoutInflater mInflater;
    private ArrayList<HomeBodyValue> mData;
    /**
     * 异步图片加载工具类
     */
    private ImageLoaderManager mImageLoader;

    /**
     * 构造方法
     * @param context
     * @param data
     */
    public CourseAdapter(Activity context, ArrayList<HomeBodyValue> data) {
        mContext=context;
        mData=data;
        this.mInflater=LayoutInflater.from(mContext);
        this.mImageLoader= ImageLoaderManager.getInstance(mContext);
    }


    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int positon) {
        return mData.get(positon);
    }

    @Override
    public long getItemId(int positon) {
        return positon;
    }

    @Override
    public int getViewTypeCount() {
        return CARD_COUNT;
    }

    /**
     * 通知adapter使用哪种类型的item去加载数据
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        HomeBodyValue value= (HomeBodyValue) getItem(position);
        return value.type;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //1,获取数据的type类型
        int type=getItemViewType(position);
        final HomeBodyValue value= (HomeBodyValue) getItem(position);
        //为空表明当前没有可使用的缓存view
        if(convertView==null){
            switch (type){
                case CARD_SINGLE_PIC:
                    mViewHolder=new ViewHolder();
                    convertView=mInflater
                            .inflate(R.layout.item_product_card_one_layout,parent,false);
                    mViewHolder.mLogoView = (CircleImageView) convertView.findViewById(R.id.item_logo_view);
                    mViewHolder.mTitleView = (TextView) convertView.findViewById(R.id.item_title_view);
                    mViewHolder.mInfoView = (TextView) convertView.findViewById(R.id.item_info_view);
                    mViewHolder.mFooterView = (TextView) convertView.findViewById(R.id.item_footer_view);
                    mViewHolder.mPriceView = (TextView) convertView.findViewById(R.id.item_price_view);
                    mViewHolder.mFromView = (TextView) convertView.findViewById(R.id.item_from_view);
                    mViewHolder.mZanView = (TextView) convertView.findViewById(R.id.item_zan_view);
                    mViewHolder.mProductView = (ImageView) convertView.findViewById(R.id.product_photo_view);
                    break;
                case CARD_MULTI_PIC:
                    mViewHolder = new ViewHolder();
                    convertView = mInflater.inflate(R.layout.item_product_card_two_layout, parent, false);
                    mViewHolder.mLogoView = (CircleImageView) convertView.findViewById(R.id.item_logo_view);
                    mViewHolder.mTitleView = (TextView) convertView.findViewById(R.id.item_title_view);
                    mViewHolder.mInfoView = (TextView) convertView.findViewById(R.id.item_info_view);
                    mViewHolder.mFooterView = (TextView) convertView.findViewById(R.id.item_footer_view);
                    mViewHolder.mPriceView = (TextView) convertView.findViewById(R.id.item_price_view);
                    mViewHolder.mFromView = (TextView) convertView.findViewById(R.id.item_from_view);
                    mViewHolder.mZanView = (TextView) convertView.findViewById(R.id.item_zan_view);
                    mViewHolder.mProductLayout = (LinearLayout) convertView.findViewById(R.id.product_photo_layout);
                    break;
                case CARD_VIEW_PAGER:
                    mViewHolder = new ViewHolder();
                    convertView=mInflater.inflate(R.layout.item_product_card_three_layout,parent,false);
                    mViewHolder.mViewPager=(ViewPager)convertView.
                            findViewById(R.id.pager);
                    mViewHolder.mViewPager.setPageMargin(Utils.dip2px(mContext,12));
                    //为我们的viewPage填充数据
                    ArrayList<HomeBodyValue> list= Util.handleData(value);
                    mViewHolder.mViewPager.setAdapter(new HotSalePageAdapter(mContext,list));
                    //一开始就让viewpager处于一个比较靠中间的item项
                    mViewHolder.mViewPager.setCurrentItem(list.size()*100);
                    break;
            }

            convertView.setTag(mViewHolder);
        }else{//有可用的
            mViewHolder= (ViewHolder) convertView.getTag();
        }
        //开始绑定数据
        switch (type){
            case CARD_SINGLE_PIC:
                mViewHolder.mTitleView.setText(value.title);
                mViewHolder.mInfoView.setText(value.info.concat(mContext.getString(R.string.tian_qian)));
                mViewHolder.mFooterView.setText(value.text);
                mViewHolder.mPriceView.setText(value.price);
                mViewHolder.mFromView.setText(value.from);
                mViewHolder.mZanView.setText(mContext.getString(R.string.dian_zan).concat(value.zan));
                /**
                 * 为imageVIew完成图片的加载
                 */
                mImageLoader.displayImage(mViewHolder.mLogoView,value.logo);
                mImageLoader.displayImage(mViewHolder.mProductView,value.url.get(0));
                break;
            case CARD_MULTI_PIC:
                mImageLoader.displayImage(mViewHolder.mLogoView, value.logo);
                mViewHolder.mTitleView.setText(value.title);
                mViewHolder.mInfoView.setText(value.info.concat(mContext.getString(R.string.tian_qian)));
                mViewHolder.mFooterView.setText(value.text);
                mViewHolder.mPriceView.setText(value.price);
                mViewHolder.mFromView.setText(value.from);
                mViewHolder.mZanView.setText(mContext.getString(R.string.dian_zan).concat(value.zan));
                //删除已有的view
                mViewHolder.mProductLayout.removeAllViews();
                //动态添加多个imagevie 到我们的水平scrollview中
                for (String url : value.url) {
                    mViewHolder.mProductLayout.addView(createImageView(url));
                }
                break;

        }

        return convertView;
    }

    /**
     * 动态穿件我们的ImageView
     * @param url
     * @return
     */
    private ImageView createImageView(String url) {
        ImageView imageView=new ImageView(mContext);
        //要与添加到的viewGroup要保持一致  设置参数
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(
                Utils.dip2px(mContext,100),LinearLayout.LayoutParams.MATCH_PARENT);
        params.leftMargin=Utils.dip2px(mContext,5);
        imageView.setLayoutParams(params);
        mImageLoader.displayImage(imageView,url);
        return imageView;
    }

    private static class ViewHolder{
            //所有Card共有属性
            private CircleImageView mLogoView;
            private TextView mTitleView;
            private TextView mInfoView;
            private TextView mFooterView;
            //Video Card特有属性
            private RelativeLayout mVieoContentLayout;
            private ImageView mShareView;

            //Video Card外所有Card具有属性
            private TextView mPriceView;
            private TextView mFromView;
            private TextView mZanView;
            //Card One特有属性
            private LinearLayout mProductLayout;
            //Card Two特有属性
            private ImageView mProductView;
            //Card Three特有属性
            private ViewPager mViewPager;
        }

}
