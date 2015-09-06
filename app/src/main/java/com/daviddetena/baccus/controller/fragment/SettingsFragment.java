package com.daviddetena.baccus.controller.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.daviddetena.baccus.R;
import com.daviddetena.baccus.controller.activity.SettingsActivity;

/**
 * Created by daviddetena on 06/09/15.
 */
public class SettingsFragment extends Fragment implements View.OnClickListener{

    // Constante que utilizaremos como clave para comunicarnos con otras actividades y
    // con el que referenciaremos el tipo de de escala de imagen seleccionado
    public static final String ARG_WINE_IMAGE_SCALE_TYPE = "com.daviddetena.baccus.controller.fragment.SettingsFragment.ARG_WINE_IMAGE_SCALE_TYPE";


    // Vistas del Settings Activity
    private RadioGroup mRadioGroup = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        // Vista raíz que carga el layout de fragment
        View root = inflater.inflate(R.layout.fragment_settings, container, false);


        mRadioGroup = (RadioGroup) root.findViewById(R.id.scale_type_radios);

        // Necesitamos saber qué botón es el que pulsó el usuario para dejarlo como checked para
        // que se lo encuentre así la próxima vez que vaya a la pantalla
        if(getArguments().getSerializable(ARG_WINE_IMAGE_SCALE_TYPE).equals(ImageView.ScaleType.FIT_XY)){
            mRadioGroup.check(R.id.fit_radio);
        }
        else{
            if(getArguments().getSerializable(ARG_WINE_IMAGE_SCALE_TYPE).equals(ImageView.ScaleType.FIT_CENTER)){
                mRadioGroup.check(R.id.center_radio);
            }
        }

        // Obtenemos botones de la interfaz
        Button cancelButton = (Button) root.findViewById(R.id.cancel_button);
        Button saveButton = (Button) root.findViewById(R.id.save_button);

        // Los dos botones tienen por Listener la propia actividad.
        cancelButton.setOnClickListener(this);
        saveButton.setOnClickListener(this);

        return root;
    }

    /**
     * Método de la interfaz OnClickListener que esta clase tiene que implementar. Es aquí donde
     * comprobamos qué botón es el que se ha pulsado
     * @param view Vista que provocó el evento OnClick
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cancel_button:
                cancelSettings();
                break;

            case R.id.save_button:
                saveSettings();
                break;
        }
    }


    /**
     * Método que se ejecutará cuando se pulse el botón de guardar
     */
    private void saveSettings(){
        Intent config = new Intent();

        // Guardamos el valor de la escala según "Ajustada" o "Centrada" en nuestra clave estática
        // definida arriba EXTRA_WINE_IMAGE_SCALE_TYPE
        // Dejamos EXTRA_WINE_IMAGE_SCALE_TYPE ya que a quien se lo estamos pasando, otra actividad,
        // EXTRA_WINE_IMAGE_SCALE_TYPE
        if(mRadioGroup.getCheckedRadioButtonId() == R.id.fit_radio){
            config.putExtra(SettingsActivity.EXTRA_WINE_IMAGE_SCALE_TYPE, ImageView.ScaleType.FIT_XY);
        }
        else{
            if(mRadioGroup.getCheckedRadioButtonId() == R.id.center_radio){
                config.putExtra(SettingsActivity.EXTRA_WINE_IMAGE_SCALE_TYPE, ImageView.ScaleType.FIT_CENTER);
            }
        }

        // Esta forma de crear los argumentos donde un fragment finaliza la ejecución de una actividad
        // no es la mejor forma ni la más común.
        // Hay que evitar que los fragment sean los que controlen la actividad. Sería mejor informar
        // a la actividad en la que el fragment está contenido de que el usuario ha elegido la opción
        // que sea. La cambiaremos más adelante
        getActivity().setResult(Activity.RESULT_OK, config);
        getActivity().finish();
    }

    /**
     * Método que se ejecutará cuando se pulse el botón de cancelar. Devolvemos un RESULT_CANCELED
     * y cerramos vista
     */
    private void cancelSettings() {
        getActivity().setResult(Activity.RESULT_CANCELED);
        getActivity().finish();
    }
}