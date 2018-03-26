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

import org.w3c.dom.Text;

/**
 * Created by Usuario on 25/3/2018.
 */

public class Eliminar extends AppCompatActivity {

    TextView tvC, tvN;
    Button btnE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eliminar);

        tvC = (TextView) findViewById(R.id.tvCEE);
        tvN = (TextView) findViewById(R.id.tvNE);
        btnE = (Button) findViewById(R.id.btnEE);


        Bundle bundle = getIntent().getExtras();

        String dato = bundle.getString("codigo");
        tvC.setText(dato);

        final AyudaBD ayudabd = new AyudaBD(getApplicationContext());
        SQLiteDatabase db = ayudabd.getWritableDatabase();

        String id = tvC.getText().toString();

        Cursor c = db.rawQuery("SELECT nombres, precio FROM Directorio WHERE id=" + id, null);
        if (c.moveToFirst()) {
            tvN.setText(c.getString(0));

            btnE.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    String id = tvC.getText().toString();
                    if (id.equals("")) {
                        Toast.makeText(getApplicationContext(), "Ingrese código", Toast.LENGTH_LONG).show();
                    } else {
                        final AyudaBD ayudabd = new AyudaBD(getApplicationContext());
                        SQLiteDatabase db = ayudabd.getWritableDatabase();

                        int delete = db.delete("Directorio", "id=" + id, null);

                        if (delete == 1) {
                            Toast.makeText(getApplicationContext(), "Producto Eliminado", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "El producto no existe", Toast.LENGTH_LONG).show();
                        }
                        db.close();

                        Intent intent = new Intent(Eliminar.this, Main.class);
                        startActivity(intent);
                        finish();
                    }

                }


            });
        }
    }
}
