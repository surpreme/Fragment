package com.liziyang.dall.gugedemofragment;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.PhantomReference;
import java.lang.ref.WeakReference;
import java.util.HashMap;

public class NewsFragment extends Fragment {
    private ViewPager viewPager;
    private View[] views;
    private String[] topstring={"电影","音乐","直播"};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate ( R.layout.news_layout,container,false );
        viewPager=view.findViewById ( R.id.viewPager );
        LayoutInflater layoutInflater=LayoutInflater.from ( getActivity () );
        views=new View[3];
        views[0]=layoutInflater.inflate ( R.layout.my1,null );
        views[1]=layoutInflater.inflate ( R.layout.my2,null );
        views[2]=layoutInflater.inflate ( R.layout.my3,null );
        viewPager.setAdapter ( new MyAdapter() );

        return view;
    }

    private class MyAdapter extends PagerAdapter {
        /**
         * 得到数量
         * @return
         */
        @Override
        public int getCount() {
            return views.length;
        }

        /**
         * 来源
         * @param
         * @param o
         * @return
         */
        @Override
        public boolean isViewFromObject(@NonNull View p , @NonNull Object o) {
            return p==o;
        }

        /**
         * 点击处理
         * @param container
         * @param position
         * @return
         */
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container , int position) {

                container.addView ( views[position] );

            return views[position];
        }

        /**
         * 设置标题
         * @param position
         * @return
         */
        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return topstring[position];
        }

        /**
         * 销毁项
         * @param container
         * @param position
         * @param object
         */
        @Override
        public void destroyItem(@NonNull ViewGroup container , int position , @NonNull Object object) {
                container.removeView ( views[position] );


        }

        public MyAdapter() {
            super ();
        }

        @Override
        public void startUpdate(@NonNull ViewGroup container) {
            super.startUpdate ( container );
        }


        @Override
        public void setPrimaryItem(@NonNull ViewGroup container , int position , @NonNull Object object) {
            super.setPrimaryItem ( container , position , object );
        }

        @Override
        public void finishUpdate(@NonNull ViewGroup container) {
            super.finishUpdate ( container );
        }

        @Nullable
        @Override
        public Parcelable saveState() {
            return super.saveState ();
        }

        @Override
        public void restoreState(@Nullable Parcelable state , @Nullable ClassLoader loader) {
            super.restoreState ( state , loader );
        }

        @Override
        public int getItemPosition(@NonNull Object object) {
            return super.getItemPosition ( object );
        }

        @Override
        public void notifyDataSetChanged() {
            super.notifyDataSetChanged ();
        }

        @Override
        public void registerDataSetObserver(@NonNull DataSetObserver observer) {
            super.registerDataSetObserver ( observer );
        }

        @Override
        public void unregisterDataSetObserver(@NonNull DataSetObserver observer) {
            super.unregisterDataSetObserver ( observer );
        }


        @Override
        public float getPageWidth(int position) {
            return super.getPageWidth ( position );
        }
        @Override
        public int hashCode() {
            return super.hashCode ();
        }

        @Override
        public boolean equals( Object obj) {
            return super.equals ( obj );
        }
        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone ();
        }
        @Override
        public String toString() {
            return super.toString ();
        }
        @Override
        protected void finalize() throws Throwable {
            super.finalize ();
        }
    }
}
