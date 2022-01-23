package com.iitdelhi.calculator.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.iitdelhi.calculator.Fragments.Calculator;
import com.iitdelhi.calculator.Fragments.ExchangeRate;
import com.iitdelhi.calculator.Fragments.UnitConverter;

public class FragmentAdapter extends FragmentPagerAdapter {
    public FragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position == 0)
            return new Calculator();
        else if (position == 1)
            return new ExchangeRate();
        else if (position == 2)
            return new UnitConverter();
        return new Calculator();
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title;
        if(position == 0)
            title = "Calculator";
        else if (position == 1){
            title = "Exchange Rate";
        }
        else
            title = "Unit converter";

        return title;
    }
}
