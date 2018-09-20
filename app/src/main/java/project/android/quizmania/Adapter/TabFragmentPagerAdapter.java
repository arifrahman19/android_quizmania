package project.android.quizmania.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import project.android.quizmania.Fragment.KuisSekarangFragment;
import project.android.quizmania.Fragment.AturWaktuKuisFragment;


public class TabFragmentPagerAdapter extends FragmentPagerAdapter {

    String[] title = new String[]{
            "Kuis Sekarang", "Atur Waktu Kuis"
    };

    public TabFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new KuisSekarangFragment();
                break;
            case 1:
                fragment = new AturWaktuKuisFragment();
                break;
            default:
                fragment = null;
                break;
        }
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }

    @Override
    public int getCount() {
        return title.length;
    }
}
