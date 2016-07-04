package app.connect.com.connect.Fragments;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.connect.com.connect.Adapters.TabsAdapter;
import app.connect.com.connect.BancoDeDados.PerfilDAO;
import app.connect.com.connect.R;
import app.connect.com.connect.SlidingTabLayout;

/**
 * A placeholder fragment containing a simple view.
 */
public class FragmentMain extends Fragment {

    //TabView
    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;




    public FragmentMain() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView;
        rootView = inflater.inflate(R.layout.fragment_main, container, false);


        //Tabs
        mViewPager = (ViewPager) rootView.findViewById(R.id.vp_tabs);
        mViewPager.setAdapter(new TabsAdapter(getChildFragmentManager(), getContext()));
        mSlidingTabLayout = (SlidingTabLayout) rootView.findViewById(R.id.stl_tabs);
        mSlidingTabLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        mSlidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.colorAccent));
        //Vinculando viewPager ao slidingTabs
        mSlidingTabLayout.setViewPager(mViewPager);
        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment, new FragmentRequest());
                ft.addToBackStack(null);
                ft.commit();
            }
        });



        return rootView;
    }
}
