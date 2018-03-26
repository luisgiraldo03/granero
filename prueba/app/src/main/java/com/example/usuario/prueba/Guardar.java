package com.example.usuario.prueba;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;



public class Guardar extends AppCompatActivity {

    String codigo, nombre, precio, auxCod;
    Button btnGuardar;
    EditText etNombres, etPrecio;
    TextView tvCod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guardar);


        tvCod = (TextView) findViewById(R.id.tvCodigo);
        btnGuardar = (Button) findViewById(R.id.btnGmodulo);
        etNombres = (EditText) findViewById(R.id.etNG);
        etPrecio = (EditText) findViewById(R.id.etPG);

        Bundle bundle = getIntent().getExtras();

        String dato = bundle.getString("codigo");
        tvCod.setText(dato);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codigo = tvCod.getText().toString();
                nombre = etNombres.getText().toString();
                precio = etPrecio.getText().toString();

                if (codigo.matches("") || nombre.matches("") || precio.matches("")) {
                    Toast.makeText(getApplicationContext(), "Ingrese todos los campos", Toast.LENGTH_LONG).show();
                } else {

                    final AyudaBD ayudabd = new AyudaBD(getApplicationContext());
                    SQLiteDatabase db = ayudabd.getWritableDatabase();

                    Cursor registro = db.rawQuery("SELECT * FROM Directorio WHERE id=" + codigo, null);
                    if (registro.moveToFirst()) {
                        Toast.makeText(getApplicationContext(), "Ya existe este producto", Toast.LENGTH_LONG).show();
                        db.close();
                    } else {
                        ContentValues valores = new ContentValues();
                        valores.put("id", codigo);
                        valores.put("nombres", nombre);
                        valores.put("precio", precio);


                        db.insert("Directorio", null, valores);
                        db.close();
                        Toast.makeText(getApplicationContext(), "Se guardo el producto: " + nombre, Toast.LENGTH_LONG).show();
                        Intent go = new Intent(Guardar.this, Main.class);
                        startActivity(go);
                        finish();

                    }
                }
            }
        });
    }
}