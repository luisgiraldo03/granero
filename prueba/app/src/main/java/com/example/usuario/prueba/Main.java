package com.example.usuario.prueba;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class Main extends AppCompatActivity {

    Button btnG, btnC, btnE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        btnC = (Button) findViewById(R.id.btnConsultarP);
        btnG = (Button)findViewById(R.id.btnGuardarP);
        btnE = (Button)findViewById(R.id.btnElMain);

        btnG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go = new Intent(Main.this, MainActivity.class);
                startActivity(go);

            }
        });

        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go = new Intent(Main.this, Buscar_Producto.class);
                startActivity(go);


            }
        });

        btnE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go = new Intent(Main.this, Eliminar_Producto.class);
                startActivity(go);

            }
        });


    }
}