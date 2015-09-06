package com.daviddetena.baccus.controller.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.daviddetena.baccus.controller.fragment.WebFragment;

/**
 * Created by daviddetena on 05/09/15.
 */
public class WebActivity extends FragmentContainerActivity {

    // Constante con la hacemos referencia al modelo (Wine) que viene de la pantalla anterior y que
    // recogeremos mediante getIntent().getSerializableExtra()
    public static final String EXTRA_WINE = "com.daviddetena.baccus.controller.activity.WineActivity.extra_wine";

    /**
     * Método que toda clase que herede de FragmentContainerActivity tiene que implementar
     * @return
     */
    @Override
    protected Fragment createFragment() {

        // Los fragment se comunican mediante argumentos, y no mediante intents.
        // Le pasamos al fragment, mediante el argumento ARG_WINE, el vino que a esta clase le llega
        // como extra. Simplemente le pasamos la pelota.
        Bundle arguments = new Bundle();
        arguments.putSerializable(WebFragment.ARG_WINE, getIntent().getSerializableExtra(EXTRA_WINE));

        // Devolvemos el fragment con estos argumentos
        WebFragment fragment = new WebFragment();
        fragment.setArguments(arguments);

        return fragment;
    }
}
