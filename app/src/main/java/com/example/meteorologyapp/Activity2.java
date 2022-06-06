package com.example.meteorologyapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.meteorologyapp.Modelos.Entidades.EstadoMetereologico;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;

public class Activity2 extends AppCompatActivity {

    Button b_aceptar2;
    private String estacion, fechaTime="", fecha="";
    RadioButton rb_humedad, rb_precipitacion, rb_velocidad, rb_temperatura, rb_direccion;
    Spinner s_primera_fecha, s_segunda_fecha;
    private ArrayAdapter<CharSequence> adapterFecha;
    private LinkedList<String> cadenasAdaptador = new LinkedList<>();
    private LinkedList<String> cadenasAdaptador2 = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        s_primera_fecha = findViewById(R.id.s_primera_fecha);
        s_segunda_fecha = findViewById(R.id.s_segunda_fecha);
        b_aceptar2 = findViewById(R.id.b_aceptar2);
        rb_direccion = findViewById(R.id.rb_direccion);
        rb_humedad = findViewById(R.id.rb_humedad);
        rb_precipitacion = findViewById(R.id.rb_precipitacion);
        rb_velocidad = findViewById(R.id.rb_velocidad);
        rb_temperatura = findViewById(R.id.rb_temperatura);
        rb_temperatura.setChecked(true);

        InputStream inputStream = getResources().openRawResource(R.raw.data);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,
                Charset.forName("UTF-8")));

        String line = "";
        int contador = 0;

        cadenasAdaptador.add("Seleccionar");

        try {
            while (((line = reader.readLine()) != null) && (contador<7000)){

                String[] tokens = line.split(",");
                fechaTime = tokens[1].replace("\"", "");
                String[] tokensFecha = fechaTime.split(" ");
                fecha = tokensFecha[0];

                for (int i=0 ; i < cadenasAdaptador.size(); i++){
                    if (!cadenasAdaptador.contains(fecha)){
                        cadenasAdaptador.add(fecha);
                    }
                }
                contador++;
            }
            Toast.makeText(this, "Se guardo la info", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }


        adapterFecha = new ArrayAdapter(this, android.R.layout.simple_spinner_item,
                cadenasAdaptador);
        adapterFecha.setDropDownViewResource(android.R.layout.simple_spinner_item);
        s_primera_fecha.setAdapter(adapterFecha);
        s_segunda_fecha.setAdapter(adapterFecha);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            estacion = bundle.getString("estacion");
        }

        b_aceptar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!(s_primera_fecha.getSelectedItem().toString().equals("Seleccionar") ||
                        s_segunda_fecha.getSelectedItem().toString().equals("Seleccionar"))){

                    Intent intent = new Intent(Activity2.this, Activity3.class);
                    if(rb_humedad.isChecked()){
                        intent.putExtra("variable", "humedad");
                    }else if(rb_precipitacion.isChecked()){
                        intent.putExtra("variable", "precipitacion");
                    }else if(rb_direccion.isChecked()){
                        intent.putExtra("variable", "direccion");
                    }else if(rb_velocidad.isChecked()){
                        intent.putExtra("variable", "velocidad");
                    }else{
                        intent.putExtra("variable", "temperatura");
                    }
                    intent.putExtra("estacion2", estacion);
                    intent.putExtra("fecha1",  s_primera_fecha.getSelectedItem().toString());
                    intent.putExtra("fecha2",  s_segunda_fecha.getSelectedItem().toString());

                    startActivity(intent);
                }else{
                    Toast.makeText(Activity2.this, "Ingrese datos en las fechas",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}