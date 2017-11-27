package edu.upc.eetac.dsa.calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ConfirmarEsborrar extends AppCompatActivity {

    public static final int BORRAR = 505;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_esborrar);

        Button si = (Button)findViewById(R.id.buttonSi);
        Button no = (Button)findViewById(R.id.buttonNo);

        si.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(BORRAR);
                finish();
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
