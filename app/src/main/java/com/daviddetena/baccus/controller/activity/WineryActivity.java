package com.daviddetena.baccus.controller.activity;

import android.support.v4.app.Fragment;

import com.daviddetena.baccus.controller.fragment.WineryFragment;

public class WineryActivity extends FragmentContainerActivity {

    /**
     * Como el Winery no recibe argumentos, sino que crea los vinos en su onCreateView() y es el
     * quien los pasa como argumentos a WineActivity, sólo necesitamos devolver una instancia del
     * Fragment
     * @return
     */
    @Override
    protected Fragment createFragment() {
        return new WineryFragment();
    }
}
