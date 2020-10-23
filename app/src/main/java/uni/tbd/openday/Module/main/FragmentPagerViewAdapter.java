package uni.tbd.openday.Module.main;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;



public class FragmentPagerViewAdapter extends FragmentPagerAdapter {

    private Fragment[] list;
    private String[] labelList;

    public FragmentPagerViewAdapter(FragmentManager fm, Fragment[] list, String[] labelList) {
        super(fm);
        this.list = list;
        this.labelList = labelList;
    }

    @Override
    public Fragment getItem(int position) {
        return list[position];
    }

    @Override
    public int getCount() {
        return list.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return labelList[position];
    }
}
