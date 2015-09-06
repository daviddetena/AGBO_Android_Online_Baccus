package com.daviddetena.baccus.controller.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.daviddetena.baccus.R;
import com.daviddetena.baccus.controller.activity.SettingsActivity;
import com.daviddetena.baccus.controller.activity.WebActivity;
import com.daviddetena.baccus.model.Wine;

/**
 * Created by daviddetena on 06/09/15.
 */
public class WineFragment extends Fragment{
    // Constante con la que recibiremos argumento al invocar este Activity desde un Intent
    public static final String ARG_WINE = "com.daviddetena.baccus.controller.fragment.WineFragment.ARG_WINE";


    private static final String TAG = WineFragment.class.getSimpleName();

    // Código para indicar que la pantalla de la que procede es la de Settings en startActivityForResult
    private static final int SETTINGS_REQUEST = 1;

    // Constante que utilizaremos como clave para guardar la escala actual de la imagen y poderlo
    // guardar al ejecutar onSaveInstanceState()
    private static final String STATE_IMAGE_SCALE_TYPE = "com.daviddetena.baccus.controller.fragment.WineFragment.STATE_IMAGE_SCALE_TYPE";

    // Modelo
    private Wine mWine = null;

    // Vistas
    private ImageView mWineImage = null;
    private TextView mWineNameText = null;
    private TextView mWineTypeText = null;
    private TextView mWineOriginText = null;
    private RatingBar mWineRatingBar = null;
    private TextView mWineCompanyText = null;
    private TextView mWineNotesText = null;
    private ViewGroup mWineGrapesContainer = null;
    private ImageButton mGoToWebButton = null;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        // Utilizamos el fichero fragment_wine.xml como contexto para la vista raíz
        View root = inflater.inflate(R.layout.fragment_wine, container, false);

        // Recogemos el modelo que nos pasa WineryActivity
        mWine = (Wine) getArguments().getSerializable(ARG_WINE);

        // Accedemos a las vistas desde el controlador, utilizando sus referencias en el .xml, para
        // asociarlas a nuestras variables
        mWineImage = (ImageView) root.findViewById(R.id.wine_image);
        mWineNameText = (TextView) root.findViewById(R.id.wine_name);
        mWineTypeText = (TextView) root.findViewById(R.id.wine_type);
        mWineOriginText = (TextView) root.findViewById(R.id.wine_origin);
        mWineRatingBar = (RatingBar) root.findViewById(R.id.wine_rating);
        mWineCompanyText = (TextView) root.findViewById(R.id.wine_company);
        mWineNotesText = (TextView) root.findViewById(R.id.wine_notes);
        mWineGrapesContainer = (ViewGroup) root.findViewById(R.id.grapes_container);
        mGoToWebButton = (ImageButton) root.findViewById(R.id.go_to_web_button);

        // Sincronizamos modelo y vista
        mWineImage.setImageResource(mWine.getPhoto());
        mWineNameText.setText(mWine.getName());
        mWineTypeText.setText(mWine.getType());
        mWineOriginText.setText(mWine.getOrigin());
        mWineRatingBar.setRating(mWine.getRating());
        mWineCompanyText.setText(mWine.getCompanyName());
        mWineNotesText.setText(mWine.getNotes());

        // Creamos tantos TextViews como Uvas tenga el vino
        for(int i=0; i<mWine.getGrapeCount(); i++){
            TextView grapeText = new TextView(getActivity());
            grapeText.setText(mWine.getGrape(i));

            // Queremos que la uva ocupe el ancho completo en su contenedor
            grapeText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            // Añadimos la vista de la uva que hemos creado a su contenedor
            mWineGrapesContainer.addView(grapeText);
        }

        // Configuramos botones

        // Clase anónima para suscribirme al evento del click en el botón. Implementamos los métodos
        // de dicha interfaz. En este caso, sólo el onClick, donde cargaremos nuestra WebView
        // Activity mediante un intent.
        mGoToWebButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Todos los fragment tienen un método getActivity(). Lo utilizamos para pasárselo
                // al intent necesario
                Intent webIntent = new Intent(getActivity(), WebActivity.class);

                // Pasamos nuestro modelo Wine al Activity de WebActivity
                webIntent.putExtra(WebActivity.EXTRA_WINE, mWine);
                startActivity(webIntent);
            }
        });

        // Configuramos cómo se ve la imagen (cargamos lo que haya guardado en la clave definida
        // STATE_IMAGE_SCALE_TYPE
        if(savedInstanceState != null && savedInstanceState.containsKey(STATE_IMAGE_SCALE_TYPE)){
            mWineImage.setScaleType((ImageView.ScaleType) savedInstanceState.getSerializable(STATE_IMAGE_SCALE_TYPE));
        }

        return root;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }


    /**
     * @param item The menu item that was selected.
     * @return boolean Return false to allow normal menu processing to
     * proceed, true to consume it here.
     * @see #onCreateOptionsMenu
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        // Se ha pulsado el botón de Ajustes. Cargamos la pantalla de Ajustes mediante el Intent
        if(item.getItemId() == R.id.action_settings){

            // Definimos el parámetro EXTRA_WINE_IMAGE_SCALE_TYPE a la escala que tenga de forma
            // inicial la imagen. Como queremos recoger información de la pantalla a la que llamamos,
            // lo hacemos con startActivityForResult()
            Intent settingsIntent = new Intent(getActivity(), SettingsActivity.class);
            settingsIntent.putExtra(SettingsActivity.EXTRA_WINE_IMAGE_SCALE_TYPE, mWineImage.getScaleType());
            startActivityForResult(settingsIntent, SETTINGS_REQUEST);

            return true;
        }
        else{
            // Han pulsado otro
            return false;
        }
    }

    /**
     * Método que se ejecuta al volver de la pantalla indicada en el startActivityForResult
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Comprobamos lo seleccionado por el usuario y que se ha seleccionado algo
        if(requestCode == SETTINGS_REQUEST && resultCode == Activity.RESULT_OK){

            // Cambiamos la escala de la imagen a la que recogemos de la que guardó SettingsActivity
            ImageView.ScaleType scaleType = (ImageView.ScaleType) data.getSerializableExtra(SettingsActivity.EXTRA_WINE_IMAGE_SCALE_TYPE);
            mWineImage.setScaleType(scaleType);
        }
    }

    /**
     * Guardamos el estado actual de la escala de la imagen
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(STATE_IMAGE_SCALE_TYPE, mWineImage.getScaleType());
    }
}
