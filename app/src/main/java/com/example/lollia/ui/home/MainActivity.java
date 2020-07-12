package com.example.lollia.ui.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.lollia.adapter.Fragment1;
import com.example.lollia.R;
import com.example.lollia.ui.menu.about;
import com.example.lollia.ui.menu.favorite;
import com.example.lollia.ui.menu.offer;
import com.example.lollia.ui.menu.pin_shop;
import com.facebook.login.LoginManager;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final int REQUEST_RUNTIME_PERMISSION = 123;

    private ViewPager viewPager;
    private TabLayout tabLayout;
    public static int pos=0;
    private DrawerLayout drawerLayout;
    private NavigationView navviewer;
    private ImageView navButton ;
    private FirebaseAuth mauth;
    private boolean exit = false;



    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        if (exit){finish();}
        else {
            Toast.makeText(MainActivity.this,"press Back again to exit",Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit=false;
                }
            },3 * 1000);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        overridePendingTransition(R.anim.activity_slide_in, R.anim.activity_slide_out);

        mauth = FirebaseAuth.getInstance();
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.getHeight();
        viewPager = (ViewPager) findViewById(R.id.pager);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        navButton = (ImageView) findViewById(R.id.nav_view_button) ;

        navButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        navviewer = (NavigationView) findViewById(R.id.navviewer);
        navviewer.setNavigationItemSelectedListener(this);

        //exit from app




        tabLayout.addTab(tabLayout.newTab().setText("الرئيســـــية").setIcon(getResources().getDrawable(R.drawable.home)));
        tabLayout.addTab(tabLayout.newTab().setText("منتجاتنــــا").setIcon(getResources().getDrawable(R.drawable.baseline_shopping_basket_black_18)));
        tabLayout.addTab(tabLayout.newTab().setText("تواصل بنـــــا").setIcon(getResources().getDrawable(R.drawable.ic_phone)));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        Fragment1 adapter = new Fragment1(getSupportFragmentManager(),tabLayout.getTabCount());
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(adapter);

        tabLayout.getTabAt(0).setText("الرئيســـــية").setIcon(getResources().getDrawable(R.drawable.home));
        tabLayout.getTabAt(1).setText("منتجاتنــــا").setIcon(getResources().getDrawable(R.drawable.baseline_shopping_basket_black_18));
        tabLayout.getTabAt(2).setText("تواصل بنـــــا").setIcon(getResources().getDrawable(R.drawable.ic_phone));



        View root = tabLayout.getChildAt(0);
        if (root instanceof LinearLayout) {
            ((LinearLayout) root).setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
            GradientDrawable drawable = new GradientDrawable();
            drawable.setSize(2, 1);
            ((LinearLayout) root).setDividerPadding(10);
            ((LinearLayout) root).setDividerDrawable(drawable);
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pos = tab.getPosition();
                if (pos == 0) {
                    tabLayout.getTabAt(0).setIcon(getResources().getDrawable(R.drawable.home_pressed));
                    tabLayout.getTabAt(1).setIcon(getResources().getDrawable(R.drawable.baseline_shopping_basket_black_18));
                    tabLayout.getTabAt(2).setIcon(getResources().getDrawable(R.drawable.ic_phone));
                } else if (pos == 1) {
                    tabLayout.getTabAt(0).setIcon(getResources().getDrawable(R.drawable.home));
                    tabLayout.getTabAt(1).setIcon(getResources().getDrawable(R.drawable.baseline_shopping_basket_black_18));
                    tabLayout.getTabAt(2).setIcon(getResources().getDrawable(R.drawable.ic_phone));
                } else if (pos == 2) {
                    tabLayout.getTabAt(0).setIcon(getResources().getDrawable(R.drawable.home));
                    tabLayout.getTabAt(1).setIcon(getResources().getDrawable(R.drawable.baseline_shopping_basket_black_18));
                    tabLayout.getTabAt(2).setIcon(getResources().getDrawable(R.drawable.ic_phone_in_talk));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
    {
        int id = menuItem.getItemId();
        if (id==R.id.logout)
        {
            LoginManager.getInstance().logOut();
            mauth.signOut();
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
            finish();

        }
        else if (id==R.id.favorite)
        {
            Intent intent = new Intent(MainActivity.this, favorite.class);
            startActivity(intent);

        }
        else if (id==R.id.pin)
        {
            Intent intent = new Intent(MainActivity.this, pin_shop.class);
            startActivity(intent);

        }
        else if (id==R.id.offer)
        {
            Intent intent = new Intent(MainActivity.this, offer.class);
            startActivity(intent);

        }
        else if (id==R.id.lolla)
        {
            Intent intent = new Intent(MainActivity.this, about.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


}
