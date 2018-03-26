package com.example.usuario.prueba;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Buscar extends AppCompatActivity {

    TextView tvN, tvP, tvCodBM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buscar);

        tvN = (TextView) findViewById(R.id.tvNombre);
        tvP = (TextView) findViewById(R.id.tvPrecio);
        tvCodBM = (TextView) findViewById(R.id.tvCB);

        Bundle bundle = getIntent().getExtras();

        String dato = bundle.getString("codigo");
        tvCodBM.setText(dato);

        String id = tvCodBM.getText().toString();

        if (id.equals("")) {
            Toast.makeText(getApplicationContext(), "Ingrese codigo", Toast.LENGTH_LONG).show();
        } else {
            final AyudaBD ayudabd = new AyudaBD(getApplicationContext());
            SQLiteDatabase db = ayudabd.getWritableDatabase();


            Cursor c = db.rawQuery("SELECT nombres, precio FROM Directorio WHERE id=" +id , null);
            if (c.moveToFirst()) {
                tvN.setText(c.getString(0));
                tvP.setText(c.getString(1));
            } else {
                Toast.makeText(getApplicationContext(), "No existe el producto", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Buscar.this, Main.class);
                startActivity(intent);
                finish();
            }
            db.close();

        }
    }

}


