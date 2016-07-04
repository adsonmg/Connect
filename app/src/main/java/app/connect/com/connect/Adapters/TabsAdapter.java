package app.connect.com.connect.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import app.connect.com.connect.Fragments.FragmentContacts;
import app.connect.com.connect.Fragments.FragmentProfile;

/**
 * Created by adson on 7/2/2016.
 */
public class TabsAdapter extends FragmentStatePagerAdapter {
    private Context mContext;
    private String[] titles = {"Perfil", "Contatos"};
    public TabsAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new FragmentProfile();
                break;
            case 1:
                fragment = new FragmentContacts();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
