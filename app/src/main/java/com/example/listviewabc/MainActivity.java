package com.example.listviewabc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int posicionSeleccionada = -1;

    EditText etDato;
    ListView lvDatos;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etDato = findViewById(R.id.etDato);
        lvDatos = findViewById(R.id.lvDatos);

        list = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        lvDatos.setAdapter(adapter);

        lvDatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                posicionSeleccionada = position;
                etDato.setText(list.get(position));
            }
        });
    }

    public void ocAlta(View view){
        if (posicionSeleccionada == -1) {
            String dato = etDato.getText().toString().trim(); // trim es limpiar a izq der de espacios
            if (dato.length() > 0) {
                list.add(dato);
                ocNew(view);
                lvDatos.setAdapter(adapter);
            } else {
                Toast.makeText(getApplicationContext(), "ERROR: Campo vacío", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(getApplicationContext(), "ERROR: Esta en modo baja o cambio", Toast.LENGTH_SHORT).show();
        }
    }

    public void ocBaja(View view){
        if(posicionSeleccionada >= 0){
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Confirmacion");
            builder.setMessage("Esta seguro de eliminar el elemento?");
            builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    list.remove(posicionSeleccionada);
                    ocNew(view);
                    lvDatos.setAdapter(adapter);
                }
            });
            builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        else {
            Toast.makeText(getApplicationContext(), "ERROR: Está en modo alta", Toast.LENGTH_SHORT).show();
        }
    }

    public void ocCambio(View view){

        if(posicionSeleccionada >= 0){
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Confirmacion");
            builder.setMessage("Esta seguro de cambiar el elemento?");
            builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    list.remove(posicionSeleccionada);
                    list.add(posicionSeleccionada, etDato.getText().toString());
                    ocNew(view);
                    lvDatos.setAdapter(adapter);
                }
            });
            builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        else {
            Toast.makeText(getApplicationContext(), "ERROR: Está en modo alta", Toast.LENGTH_SHORT).show();
        }
    }

    public void ocNew(View view){
        etDato.setText(null);
        posicionSeleccionada = -1;
    }
}