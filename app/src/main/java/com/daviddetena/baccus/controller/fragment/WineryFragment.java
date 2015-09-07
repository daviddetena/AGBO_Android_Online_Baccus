package com.daviddetena.baccus.controller.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daviddetena.baccus.R;
import com.daviddetena.baccus.controller.adapter.WineryPagerAdapter;

/**
 * Created by daviddetena on 06/09/15.
 */
public class WineryFragment extends Fragment {

    private ViewPager mPager = null;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_winery, container, false);

        // Obtenemos referencia a ViewPager
        mPager = (ViewPager) root.findViewById(R.id.pager);

        // Indicamos el adaptador que tendrá el Pager, que es el que hemos creado nosotros
        mPager.setAdapter(new WineryPagerAdapter(getFragmentManager()));

        return root;
    }
}
