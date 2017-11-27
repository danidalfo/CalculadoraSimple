package edu.upc.eetac.dsa.calculadora;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TractamentOperacio extends AppCompatActivity {

    public static final  int BORRAR = 500;
    public static final  int MODIFICAR = 501;

    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tractament_operacio);

        Bundle extras = getIntent().getExtras();

        index = extras.getInt("index");

        Button borrar = (Button)findViewById(R.id.buttonDeleteOp);
        Button editar = (Button)findViewById(R.id.buttonModOp);

        EditText resultat = (EditText)findViewById(R.id.editTextResult);
        resultat.setText(extras.getString("operacio"));

        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                intent.putExtra("index",index);
                setResult(BORRAR,intent);
                finish();
            }
        });

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                intent.putExtra("index",index);
                setResult(MODIFICAR,intent);
                finish();
            }
        });
    }
}
