package com.liziyang.dall.gugedemofragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.FrameLayout;
import android.widget.TextView;

public class SettingFragment extends Fragment {
    private FrameLayout frameLayout;
    private TextFragment1 textFragment1;
    private TextFragment2 textFragment2;
    private TextFragment3 textFragment3;
    private FragmentManager fragmentManager;
    private TextView textView1, textView2, textView3;
    private View tvLayout1,tvLayout2,tvLayout3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate ( R.layout.setting_layout , container , false );
        textView1 = view.findViewById ( R.id.tv1 );
        textView2 = view.findViewById ( R.id.tv2 );
        textView3 = view.findViewById ( R.id.tv3 );
        tvLayout1=view.findViewById ( R.id.tvLayout1);
        tvLayout2=view.findViewById ( R.id.tvLayout2);
        tvLayout3=view.findViewById ( R.id.tvLayout3);
        frameLayout=view.findViewById ( R.id.scrollIndicatorDown );

//        setUI();
////        setTabSelection ( 0 );
//        setTabSelection ( 0 );
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated ( savedInstanceState );
        setUI ();
        setTabSelection ( 0 );
    }


    private void setTabSelection(int index) {
        //注意在父类fragment中写子类fragment需要使用特殊的碎片管理器 getChildFragmentManager
        fragmentManager=getChildFragmentManager ();
        //清除选中
        clearSelection();
        FragmentTransaction transaction=fragmentManager.beginTransaction ();
        //隐藏碎片
        hideFragment2(transaction);
        switch (index){
            case 0:
                textView1.setTextColor ( Color.parseColor ( "#00574B" ) );
                tvLayout1.setBackgroundColor ( getResources ().getColor ( R.color.colorAccent ) );
                //判断碎片是否为空 以免重复建立 影响性能
                if (textFragment1 ==null) {
                    textFragment1 = new TextFragment1 ();
                    transaction.add ( R.id.scrollIndicatorDown , textFragment1 );
                    Log.i ( "空" , "为空" );
                } else {
                    Log.i ( "空","不为空" );
                    transaction.show (textFragment1);

                }
                break;
            case 1:
                textView2.setTextColor ( Color.parseColor ( "#00574B" ) );
                tvLayout2.setBackgroundColor ( getResources ().getColor ( R.color.colorAccent ) );


                //判断碎片是否为空 以免重复建立 影响性能
                if (textFragment2 ==null){
                    textFragment2 =new TextFragment2 ();
                    transaction.add ( R.id.scrollIndicatorDown, textFragment2 );
                    Log.i ( "空","为空" );
                }else {
                    transaction.show ( textFragment2 );
                    Log.i ( "空","不为空" );
                }
                break;
            case 2:
                textView3.setTextColor ( Color.parseColor ( "#00574B" ) );
                tvLayout3.setBackgroundColor ( getResources ().getColor ( R.color.colorAccent ) );
                //判断碎片是否为空 以免重复建立 影响性能
                if (textFragment3==null){
                    textFragment3=new TextFragment3 ();
                    transaction.add ( R.id.scrollIndicatorDown,textFragment3 );
                    Log.i ( "空","为空" );
                }else {
                    transaction.show ( textFragment3 );
                    Log.i ( "空","不为空" );
                }
                break;
        }
        transaction.commit ();

    }

    public void hideFragment2(FragmentTransaction transaction) {
        //隐藏碎片 避免重叠
        if (textFragment1 !=null){
            transaction.hide ( textFragment1 );
        }if (textFragment2 !=null){
            transaction.hide ( textFragment2 );
        }if (textFragment3!=null){
            transaction.hide ( textFragment3 );
        }

    }

    private void clearSelection() {
        textView1.setTextColor ( Color.parseColor ( "#82858b" ) );
        textView2.setTextColor ( Color.parseColor ( "#82858b" ) );
        textView3.setTextColor ( Color.parseColor ( "#82858b" ) );
        tvLayout1.setBackgroundColor ( getResources ().getColor ( R.color.colorPrimary ) );
        tvLayout2.setBackgroundColor ( getResources ().getColor ( R.color.colorPrimary ) );
        tvLayout3.setBackgroundColor ( getResources ().getColor ( R.color.colorPrimary ) );


    }






    private void setUI() {
        tvLayout1.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                setTabSelection ( 0 );

            }
        } );
        tvLayout2.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                setTabSelection ( 1 );

            }
        } );
        tvLayout3.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                setTabSelection ( 2 );

            }
        } );

    }

}
