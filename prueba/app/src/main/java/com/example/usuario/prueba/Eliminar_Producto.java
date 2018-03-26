package com.example.usuario.prueba;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by Usuario on 25/3/2018.
 */

public class Eliminar_Producto extends AppCompatActivity {

    EditText etC;
    Button btnEscE;
    public ZXingScannerView vistaScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eliminar_producto);

        etC = (EditText) findViewById(R.id.etCodigoEM);
        btnEscE = (Button) findViewById(R.id.btnEscEl);

        btnEscE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vistaScan = new ZXingScannerView(Eliminar_Producto.this);
                vistaScan.setResultHandler(new ZxingScan());
                setContentView(vistaScan);
                vistaScan.startCamera();
            }
        });
    }

    class ZxingScan implements ZXingScannerView.ResultHandler {

        @Override
        public void handleResult(Result result) {
            String dato = result.getText();
            setContentView(R.layout.activity_main);
            vistaScan.stopCamera();
            etC = (EditText) findViewById(R.id.etIdG);
            etC.setText(dato);
            String aux = etC.getText().toString();

            if (!aux.equals("")) {

                Intent intent = new Intent(Eliminar_Producto.this, Eliminar.class);
                intent.putExtra("codigo", etC.getText().toString());
                startActivity(intent);
                finish();

            }
        }
    }
}

