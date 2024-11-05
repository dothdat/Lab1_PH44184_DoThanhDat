package com.datddtph44184.lab1_ph44184_dothanhdat;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPage extends FragmentStateAdapter {

    public ViewPage(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new TheLoai();
            case 1:
                return new Product();
        }
        return new TheLoai();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
