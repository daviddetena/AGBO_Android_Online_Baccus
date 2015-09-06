package com.daviddetena.baccus.controller.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.daviddetena.baccus.R;

/**
 * Con esta clase conseguimos una forma genérica con la que nuestras actividades se conviertan
 * en actividades que contienen un único fragment. Para ello, simplemente tendrán que heredar de
 * esta clase e implementar el método createFragment()
 */
public abstract class FragmentContainerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Indicamos que la vista de esta clase es el activity genérico para fragment
        setContentView(R.layout.activity_fragment_container);

        // Utilizamos getSupportFragmentManager para dar soporte a dispositivos antiguos.
        // Si no nos hiciera falta, utilizaríamos el método getFragmentManager()
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        // Intentamos recuperar el fragment definido con el id por si ya tiene valor
        if(fragment == null){

            // Si no existe, tenemos que crearlo y hacer una transacción para añadirlo.
            // El método createFragment es un método abstracto que todas las clases que hereden
            // de FragmentContainerActivity deben implementar para tener un único fragment
            // en su interior. Como cada actividad creará su propio fragment, lo implementamos
            // fuera
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }

    /**
     * Método que tendrán que implementar las clases que hereden de esta clase genérica para crear
     * un fragment según sus necesidades
     * @return
     */
    protected abstract Fragment createFragment();
}
