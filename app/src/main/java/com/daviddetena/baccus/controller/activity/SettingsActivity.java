package com.daviddetena.baccus.controller.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.daviddetena.baccus.R;
import com.daviddetena.baccus.controller.fragment.SettingsFragment;

/**
 * Created by daviddetena on 05/09/15.
 * Clase para la pantalla de Ajustes
 */
public class SettingsActivity extends FragmentContainerActivity{

    // Constante que utilizaremos como clave para comunicarnos con otras actividades y
    // con el que referenciaremos el tipo de de escala de imagen seleccionado
    public static final String EXTRA_WINE_IMAGE_SCALE_TYPE = "com.daviddetena.baccus.controller.activity.SettingsActivity.EXTRA_WINE_IMAGE_SCALE_TYPE";


    /**
     *
     * @return
     */
    @Override
    protected Fragment createFragment() {
        // Los fragment se comunican mediante argumentos, y no mediante intents.
        // Le pasamos al fragment, mediante el argumento ARG_WINE_IMAGE_SCALE_TYPE la escala de la
        // la imagen. Simplemente le pasamos la pelota.
        Bundle arguments = new Bundle();
        arguments.putSerializable(SettingsFragment.ARG_WINE_IMAGE_SCALE_TYPE, getIntent().getSerializableExtra(EXTRA_WINE_IMAGE_SCALE_TYPE));

        SettingsFragment fragment = new SettingsFragment();
        fragment.setArguments(arguments);

        return fragment;
    }
}
