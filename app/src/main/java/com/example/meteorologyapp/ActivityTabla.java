package com.example.meteorologyapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import com.example.meteorologyapp.Modelos.Entidades.EstadoMetereologico;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class ActivityTabla extends AppCompatActivity {
    int id = 0;
    private String fecha = "", direccionViento="";
    private float temperatura = 0,  humedad = 0, precipitacion = 0, velocidadViento = 0,
            estacion=0;
    private String fecha1="", fecha2="", fechaReal = "";
    Boolean intervalo = false;
    private String estacionATabla = "", variableATabla;
    private RecyclerView rv_tabla;
    private LinearLayoutManager linearLayoutManager;
    private AdaptadorTablaRV adaptadorTablaRV;
    private LinkedList<EstadoMetereologico> estadosMetereologicos = new LinkedList<>();
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabla);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        floatingActionButton = findViewById(R.id.floatingActionButton);
        Bundle bundle = getIntent().getExtras();

        if(bundle!=null){
            estacionATabla = bundle.getString("estacion3");
            variableATabla = bundle.getString("variable3");
            fecha1 = bundle.getString("fecha1A");
            fecha2 = bundle.getString("fecha2A");
            Toast.makeText(this, "Estacion : " + estacionATabla +
                            ", Variable: " + variableATabla, Toast.LENGTH_SHORT).show();
        }

        rv_tabla = findViewById(R.id.rv_tabla);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_tabla.setLayoutManager(linearLayoutManager);

        InputStream inputStream = getResources().openRawResource(R.raw.data);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,
                Charset.forName("UTF-8")));
        String line = "";
        int contador = 0;

        try {
            while (((line = reader.readLine()) != null) && (contador<7000)){
                String[] tokens = line.split(",");

                id = Integer.parseInt(tokens[0].replace("\"", ""));
                fecha = tokens[1].replace("\"", "");
                estacion = Float.parseFloat(tokens[2].replace("\"", ""));
                temperatura = Float.parseFloat(tokens[3].replace("\"", ""));
                humedad = Float.parseFloat(tokens[4].replace("\"", ""));
                precipitacion = Float.parseFloat(tokens[5].replace("\"", ""));
                velocidadViento = Float.parseFloat(tokens[6].replace("\"", ""));
                direccionViento = tokens[7].replace("\"", "");

                String[] tokensFecha = fecha.split(" ");
                fechaReal = tokensFecha[0];

                try {
                    SimpleDateFormat sdformat = new
                            SimpleDateFormat("yyyy-MM-dd");
                    Date dateFechaReal = sdformat.parse(fechaReal);
                    Date dateFecha1 = sdformat.parse(fecha1);
                    Date dateFecha2 = sdformat.parse(fecha2);

                    if ((dateFechaReal.after(dateFecha1) || dateFechaReal.equals(dateFecha1)) &&
                            (dateFechaReal.before(dateFecha2) || dateFechaReal.equals(dateFecha2)) ) {
                        intervalo = true;
                    }else{
                        intervalo = false;
                    }
                } catch (ParseException ex) {
                    Toast.makeText(this, "No se parseo la fecha", Toast.LENGTH_SHORT).show();
                }

                if((estacion == Float.parseFloat(estacionATabla)) && (intervalo == true)){
                    EstadoMetereologico estadoMetereologico = new EstadoMetereologico( id, fecha,
                            estacion, temperatura, humedad, precipitacion, direccionViento,
                            velocidadViento);
                    estadosMetereologicos.add(estadoMetereologico);
                }

                contador++;

            }
            Toast.makeText(this, "Se guardo la info", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "El mensaje: " + estadosMetereologicos.size(),
                    Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }

        adaptadorTablaRV = new AdaptadorTablaRV(this, estadosMetereologicos, variableATabla);
        rv_tabla.setAdapter(adaptadorTablaRV);


         floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityTabla.this, Activity2.class);
                intent.putExtra("estacion", estacionATabla);
                startActivity(intent);
            }
        });
    }

}