package com.example.jorge.editortexto;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;


public class Principal extends Activity {
    private EditText etTexto;
    private Uri urlArchivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_principal);
        etTexto = (EditText)findViewById(R.id.etTexto);
        urlArchivo = getIntent().getData();
        LeerArchivo(urlArchivo);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void LeerArchivo(Uri ruta) {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        String linea, texto="";
        try {
            archivo = new File(ruta.getPath());
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            while ((linea = br.readLine()) != null) {
                texto += linea + "\n";
            }
            etTexto.setText(texto);
        } catch (IOException e) {
            Toast.makeText(this, "No se ha podido leer", Toast.LENGTH_SHORT).show();
        }
    }


    public void GuardarArchivo(View v){
        try {
            File f = new File(urlArchivo.getPath());
            OutputStreamWriter out= new OutputStreamWriter(new FileOutputStream(f));
            out.write(etTexto.getText().toString());
            out.close();
            Toast.makeText(this, "Guardado correctamente", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "No se ha guardado", Toast.LENGTH_SHORT).show();
        }
    }
}
