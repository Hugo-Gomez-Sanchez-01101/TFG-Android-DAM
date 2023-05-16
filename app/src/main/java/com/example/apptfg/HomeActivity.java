package com.example.apptfg;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.apptfg.algoritmo.Generador;
import com.example.apptfg.gestor.GestorFirebase;
import com.example.apptfg.regla.Usos;

public class HomeActivity extends FatherView {
    private TextView TextViewMinimo;
    private TextView textViewMaximo;
    private Spinner spinner;
    private SeekBar seekBarMinimo;
    private SeekBar seekBarMaximo;
    private Long[] valoresMinimoMaximo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        darFuncionalidadMenu();
        iniciarGestor();
        setup();
    }

    private void iniciarGestor() {
        GestorFirebase.getInstance();
    }

    private void setup() {
        findViewById(R.id.btnGenerarPc).setOnClickListener(V -> generarOrdenador());
        TextViewMinimo = findViewById(R.id.textViewMinimo);
        textViewMaximo = findViewById(R.id.textViewMaximo);
        seekBarMinimo = findViewById(R.id.seekBarMinimo);
        seekBarMinimo.setEnabled(false);
        seekBarMaximo = findViewById(R.id.seekBarMaxmio);
        seekBarMaximo.setEnabled(false);
        spinner = findViewById(R.id.spinnerUso);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!spinner.getSelectedItem().toString().equals("Seleccione")) {
                    cambiarValoresBarrasPorTipo();
                    seekBarMinimo.setEnabled(true);
                    seekBarMaximo.setEnabled(true);
                } else {
                    seekBarMinimo.setEnabled(false);
                    seekBarMaximo.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        seekBarMinimo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                TextViewMinimo.setText(progress + "€");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        seekBarMaximo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewMaximo.setText(progress + "€");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    private void cambiarValoresBarrasPorTipo() {
        String u = spinner.getSelectedItem().toString();
        GestorFirebase.getInstance().obtenerMaximoMinimo(obtenerUso(), new GestorFirebase.MinimoMaximosCallback() {

            @Override
            public void onValoresObtenidos(Long[] minimoMaximo) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    seekBarMinimo.setMin(minimoMaximo[0].intValue());
                    seekBarMinimo.setMax(minimoMaximo[1].intValue() - 1);
                    seekBarMaximo.setMin(minimoMaximo[0].intValue());
                    seekBarMaximo.setMax(minimoMaximo[1].intValue());
                }
                //valoresMinimoMaximo = minimoMaximo;
            }

            @Override
            public void onError(String errorMessage) {

            }
        });
    }

    private Enum<Usos> obtenerUso() {
        String itemSpinner = spinner.getSelectedItem().toString();
        Enum<Usos> u = null;
        switch (itemSpinner) {
            case "Ofimática":
                u = Usos.OFIMATICA;
                break;
            case "Gaming":
                u = Usos.GAMING;
                break;
            case "Gaming profesional":
                u = Usos.GAMING_PROFESIONAL;
                break;
            case "Diseño gráfico / Edición de video":
                u = Usos.EDICION;
                break;
        }
        return u;
    }

    private void generarOrdenador() {
        Generador generador = new Generador(Usos.GAMING, 0, 1000000);
    }

}

