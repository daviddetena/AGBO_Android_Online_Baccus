package com.daviddetena.baccus.controller;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.daviddetena.baccus.R;
import com.daviddetena.baccus.model.Wine;

/**
 * Created by daviddetena on 05/09/15.
 */
public class WebActivity extends Activity {

    // Modelo
    private Wine mWine = null;

    // Vistas: navegador y barra de progreso
    private WebView mBrowser = null;
    private ProgressBar mLoading = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        // Esta clase tendrá por contenido la actividad de la web del vino definida en su .xml
        setContentView(R.layout.activity_web);

        // Creamos el modelo
        mWine = new Wine("Bembibre",
                "Tinto",
                R.drawable.vegaval,
                "Dominio de Tares",
                "http://www.dominiodetares.com/portfolio/bembibre/",
                "Este vino muestra toda la complejidad y la elegancia de la variedad Mencía. En fase visual luce un color rojo picota muy cubierto con tonalidades violáceas en el menisco. En nariz aparecen recuerdos frutales muy intensos de frutas rojas (frambuesa, cereza) y una potente ciruela negra, así como tonos florales de la gama de las rosas y violetas, vegetales muy elegantes y complementarios, hojarasca verde, tabaco y maderas aromáticas (sándalo) que le brindan un toque ciertamente perfumado.",
                "El Bierzo",
                5);

        mWine.addGrape("Mencía");

        // Asociamos vista y controlador
        mBrowser = (WebView) findViewById(R.id.browser);
        mLoading = (ProgressBar) findViewById(R.id.loading);

        // Configuramos vistas

        // Indicamos que nuestro navegador estará integrado en una webView dentro de nuestra app,
        // y no que se abra en el Navegador por defecto de Android.
        // Para ello sobreescrimos métodos del WebViewClient a través de la clase anónima que
        // creamos y que utilizaremos para dar feedback al usuario con la progressbar
        mBrowser.setWebViewClient(new WebViewClient(){

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

        // Cargo la página web del vino
        mBrowser.loadUrl(mWine.getCompanyWeb());

    }
}
