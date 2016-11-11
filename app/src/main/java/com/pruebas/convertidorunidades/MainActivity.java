package com.pruebas.convertidorunidades;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Spinner spinner1;
    Spinner spinner2;
    EditText txtMetros;
    TextView resultado1;
    EditText txtCentigrados1;
    EditText txtCentigrados2;
    EditText txtFahrenheit;

    final String unidadMts="mts";
    final String unidadmillasTerrestres="mi";
    final String unidadkilometros="km";
    final String unidadPulgada="in";
    final String unidadCentigrados="C";
    final String unidadFahrenheit="F";
    final String unidadKelvin="K";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        spinner1 = (Spinner) findViewById(R.id.spinner1) ;
        txtMetros = (EditText) findViewById(R.id.txtMetros);
        resultado1 = (TextView) findViewById(R.id.lblResultado1);
        txtCentigrados1 = (EditText) findViewById(R.id.txtCentigrados1);
        txtCentigrados2 = (EditText) findViewById(R.id.txtCentigrados2);
        txtFahrenheit = (EditText) findViewById(R.id.txtFahrenheit);

        Button btnFahrenheit = (Button) findViewById(R.id.btnFahrenheit);
        btnFahrenheit.setOnClickListener(this);

        Button btnKelvin = (Button) findViewById(R.id.btnKelvin);
        btnKelvin.setOnClickListener(this);

        Button btnCentigrados = (Button) findViewById(R.id.btnCentigrados);
        btnCentigrados.setOnClickListener(this);


        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                resultado1.setText("");
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

    }


    public void calcularMetros(View view) {
        int valSpinner1 = (int) spinner1.getSelectedItemId();
        String valMts = txtMetros.getText().toString();
       if(!validarEntero(valMts))
       {
           Snackbar.make(view, "Debe ingresar valor metros", Snackbar.LENGTH_LONG)
                   .setAction("Action", null).show();
           return;
       }

        switch (valSpinner1){
            case 1:
                resultado1.setText( ConvertirUnidad(unidadMts,unidadmillasTerrestres,Integer.valueOf(valMts)));
                break;
            case 2:
                resultado1.setText( ConvertirUnidad(unidadMts,unidadkilometros,Integer.valueOf(valMts)));
                break;
            case 3:
                resultado1.setText( ConvertirUnidad(unidadMts,unidadPulgada,Integer.valueOf(valMts)));
                break;
            case 0:
                resultado1.setText("");
        }


    }

    private boolean validarEntero(String cadena){
        if(cadena.isEmpty()) return false;

        if(cadena.matches("\\d+")){
            return  true;
        }
        return  false;
    }

    private String ConvertirUnidad( String unidadInicial, String unidadFinal, int cantidad){

        switch (unidadInicial)
        {
            case unidadMts:
                if(unidadFinal.compareTo(unidadmillasTerrestres)==0){
                    return cantidad * 0.000621371 + " Millas";
                }else if(unidadFinal.compareTo(unidadkilometros)==0){
                    double d1 = (double) cantidad / (double)1000;
                    return d1  + " Kilometros";
                }else if(unidadFinal.compareTo(unidadPulgada)==0){
                    return cantidad * 39.370 + " Pulgadas";
                }
            break;
            case unidadCentigrados:
                if(unidadFinal.compareTo(unidadFahrenheit)==0){
                    return (((double) cantidad * 9/5)  +32)+ " Grados Fahrenheit";
                }else if(unidadFinal.compareTo(unidadKelvin)==0){
                    return (cantidad + 273.15) + " Grados Kelvin";
                }
            break;
            case unidadFahrenheit:
                if(unidadFinal.compareTo(unidadCentigrados)==0){
                 return ( ((double) cantidad - 32) * ((double)5/9) ) + " Grados Centigrados";
                }
            break;

        }
        return "";
    }


    @Override
    public void onClick(View view) {
        String val;
        String Mensaje;
        switch(view.getId()) {

            case R.id.btnFahrenheit:
                 val = txtCentigrados1.getText().toString();
                if(!validarEntero(val))
                {
                    ImprimirMensaje(view,"Debe ingresar valor centigrados");
                    return;
                }

                Mensaje= ConvertirUnidad(unidadCentigrados,unidadFahrenheit,Integer.valueOf(val));
                ImprimirMensaje(view,Mensaje);
                break;
            case R.id.btnKelvin:
                 val = txtCentigrados2.getText().toString();
                if(!validarEntero(val))
                {
                    ImprimirMensaje(view,"Debe ingresar valor centigrados");
                    return;
                }
                Mensaje=ConvertirUnidad(unidadCentigrados,unidadKelvin,Integer.valueOf(val));
                ImprimirMensaje(view,Mensaje);
                break;
            case R.id.btnCentigrados:
                 val = txtFahrenheit.getText().toString();
                if(!validarEntero(val))
                {
                    ImprimirMensaje(view,"Debe ingresar valor Fahrenheit");
                    return;
                }
                Mensaje=ConvertirUnidad(unidadFahrenheit,unidadCentigrados,Integer.valueOf(val));
                ImprimirMensaje(view,Mensaje);
                break;
        }
    }

    private void ImprimirMensaje(View view, String mensaje){
        Snackbar.make(view, mensaje, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
