package com.example.usuario.prueba;

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

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class Buscar_Producto extends AppCompatActivity {


    Button btnEscBuscar;
    EditText etCodigoBuscar;
    private ZXingScannerView vistaScan;
    String cod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buscar_producto);

        btnEscBuscar = (Button) findViewById(R.id.btnEscanaerB);
        etCodigoBuscar = (EditText) findViewById(R.id.etCodigoB);



        btnEscBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vistaScan = new ZXingScannerView(Buscar_Producto.this);
                vistaScan.setResultHandler(new zxingScan());
                setContentView(vistaScan);
                vistaScan.startCamera();
            }
        });
    }

    class zxingScan implements ZXingScannerView.ResultHandler {

        @Override
        public void handleResult(Result result) {

            String codigo = result.getText();
            setContentView(R.layout.buscar_producto);
            vistaScan.stopCamera();
            etCodigoBuscar = (EditText) findViewById(R.id.etCodigoB);
            etCodigoBuscar.setText(codigo);
            cod = etCodigoBuscar.getText().toString();

            if (!cod.equals("")) {

                Intent intent = new Intent(Buscar_Producto.this, Buscar.class);
                intent.putExtra("codigo", etCodigoBuscar.getText().toString());
                startActivity(intent);
                finish();

            }
        }
    }

}




       /* btnEscBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cod = etCodigoBuscar.getText().toString();
                if (cod.equals("")) {
                    Toast.makeText(getApplicationContext(), "Ingrese codigo", Toast.LENGTH_LONG).show();
                } else {
                    final AyudaBD ayudabd = new AyudaBD(getApplicationContext());
                    SQLiteDatabase db = ayudabd.getWritableDatabase();
                    Cursor c = db.rawQuery("SELECT nombres, precio FROM Directorio WHERE id=" + cod, null);
                    if (c.moveToFirst()) {
                        tvNombre.setText(c.getString(0));
                        tvPrecio.setText(c.getString(1));

                    } else {
                        Toast.makeText(getApplicationContext(), "No existe el producto", Toast.LENGTH_LONG).show();
                    }
                    db.close();
                }
            }

        });*/


