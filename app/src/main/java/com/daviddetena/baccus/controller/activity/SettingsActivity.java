package com.daviddetena.baccus.controller.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.daviddetena.baccus.R;

/**
 * Created by daviddetena on 05/09/15.
 * Clase para la pantalla de Ajustes
 */
public class SettingsActivity extends Activity implements View.OnClickListener{

    // Constante que utilizaremos como clave para comunicarnos con otras actividades y
    // con el que referenciaremos el tipo de de escala de imagen seleccionado
    public static final String EXTRA_WINE_IMAGE_SCALE_TYPE = "com.daviddetena.baccus.controller.activity.SettingsActivity.EXTRA_WINE_IMAGE_SCALE_TYPE";


    // Vistas del Settings Activity
    private RadioGroup mRadioGroup = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mRadioGroup = (RadioGroup) findViewById(R.id.scale_type_radios);

        // Necesitamos saber qué botón es el que pulsó el usuario para dejarlo como checked para
        // que se lo encuentre así la próxima vez que vaya a la pantalla
        if(getIntent().getSerializableExtra(EXTRA_WINE_IMAGE_SCALE_TYPE).equals(ImageView.ScaleType.FIT_XY)){
            mRadioGroup.check(R.id.fit_radio);
        }
        else{
            if(getIntent().getSerializableExtra(EXTRA_WINE_IMAGE_SCALE_TYPE).equals(ImageView.ScaleType.FIT_CENTER)){
                mRadioGroup.check(R.id.center_radio);
            }
        }

        Button cancelButton = (Button) findViewById(R.id.cancel_button);
        Button saveButton = (Button) findViewById(R.id.save_button);

        // Los dos botones tienen por Listener la propia actividad.
        cancelButton.setOnClickListener(this);
        saveButton.setOnClickListener(this);
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
     * Método que se ejecutará cuando se pulse el botón de cancelar. Devolvemos un RESULT_CANCELED
     * y cerramos vista
     */
    private void cancelSettings() {
        setResult(RESULT_CANCELED);
        finish();
    }


    /**
     * Método que se ejecutará cuando se pulse el botón de guardar
     */
    private void saveSettings(){
        Intent config = new Intent();

        // Guardamos el valor de la escala según "Ajustada" o "Centrada" en nuestra clave estática
        // definida arriba EXTRA_WINE_IMAGE_SCALE_TYPE
        if(mRadioGroup.getCheckedRadioButtonId() == R.id.fit_radio){
            config.putExtra(EXTRA_WINE_IMAGE_SCALE_TYPE, ImageView.ScaleType.FIT_XY);
        }
        else{
            if(mRadioGroup.getCheckedRadioButtonId() == R.id.center_radio){
                config.putExtra(EXTRA_WINE_IMAGE_SCALE_TYPE, ImageView.ScaleType.FIT_CENTER);
            }
        }

        // Tras modificar enviamos cambios a la pantalla anterior.
        setResult(RESULT_OK, config);
        finish();
    }

}
