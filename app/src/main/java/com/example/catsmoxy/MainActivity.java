package com.example.catsmoxy;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback;

import com.example.catsmoxy.favourites.ui.CatsFavouriteFragment;
import com.example.catsmoxy.list.ui.CatsListFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    private String[] mTabTitles = new String[]{"Cats List", "Favourites Cats"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewPager();
    }

    private void initViewPager() {
        ViewPager2 viewPager2 = findViewById(R.id.view_pager);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        viewPager2.setAdapter(new ViewPagerAdapter(this));
        new TabLayoutMediator(tabLayout, viewPager2,
            (tab, position) -> tab.setText(mTabTitles[position]))
            .attach();
        viewPager2.registerOnPageChangeCallback(new OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                    if (fragment instanceof CatsFavouriteFragment) {
                        ((CatsFavouriteFragment) fragment).loadCatsFromDb();
                    }
                }
            }
        });
    }

    private class ViewPagerAdapter extends FragmentStateAdapter {

        public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new CatsListFragment();
                case 1:
                    return new CatsFavouriteFragment();
            }
            return new CatsListFragment();
        }

        @Override
        public int getItemCount() {
            return mTabTitles.length;
        }
    }
}
