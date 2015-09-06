package com.daviddetena.baccus.controller.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.daviddetena.baccus.R;
import com.daviddetena.baccus.model.Wine;

/**
 * Created by daviddetena on 06/09/15.
 */
public class WebFragment extends Fragment{
    // Constante con la hacemos referencia al modelo (Wine) que viene de la pantalla anterior y que
    // recogeremos mediante getArguments().getSerializableExtra()
    // Con Activity se utilizaba getIntent().
    public static final String ARG_WINE = "com.daviddetena.baccus.controller.fragment.WebFragment.ARG_WINE";

    // Constante del diccionario saveInstanceState() con la que guardamos la última url cargada
    // por el browser
    private static final String STATE_URL = "url";

    // Modelo
    private Wine mWine = null;

    // Vistas: navegador y barra de progreso
    private WebView mBrowser = null;
    private ProgressBar mLoading = null;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        // Utilizamos el fichero fragment_web.xml para generar toda la estructura de vistas en esta
        // View raíz
        View root = inflater.inflate(R.layout.fragment_web, container, false);

        // Accedemos al modelo que se nos pasa a través del Intent desde la pantalla inicial
        // de WineActivity
        mWine = (Wine) getArguments().getSerializable(ARG_WINE);


        // Asociamos vista y controlador, utilizando el root para buscar por id
        mBrowser = (WebView) root.findViewById(R.id.browser);
        mLoading = (ProgressBar) root.findViewById(R.id.loading);

        // Configuramos vistas

        // Indicamos que nuestro navegador estará integrado en una webView dentro de nuestra app,
        // y no que se abra en el Navegador por defecto de Android.
        // Para ello sobreescrimos métodos del WebViewClient a través de la clase anónima que
        // creamos y que utilizaremos para dar feedback al usuario con la progressbar
        mBrowser.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mLoading.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                // Hacemos invisible y que desaparezca
                mLoading.setVisibility(View.GONE);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                mLoading.setVisibility(View.GONE);
            }
        });

        mBrowser.getSettings().setJavaScriptEnabled(true);
        mBrowser.getSettings().setBuiltInZoomControls(true);

        // Cargo la página web del vino (), o la última que hubiera guardada
        if(savedInstanceState==null || !savedInstanceState.containsKey(STATE_URL)){
            mBrowser.loadUrl(mWine.getCompanyWeb());
        }
        else {
            mBrowser.loadUrl(savedInstanceState.getString(STATE_URL));
        }

        return root;
    }

    /**
     * Guardamos en nuestra constante la última url visitada antes de que el objeto se destruya
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_URL, mBrowser.getUrl());
    }

    /**
     * Sobreescribimos método que se encarga de añadir nuestro menú definido en un .xml a nuestro
     * fragment
     * @param menu
     * @param inflater
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_web, menu);
    }


    /**
     * Indicamos qué hacer al pulsar el item del menú indicado por parámetro
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        // Comprobamos si el item seleccionado es el "reload"
        if(item.getItemId() == R.id.menu_reload){
            mBrowser.reload();
            return true;
        }
        return false;
    }
}
