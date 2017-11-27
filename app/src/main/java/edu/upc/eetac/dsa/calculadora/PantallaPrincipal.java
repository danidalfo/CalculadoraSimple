package edu.upc.eetac.dsa.calculadora;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class PantallaPrincipal extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ArrayList<String> operaciones;
    int operacion;

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        operacion = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        operacion = -1;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);

        operacion = -1;

        final EditText numero1 = (EditText)findViewById(R.id.editText2);
        final EditText numero2 = (EditText)findViewById(R.id.editText3);

        operaciones = new ArrayList<String>();

        Button igual = (Button)findViewById(R.id.btneq);
        Button borrar = (Button)findViewById(R.id.btnc);
        Button historial = (Button)findViewById(R.id.btnhistorial);

        final Spinner operacio = (Spinner)findViewById(R.id.spinnerOp);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.operacions, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        operacio.setAdapter(adapter);

        operacio.setOnItemSelectedListener(this);

        igual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numero1int;
                int numero2int;
                try {
                    if(numero1.getText().toString().equals("")||numero2.getText().toString().equals(""))
                    {
                        Toast error = Toast.makeText(getApplicationContext(),"Indica els valors",Toast.LENGTH_LONG);
                    }

                    numero1int = Integer.parseInt(numero1.getText().toString());
                    numero2int = Integer.parseInt(numero2.getText().toString());

                    EditText resultat = (EditText)findViewById(R.id.editText4);

                    switch (operacion)
                    {
                        case -1:
                            Toast error = Toast.makeText(getApplicationContext(),"Seleiona una operacio",Toast.LENGTH_LONG);
                            break;
                        case 0:
                            resultat.setText(String.valueOf(numero1int+numero2int));
                            operaciones.add(numero1int+" + "+numero2int+" = "+(numero1int+numero2int));
                            break;
                        case 1:
                            resultat.setText(String.valueOf(numero1int-numero2int));
                            operaciones.add(numero1int+" - "+numero2int+" = "+(numero1int-numero2int));
                            break;
                        case 2:
                            resultat.setText(String.valueOf(numero1int/numero2int));
                            operaciones.add(numero1int+" / "+numero2int+" = "+(numero1int/numero2int));
                            break;
                        case 3:
                            resultat.setText(String.valueOf(numero1int*numero2int));
                            operaciones.add(numero1int+" * "+numero2int+" = "+(numero1int*numero2int));
                            break;
                    }

                }
                catch (NumberFormatException e)
                {
                    Toast error = Toast.makeText(getApplicationContext(),"Els valors han de ser valors nombres enters",Toast.LENGTH_LONG);
                }

            }
        });

        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText resultat = (EditText)findViewById(R.id.editText4);
                numero1.setText("0");
                numero2.setText("0");
                resultat.setText("0");
            }
        });

        historial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PantallaPrincipal.this,LlistaOperacions.class);
                intent.putStringArrayListExtra("historial",operaciones);
                startActivityForResult(intent,100);
            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100)
        {
            operaciones = data.getExtras().getStringArrayList("historial");
        }
        if(requestCode==100&&resultCode==TractamentOperacio.MODIFICAR)
        {
            int index = data.getExtras().getInt("index");
            String[] trozos = operaciones.get(index).split(" ");
            EditText numero1 = (EditText)findViewById(R.id.editText2);
            EditText numero2 = (EditText)findViewById(R.id.editText3);
            EditText resultat = (EditText)findViewById(R.id.editText4);
            numero1.setText(trozos[0]);
            numero2.setText(trozos[2]);
            resultat.setText(trozos[4]);
        }
    }
}



