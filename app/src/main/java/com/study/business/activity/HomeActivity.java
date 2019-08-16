package com.study.business.activity;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.study.business.R;
import com.study.business.base.BaseActivity;
import com.study.business.base.ViewInject;
import com.study.business.fragment.HomeFragmet;
import com.study.business.fragment.MessageFragment;
import com.study.business.fragment.MineFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @function 创建所有的fragment  隐藏 或显示
 */
@ViewInject(mainLayoutId = R.layout.activity_home)
public class HomeActivity extends BaseActivity {
    @BindView(R.id.rl_home_content)
    RelativeLayout rlHomeContent;
    @BindView(R.id.tv_home_image)
    TextView tvHomeImage;
    @BindView(R.id.rl_home_home)
    RelativeLayout rlHomeHome;
    @BindView(R.id.tv_message_image)
    TextView tvMessageImage;
    @BindView(R.id.rl_home_message)
    RelativeLayout rlHomeMessage;
    @BindView(R.id.tv_mine_image)
    TextView tvMineImage;
    @BindView(R.id.rl_home_mine)
    RelativeLayout rlHomeMine;

    FragmentManager fm;
    Fragment mCurrent;
    private HomeFragmet mHomeFragment;
    private MessageFragment mMessageFragment;
    private MineFragment mMineFragment;

    @Override
    public void afterBindView() {
        tvHomeImage.setBackgroundResource(R.mipmap.comui_tab_home_selected);
        //添加默认要显示的fragment
        mHomeFragment = new HomeFragmet();
        fm=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fm.beginTransaction();
        //replace :  先 remove 在add  始终保持栈中只有一个fragment 每次都会新建fragment实例
        // hide  :   只是把fragment 隐藏  最常用的
        fragmentTransaction.replace(R.id.rl_home_content,mHomeFragment);
        fragmentTransaction.commit();
    }

    /**
     * 用来隐藏具体的fragment
     * @param fragment
     * @param ft
     */
    private void hideFragment(Fragment fragment,FragmentTransaction ft){
        if(fragment!=null){
            ft.hide(fragment);
        }

    }

    @OnClick({R.id.rl_home_home, R.id.rl_home_message, R.id.rl_home_mine})
    public void onViewClicked(View view) {
        FragmentTransaction fragmentTransaction=fm.beginTransaction();
        switch (view.getId()) {
            case R.id.rl_home_home:
                tvHomeImage.setBackgroundResource(R.mipmap.comui_tab_home_selected);
                tvMessageImage.setBackgroundResource(R.mipmap.comui_tab_message);
                tvMineImage.setBackgroundResource(R.mipmap.comui_tab_mine);
                //隐藏其它两个fragment
                hideFragment(mMessageFragment,fragmentTransaction);
                hideFragment(mMineFragment,fragmentTransaction);
                //将我们的homefragment  显示出来
                if(mHomeFragment==null){
                    mHomeFragment=new HomeFragmet();
                    fragmentTransaction.add(R.id.rl_home_content,mHomeFragment);
                }else{
                    //已经创建过了
                    fragmentTransaction.show(mHomeFragment);
                }
                break;
            case R.id.rl_home_message:
                tvHomeImage.setBackgroundResource(R.mipmap.comui_tab_home);
                tvMessageImage.setBackgroundResource(R.mipmap.comui_tab_message_selected);
                tvMineImage.setBackgroundResource(R.mipmap.comui_tab_mine);
                //隐藏其它两个Fragment
                hideFragment(mHomeFragment,fragmentTransaction);
                hideFragment(mMineFragment,fragmentTransaction);
                if(mMessageFragment==null){
                    mMessageFragment=new MessageFragment();
                    fragmentTransaction.add(R.id.rl_home_content,mMessageFragment);
                }else{
                    //已经创建了
                    fragmentTransaction.show(mMessageFragment);
                }
                break;
            case R.id.rl_home_mine:
                tvHomeImage.setBackgroundResource(R.mipmap.comui_tab_home);
                tvMessageImage.setBackgroundResource(R.mipmap.comui_tab_message);
                tvMineImage.setBackgroundResource(R.mipmap.comui_tab_mine_selected);
                //隐藏其它两个Framgent
                hideFragment(mHomeFragment,fragmentTransaction);
                hideFragment(mMessageFragment,fragmentTransaction);
                if(mMineFragment==null){
                    mMineFragment=new MineFragment();
                    fragmentTransaction.add(R.id.rl_home_content,mMineFragment);
                }else{
                    //已经创建了
                    fragmentTransaction.show(mMineFragment);
                }
                break;
        }
        fragmentTransaction.commit();
    }
}
