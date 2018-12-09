> 转载请指明出处 WangYouHu 版权归博主所有



> Fragment应用越来越广泛 当初在android3.0加入fragment是为了解决手机和平板之间的屏幕兼容问题 平板屏幕比较大
> 屏幕空间不能充分利用的解决方案 现在手机上很多app也使用到了fragment解决屏幕小的问题 如QQ 微信  爱奇艺 网易云音乐等

![在这里插入图片描述](https://img-blog.csdnimg.cn/20181210001729224.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxOTEyNDQ3,size_16,color_FFFFFF,t_70)![在这里插入图片描述](https://img-blog.csdnimg.cn/20181210001701129.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxOTEyNDQ3,size_16,color_FFFFFF,t_70)

通过你点击一个tab 然后跳转到相应的界面 




*目前很多公司要求谷歌放弃fragment 因为他生命周期很复杂 还会出现很多漏洞 但现在很多公司要求会使用fragment*
***我们遇到的问题是什么？***

 - 1*由于fragment和activity生命周期不同 可能会出现内存不足销毁对象和屏幕旋转生命周期重建 造成重叠*
 - 2*当对fragment不进行正确添加删除隐藏显示会出现重叠* 
 - 3*对象存在 多次重建 影响性能* 
 - 4*存在父类fragment和子类fragment多次嵌套滑动冲突的现象*
 








*下面来看一下fragment和activity的生命周期？*
![在这里插入图片描述](https://img-blog.csdnimg.cn/20181210003308829.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxOTEyNDQ3,size_16,color_FFFFFF,t_70)
***在create和destroy时明显不同 fragment是嵌套在activity中 生命周期戚戚相关 有关详细资料请看谷歌android developers官网***
*重叠现象？*
重叠现象出现可能是你的不正确操作和内存回收造成生命周期重建等 这必须解决 否则会造成用户的体验甚至卸载你的应用
下面来看一张重叠的图 原因是内存不足系统自动释放内存造成或屏幕旋转造成生命周期改变



![在这里插入图片描述](https://img-blog.csdnimg.cn/20181210004614511.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxOTEyNDQ3,size_16,color_FFFFFF,t_70)我在示例中使用了自定义tab+fragLayout和官方tab+viewPager和PagerTabStrip+viewpager实现 你完全可以使用我的示例来做你的项目基本框架
**首先来看我的底部tab实现方法 作为父类fragment**
在这个布局中我实现点击布局切换framgment 为了用户点击更精确

```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity"
    android:orientation="vertical">


    <FrameLayout
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1"
        android:id="@+id/content">


    </FrameLayout>
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="60dp"
        android:orientation="horizontal">
        <RelativeLayout
            android:layout_weight="1"
            android:id="@+id/message_layout"
            android:layout_height="match_parent"
            android:layout_width="0dp">
            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_centerVertical="true"
                android:orientation="vertical">
                <ImageView
                    android:id="@+id/image_message"
                    android:layout_width="24dp"
                    android:layout_height="24dp"
                    android:layout_gravity="center_horizontal"
                    app:srcCompat="@android:color/holo_purple" />
                <TextView
                    android:id="@+id/message_text"
                    android:layout_gravity="center_horizontal"
                    android:text="消息"
                    android:textColor="#82858b"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content" />

            </LinearLayout>


        </RelativeLayout>
        <RelativeLayout
            android:layout_weight="1"
            android:id="@+id/news_layout"
            android:layout_height="match_parent"
            android:layout_width="0dp">
            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_centerVertical="true"
                android:orientation="vertical">

                <ImageView
                    android:id="@+id/image_news"
                    android:layout_width="24dp"
                    android:layout_height="24dp"
                    android:layout_gravity="center_horizontal"
                    app:srcCompat="@android:color/holo_blue_light" />
                <TextView
                    android:id="@+id/news_text"
                    android:layout_gravity="center_horizontal"
                    android:text="动态"
                    android:textColor="#82858b"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content" />

            </LinearLayout>


        </RelativeLayout>
        <RelativeLayout
            android:layout_weight="1"
            android:id="@+id/setting_layout"
            android:layout_height="match_parent"
            android:layout_width="0dp">
            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_centerVertical="true"
                android:orientation="vertical">

                <ImageView
                    android:id="@+id/image_setting"
                    android:layout_width="24dp"
                    android:layout_height="24dp"
                    android:layout_gravity="center_horizontal"
                    app:srcCompat="@android:color/holo_red_light" />
                <TextView
                    android:id="@+id/setting_text"
                    android:layout_gravity="center_horizontal"
                    android:text="设置"
                    android:textColor="#82858b"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content" />

            </LinearLayout>


        </RelativeLayout>


    </LinearLayout>
</LinearLayout>
```
***在mainactivity中继承点击 然后声明控件***

```
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
```
来看我的onctreate方法 设置无顶部toolbar initViews是我用来初始化的方法 fragmentManager是我们的碎片管理器 
getFragmentManager ();是以前旧的方法 现在使用v4包中的getSupportFragmentManager ();来兼容
在打开这个软件默认进入选中的第一项

```

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
//        设置无toolbar
        requestWindowFeature ( Window.FEATURE_NO_TITLE );
        setContentView ( R.layout.activity_main );
        initViews ();
        fragmentManager = getSupportFragmentManager ();
        //设置选中
        setTabSelection(0);
    }
```

```
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
```
当类扩展点击的方法需要实现以下方法 这里是每个tab布局的点击实现的功能 setTabSelection是我写的一个方法 当点击底部tab选中实现的功能 也增加了代码的规范性

```
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
```
FragmentTransaction 是framgent操作的分管理者 专门用来管理fragment一些操作
当点击的时候清除所有的项（实现不选中的tab图标 背景颜色 字体颜色等）和 隐藏所有项 （避免出现重叠）
当判断是哪一项 则设置选中tab的样式 判断碎片是否为空 当为空初始化添加 若不为空则显示  （这避免了多次重建出现重叠）
当然最后别忘了提交commit（）；
```
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

```
设置这三个tab不选中的原生样式 当然你可以根据自己需要而定

```
private void clearSelection() {
    //设置清除后的图片文字修改
    message_image.setImageResource ( R.drawable.ic_launcher_background );
    message_text.setTextColor ( Color.parseColor ( "#82858b" ) );
    news_image.setImageResource ( R.drawable.ic_launcher_background );
    news_text.setTextColor ( Color.parseColor ( "#82858b" ) );
    setting_image.setImageResource ( R.drawable.ic_launcher_background );
    setting_text.setTextColor ( Color.parseColor ( "#82858b" ) );

}
```
隐藏碎片也是核心了 避免重叠 需要判断是否为空 否则会崩溃
 为什么不选择删除楠？
  删除重建影响性能 在创建的时候判断是否为空再创建 这也是对资源的正确分配
```
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
```
内存过大系统回收内存也会造成重叠 他再次打开会从缓存加载已有对象 直接导致多次创建
 这里才是核心 在缓存的时候正确处理 让他不保存或隐藏 这样才可以避免重叠 在恢复的时候选中第一个 当然我是怕隐藏了全部 做出的处理 二次处理有利于性能的稳定 减少bug
 

```
@Override
protected void onSaveInstanceState(Bundle outState) {
    FragmentTransaction transaction=fragmentManager.beginTransaction ();
    hideFragment(transaction);
    transaction.commit ();
    super.onSaveInstanceState ( outState );
}

@Override
protected void onRestoreInstanceState(Bundle savedInstanceState) {
    FragmentTransaction transaction=fragmentManager.beginTransaction ();
    hideFragment(transaction);
    transaction.commit ();
    setTabSelection(0);

    super.onRestoreInstanceState ( savedInstanceState );
}
```
到这里父类-自定义tab+fragLayout就实现完毕 
这里为什么没有使用viewPager而是使用framLayout？
是避免和子类fragment造成滑动相互影响


*下面来看子类第一种官方tab+viewpager实现*
tablayout中的项可以不写 在类中写 如下方法
实现文字加图标

 tabLayout.getTabAt ( 0 ).setText ( "我的" ).setIcon ( R.drawable.ic_launcher_background );
 实现文字
  tabLayout.getTabAt ( 0 ).setText ( "我的" )；
  xml布局则使用text和icon属性
  下面是一个viewPager 它可以实现滑动 

```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">


    <android.support.design.widget.TabLayout
        android:id="@+id/tabMode"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <android.support.design.widget.TabItem
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:icon="@color/colorPrimary"
            android:text="Left" />

        <android.support.design.widget.TabItem
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:icon="@color/colorAccent"
            android:text="Center" />

        <android.support.design.widget.TabItem
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:icon="@color/colorPrimaryDark"
            android:text="Right" />
    </android.support.design.widget.TabLayout>

    <android.support.v4.view.ViewPager
        android:id="@+id/viewPager"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />
</LinearLayout>

```
这是我写的辅助类工具 是viewPager的适配器 
里面加入一个传递参数的方法 
当你要添加你自己的类写入自己的类名
```
public class MyAdapterM extends FragmentPagerAdapter {
    private int num;
    Wang1 wang1;
    Wang2 wang2;
    Wang3 wang3;

    public MyAdapterM(FragmentManager fm, int num) {
        super ( fm );
        this.num=num;
    }


    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                if (wang1==null){
                    return new Wang1 ();
                }


            case 1:
                if (wang2==null){
                    return new Wang2 ();
                }

            case 2:
                if (wang3==null){
                    return new Wang3 ();
                }

                default:
                    return null;

        }

    }

    @Override
    public int getCount() {
        return num;
    }
}

```
这是我的类文件 我是采用了view的方法 父类fragment则采用add的方法
```
public class MessageFragment extends Fragment {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    //    private List<Fragment> fragmentList;
    private MyAdapterM myAdapterM;
    private View[] views;

```
注意fragment中的控件一定要在view中查找 使用getActivity方法可能会出现空对象错误

```
@Nullable
@Override
public View onCreateView(@NonNull LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate ( R.layout.message_layout , container , false );
    viewPager = view.findViewById ( R.id.viewPager );
    tabLayout = view.findViewById ( R.id.tabMode );
    setUI ();
    return view;
}
```

指明view具有多少对象 并一一添加进去 注意0则是1 
然后初始化MyAdapterM类 并传入参数
并一一相互关联
onTabSelected方法中写入选中tab的样式
在这里我考虑了安卓低版本 根据需要设置


```
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



```
到这里就写完了第一个子类示例 下面是效果图
![在这里插入图片描述](https://img-blog.csdnimg.cn/20181210015151357.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxOTEyNDQ3,size_16,color_FFFFFF,t_70)

下面来看使用PagerTabStrip+viewpager实现嵌套

```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">
    <android.support.v4.view.ViewPager
        android:id="@+id/viewPager"
        android:layout_width="match_parent"
        android:layout_height="match_parent" >
        <android.support.v4.view.PagerTabStrip
            android:id="@+id/pagerTabStrip"
            android:layout_width="300dp"
            android:layout_height="wrap_content"
            android:layout_gravity="top"

            />
        <!--顶部top 底部bottom-->
    </android.support.v4.view.ViewPager>
</LinearLayout>

```
同样是封装在view中 并定义适配器属性
```

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
```
在这里对对象进行了销毁 并不会重叠 重要数据可以保存到内存卡中 将标题添加进去
```
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
```
这时第二个子类嵌套完成 下面来看效果图
![在这里插入图片描述](https://img-blog.csdnimg.cn/20181210020050202.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxOTEyNDQ3,size_16,color_FFFFFF,t_70)

下面来写第三个嵌套子类 更倾向于淘宝美团的fragment 这里使用了滚动布局加textView实现点击切换 同样是不使用viewPager 以免造成滚动布局相互冲突
在xml布局中可以看到我使用了scrollview滚动布局 并用framLayout写入fragment

```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">



    <ScrollView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:scrollbars="none"
        android:layout_weight="2">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="vertical">
        <LinearLayout
            android:id="@+id/tvLayout1"
            android:layout_width="match_parent"
            android:layout_height="350dp"
            android:orientation="vertical">

            <TextView
                android:id="@+id/tv1"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:textSize="20sp"
                android:gravity="center"
                android:text="中国" />
        </LinearLayout>
        <LinearLayout
            android:id="@+id/tvLayout2"
            android:layout_width="match_parent"
            android:layout_height="350dp"
            android:orientation="vertical">

            <TextView
                android:id="@+id/tv2"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:textSize="20sp"
                android:gravity="center"
                android:text="美国" />
        </LinearLayout>
        <LinearLayout
            android:id="@+id/tvLayout3"
            android:layout_width="match_parent"
            android:layout_height="350dp"
            android:orientation="vertical">

            <TextView
                android:id="@+id/tv3"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:textSize="20sp"
                android:gravity="center"
                android:text="加拿大" />
        </LinearLayout>


        </LinearLayout>
    </ScrollView>

    <FrameLayout
        android:id="@+id/scrollIndicatorDown"
        android:layout_weight="1"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

    </FrameLayout>

</LinearLayout>

```
这里就是简单的绑定id
```
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

```
这里是上面注释掉的代码 我希望减轻点负担给系统 放在创建完成未显示之前 让他们分工明确 你也可以省略这步
切记 不要在这里使用getActivity查找对象id绑定 可能出现空对象
 网络好多建议在这里实现控件的功能 因为在onCreateView生命周期的时候并没有初始化好id
 代码实现是点击的功能实现 和选中第一个项并显示

```
@Override
public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated ( savedInstanceState );
    setUI ();
    setTabSelection ( 0 );
}

```
在这里我遇到了重建不显示fragment的问题 很是纠结 在fragment判断存在的时候加了log打印 可以看出fragment一直存在 但就是不显示 
最后通过修改getFragmentManager ();为getChildFragmentManager ();
getChildFragmentManager ();是子类碎片管理器 用来嵌套用的
getFragmentManager ();是父类碎片管理器 本来觉得没什么大不了的 但出现了bug 然后就显示了
安卓是一个分工规范的系统 他在封装方法并未写可以管理父类和子类的方法
也讲解一下getFragmentManager ();已弃用 建议使用getSupportFragmentManager ();
安卓库的更新 调用最新的方法会有简洁稳定全面的体验



```

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

```
隐藏碎片和设置tab样式
```
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
```
本来想使用getid方法减少代码 但也是出现了错误 我就在view中查找对象并操作解决了问题
看来偷懒害死人 呜呜
```
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

```
到这里第三个子类示范也已经结束 整体也已经写完 下面来看一下效果图和整体效果图
![在这里插入图片描述](https://img-blog.csdnimg.cn/20181210022903493.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxOTEyNDQ3,size_16,color_FFFFFF,t_70)

最后示例一下整体效果  由于本博主没有太好的录屏工具![在这里插入图片描述](https://img-blog.csdnimg.cn/20181210023151325.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxOTEyNDQ3,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20181210023218140.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxOTEyNDQ3,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20181210023259483.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxOTEyNDQ3,size_16,color_FFFFFF,t_70)
也给大家模拟一下内存回收的方法 在开发者选项中打开如下开关
![在这里插入图片描述](https://img-blog.csdnimg.cn/20181210023509803.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxOTEyNDQ3,size_16,color_FFFFFF,t_70)
最后如果你觉得小编写的不错 希望给小编一个赞和指出错误 当然你也可以使用github上的fragmnetion框架 
