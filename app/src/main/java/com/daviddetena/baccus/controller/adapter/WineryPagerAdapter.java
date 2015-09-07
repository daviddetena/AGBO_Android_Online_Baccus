package com.daviddetena.baccus.controller.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.daviddetena.baccus.controller.fragment.WineFragment;
import com.daviddetena.baccus.model.Winery;

/**
 * Created by daviddetena on 07/09/15.
 */
public class WineryPagerAdapter extends FragmentPagerAdapter {

    private Winery mWinery = null;

    public WineryPagerAdapter(FragmentManager fm) {
        super(fm);

        // Obtenemos la instancia de la bodega actual
        mWinery = Winery.getInstance();
    }

    /**
     * Devuelve cada uno de los fragments, según nos lo vayan pidiendo, según su posición.
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {

        // Pantalla que vamos a mostrar en la posición indicada. Para ello, le pasamos al fragment
        // el vino de la posición position como argumento
        WineFragment fragment = new WineFragment();
        Bundle arguments = new Bundle();
        arguments.putSerializable(WineFragment.ARG_WINE, mWinery.getWine(position));
        fragment.setArguments(arguments);

        return fragment;
    }

    /**
     * Nos devuelve el número de pantallas que tengo que mostrar en este Adapter. El número de
     * pantallas que mostraremos será el número de vinos de la bodega
     * @return
     */
    @Override
    public int getCount() {
        return mWinery.getWineCount();
    }


    /**
     * Método que nos devuelve el título de cada página según el nombre del vino pasado como
     * parámetro
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        super.getPageTitle(position);
        return mWinery.getWine(position).getName();
    }
}
