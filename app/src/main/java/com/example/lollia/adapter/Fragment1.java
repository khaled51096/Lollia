package com.example.lollia.adapter;

import com.example.lollia.ui.fragment.Contact;
import com.example.lollia.ui.fragment.Home;
import com.example.lollia.ui.fragment.Products;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class Fragment1 extends FragmentStatePagerAdapter {
    int tabCount;
    public Fragment1(@NonNull FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount=tabCount;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Home tab1 = new Home();
                return tab1;
            case 1:
                Products tab2 = new Products();
                return tab2;
            case 2:
                Contact tab3 = new Contact();
                return tab3 ;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
