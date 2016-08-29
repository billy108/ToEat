package com.chuangsi.toeat.activities;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chuangsi.toeat.R;
import com.chuangsi.toeat.adapters.MainPagerAdapter;
import com.chuangsi.toeat.fragments.FoodListFragment;
import com.chuangsi.toeat.fragments.HobbyFragment;
import com.chuangsi.toeat.fragments.ShopFragment;
import com.chuangsi.toeat.fragments.TodayFragment;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity implements View.OnClickListener{
    private ViewPager viewPager_main;
    private TextView textView_main_today;
    private TextView textView_main_food_list;
    private TextView textView_main_shop;
    private TextView textView_main_hobby;
    private TextView textView_top_title;
    private ImageView iv_main_back;

    private ArrayList<Fragment> fragmentList;
    private boolean isConnected;
    private View view_main_top;

    private TodayFragment todayFragment;
    private FoodListFragment foodListFragment;
    private ShopFragment shopFragment;
    private HobbyFragment hobbyFragment;

    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        checkNetwork();

        initData();
        initView();

    }


    public void initData() {
        if (fragmentList == null) {
            fragmentList = new ArrayList<Fragment>();
        }
        manager = getSupportFragmentManager();
    }

    public void initView() {
        view_main_top = LayoutInflater.from(this).inflate(R.layout.layout_top_title, null);
        textView_top_title = (TextView) view_main_top.findViewById(R.id.tv_topBar_title);

        viewPager_main = (ViewPager) findViewById(R.id.viewPager_main);
        textView_main_today = (TextView) findViewById(R.id.textView_main_today);
        textView_main_food_list = (TextView) findViewById(R.id.textView_main_food_list);
        textView_main_shop = (TextView) findViewById(R.id.textView_main_shop);
        textView_main_hobby = (TextView) findViewById(R.id.textView_main_hobby);
        iv_main_back = (ImageView) findViewById(R.id.iv_topBar_back);
        iv_main_back.setVisibility(View.GONE);

        textView_main_today.setOnClickListener(this);
        textView_main_shop.setOnClickListener(this);
        textView_main_food_list.setOnClickListener(this);
        textView_main_hobby.setOnClickListener(this);

        if (fragmentList.size() == 0) {
            todayFragment = new TodayFragment();
            foodListFragment = new FoodListFragment();
            shopFragment = new ShopFragment();
            hobbyFragment = new HobbyFragment();

            fragmentList.add(todayFragment);
            fragmentList.add(foodListFragment);
            fragmentList.add(shopFragment);
            fragmentList.add(hobbyFragment);
        }

        viewPager_main.setAdapter(new MainPagerAdapter(manager, fragmentList));
        viewPager_main.setCurrentItem(0);


    }

    /**
     * 检查网络
     */
    private void checkNetwork() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info != null && info.isAvailable()) {
            isConnected = true;
        } else {
            Toast.makeText(this, "网络异常,请检查", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textView_main_today:
                viewPager_main.setCurrentItem(0);
                break;
            case R.id.textView_main_food_list:
                viewPager_main.setCurrentItem(1);
                break;
            case R.id.textView_main_shop:
                viewPager_main.setCurrentItem(2);
                break;
            case R.id.textView_main_hobby:
                viewPager_main.setCurrentItem(3);
                break;

        }
    }
}
