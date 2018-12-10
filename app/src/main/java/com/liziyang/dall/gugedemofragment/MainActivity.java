package com.liziyang.dall.gugedemofragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private MessageFragment messageFragment;
    private NewsFragment newsFragment;
    private SettingFragment settingFragment;
    private View messageLayout;
    private View newsLayout;
    private View settingLayout;
    private ImageView message_image;
    private ImageView news_image;
    private ImageView setting_image;
    private TextView message_text;
    private TextView news_text;
    private TextView setting_text;
    private FragmentManager fragmentManager;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        this.bundle=savedInstanceState;
//        设置无toolbar
        requestWindowFeature ( Window.FEATURE_NO_TITLE );
        setContentView ( R.layout.activity_main );
//        if(savedInstanceState!=null){
////            messageFragment= (MessageFragment) getSupportFragmentManager ().findFragmentByTag ( MessageFragment.class.getName () );
////            settingFragment= (SettingFragment) getSupportFragmentManager ().findFragmentByTag ( SettingFragment.class.getName () );
////            newsFragment= (NewsFragment) getSupportFragmentManager ().findFragmentByTag ( NewsFragment.class.getName () );
////            getSupportFragmentManager ().beginTransaction ().show ( messageFragment ).hide ( settingFragment ).hide ( newsFragment ).commit ();
//        }else {
            initViews ();
            fragmentManager = getSupportFragmentManager ();
            //设置选中
            setTabSelection(0);

//        }
//        initViews ();
//        fragmentManager = getSupportFragmentManager ();
//        //设置选中
//        setTabSelection(0);
    }


    private void initViews() {
        messageLayout = findViewById ( R.id.message_layout );
        newsLayout = findViewById ( R.id.news_layout );
        settingLayout = findViewById ( R.id.setting_layout );
        message_image = findViewById ( R.id.image_message );
        news_image = findViewById ( R.id.image_news );
        setting_image = findViewById ( R.id.image_setting );
        message_text = findViewById ( R.id.message_text );
        news_text = findViewById ( R.id.news_text );
        setting_text = findViewById ( R.id.setting_text );
        messageLayout.setOnClickListener ( this );
        newsLayout.setOnClickListener ( this );
        settingLayout.setOnClickListener ( this );
    }

    /**
     * 你可以根据需要选择恢复的tab项 这里我选择恢复第一项
     */
    @Override
    protected void onResume() {
        super.onResume ();
        if (bundle!=null){
            setTabSelection ( 0 );
        }

    }

    /**
     * Called when a view has been clicked.
     * 重写扩展的方法
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId ()) {
            case R.id.message_layout:
                setTabSelection(0);
                break;
            case R.id.news_layout:
                setTabSelection(1);
                break;
            case R.id.setting_layout:
                setTabSelection(2);
                break;
            default:
                break;

        }

    }

    /**
     * Called when pointer capture is enabled or disabled for the current window.
     *
     * @param hasCapture True if the window has pointer capture.
     */
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
    private void setTabSelection(int index) {
        //清除选中
        clearSelection();
        FragmentTransaction transaction=fragmentManager.beginTransaction ();
        //隐藏碎片
        hideFragment(transaction);
        switch (index){
            case 0:
                message_image.setImageResource ( R.drawable.ic_launcher_background );
                message_text.setTextColor ( Color.parseColor ( "#82858b" ) );
                //判断碎片是否为空 以免重复建立 影响性能
                if (messageFragment==null){
                    messageFragment=new MessageFragment ();
                    transaction.add ( R.id.content,messageFragment );
                }else {
                    transaction.show ( messageFragment );
                }
                break;
            case 1:
                news_image.setImageResource ( R.drawable.ic_launcher_background );
                news_text.setTextColor ( Color.parseColor ( "#82858b" ) );


                //判断碎片是否为空 以免重复建立 影响性能
                if (newsFragment==null){
                    newsFragment=new NewsFragment ();
                    transaction.add ( R.id.content,newsFragment );
                }else {
                    transaction.show ( newsFragment );
                }
                break;
            case 2:
                setting_image.setImageResource ( R.drawable.ic_launcher_background );
                setting_text.setTextColor ( Color.parseColor ( "#82858b" ) );
                //判断碎片是否为空 以免重复建立 影响性能
                if (settingFragment==null){
                    settingFragment=new SettingFragment ();
                    transaction.add ( R.id.content,settingFragment );
                }else {
                    transaction.show ( settingFragment );
                }
                break;
        }
        transaction.commit ();

    }

    private void clearSelection() {
        //设置清除后的图片文字修改
        message_image.setImageResource ( R.drawable.ic_launcher_background );
        message_text.setTextColor ( Color.parseColor ( "#82858b" ) );
        news_image.setImageResource ( R.drawable.ic_launcher_background );
        news_text.setTextColor ( Color.parseColor ( "#82858b" ) );
        setting_image.setImageResource ( R.drawable.ic_launcher_background );
        setting_text.setTextColor ( Color.parseColor ( "#82858b" ) );

    }
    private void hideFragment(FragmentTransaction transaction) {
        //隐藏碎片 避免重叠
        if (messageFragment!=null){
            transaction.hide ( messageFragment );
        }if (newsFragment!=null){
            transaction.hide ( newsFragment );
        }if (settingFragment!=null){
            transaction.hide ( settingFragment );
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (outState!=null){
            FragmentTransaction transaction=fragmentManager.beginTransaction ();
            if (messageFragment!=null){
                transaction.hide ( messageFragment );
            }if (newsFragment!=null){
                transaction.hide ( newsFragment );
            }if (settingFragment!=null){
                transaction.hide ( settingFragment );
            }
            transaction.commit ();

//            setTabSelection(0);
        }

        super.onSaveInstanceState ( outState );
    }
//
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        FragmentTransaction transaction=fragmentManager.beginTransaction ();
//        hideFragment(transaction);
//        transaction.commit ();
//        setTabSelection(0);
        FragmentTransaction transaction=fragmentManager.beginTransaction ();
        if (savedInstanceState!=null){
            //隐藏碎片 避免重叠

            if (messageFragment!=null){
                transaction.hide ( messageFragment );

                }if (newsFragment!=null){
                transaction.hide ( newsFragment );

            }if (settingFragment!=null) {
                transaction.hide ( settingFragment );
            }
//            as ( 0 );

            transaction.commit ();



        }



        super.onRestoreInstanceState ( savedInstanceState );
    }
}
