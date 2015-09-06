package com.daviddetena.baccus.controller.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.daviddetena.baccus.controller.fragment.WineFragment;

public class WineActivity extends FragmentContainerActivity {

    // Constante con la que recibiremos argumento al invocar este Activity desde un Intent
    public static final String EXTRA_WINE = "com.daviddetena.baccus.controller.activity.WineActivity.EXTRA_WINE";


    @Override
    protected Fragment createFragment() {
        // Los fragment se comunican mediante argumentos, y no mediante intents.
        // Le pasamos al fragment, mediante el argumento ARG_WINE, el vino que a esta clase le llega
        // como extra. Simplemente le pasamos la pelota.
        Bundle arguments = new Bundle();
        arguments.putSerializable(WineFragment.ARG_WINE, getIntent().getSerializableExtra(EXTRA_WINE));

        // Devolvemos el fragment con estos argumentos
        WineFragment fragment = new WineFragment();
        fragment.setArguments(arguments);

        return fragment;
    }
}
