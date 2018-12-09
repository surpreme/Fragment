package com.liziyang.dall.gugedemofragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MessageFragment extends Fragment {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    //    private List<Fragment> fragmentList;
    private MyAdapterM myAdapterM;
    private View[] views;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate ( R.layout.message_layout , container , false );
        viewPager = view.findViewById ( R.id.viewPager );
        tabLayout = view.findViewById ( R.id.tabMode );
        setUI ();
        return view;
    }

    private void setUI() {
        views = new View[ 3 ];
        LayoutInflater layoutInflater = LayoutInflater.from ( getActivity () );
        views[ 0 ] = layoutInflater.inflate ( R.layout.wang1 , null );
        views[ 1 ] = layoutInflater.inflate ( R.layout.wang2 , null );
        views[ 2 ] = layoutInflater.inflate ( R.layout.wang3 , null );

//        fragmentList = new ArrayList<> ();
//        fragmentList.add ( new Wang1 () );
//        fragmentList.add ( new Wang2 () );
//        fragmentList.add ( new Wang3 () );
//        tabLayout.getTabAt ( 0 ).setText ( "我的" ).setIcon ( R.drawable.ic_launcher_background );
//        tabLayout.getTabAt ( 0 ).setText ( titles[0] ).setIcon ( R.drawable.ic_launcher_background );
//        tabLayout.getTabAt ( 1 ).setText ( titles[1] );
//        tabLayout.getTabAt ( 2 ).setText ( titles[2] );

        myAdapterM = new MyAdapterM (getChildFragmentManager (),tabLayout.getTabCount ());
        viewPager.setAdapter ( myAdapterM );
//        tabLayout.setupWithViewPager ( viewPager );
        viewPager.addOnPageChangeListener ( new TabLayout.TabLayoutOnPageChangeListener ( tabLayout ) );
        tabLayout.addOnTabSelectedListener ( new TabLayout.OnTabSelectedListener () {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem ( tab.getPosition () );
                if (tab.getPosition () == 1) {
                    //这里是一个xml写项 tablayout改为相应的布局
                    tabLayout.setBackgroundColor ( ContextCompat.getColor ( getActivity () , R.color.colorAccent ) );
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        } );

    }


    }
