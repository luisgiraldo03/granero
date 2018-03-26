package com.example.usuario.prueba;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;



public class MainActivity extends AppCompatActivity {
    Button btnEscanear;
    EditText etId;
    public ZXingScannerView vistaScan;
    int p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEscanear = (Button) findViewById(R.id.btnEscG);
        etId = (EditText) findViewById(R.id.etIdG);


        btnEscanear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vistaScan = new ZXingScannerView(MainActivity.this);
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
            etId = (EditText) findViewById(R.id.etIdG);
            etId.setText(dato);
            String aux = etId.getText().toString();

            if (!aux.equals("")) {

                Intent intent = new Intent(MainActivity.this, Guardar.class);
                intent.putExtra("codigo", etId.getText().toString());
                startActivity(intent);
                finish();

            }
        }
    }


}



