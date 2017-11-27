package edu.upc.eetac.dsa.calculadora;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class LlistaOperacions extends AppCompatActivity {

    ArrayList<String> operciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llista_operacions);

        Button tencar = (Button)findViewById(R.id.buttonClose);
        Button borrarHistorial = (Button)findViewById(R.id.buttonDeleteHist);

        tencar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getIntent().putStringArrayListExtra("historial",operciones);
                setResult(RESULT_OK,getIntent());
                finish();
            }
        });

        borrarHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LlistaOperacions.this,ConfirmarEsborrar.class);
                startActivityForResult(intent,103);
            }
        });

        operciones = getIntent().getStringArrayListExtra("historial");
        ListView llista = (ListView)findViewById(R.id.LlistaOperacions);
        llista.setAdapter( new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,operciones));

        llista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(LlistaOperacions.this,TractamentOperacio.class);
                intent.putExtra("index",position);
                intent.putExtra("operacio",operciones.get(position));
                startActivityForResult(intent,102);
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        getIntent().putStringArrayListExtra("historial",operciones);
        setResult(RESULT_OK,getIntent());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==102&&resultCode==TractamentOperacio.BORRAR)
        {
            Bundle bundle = data.getExtras();
            int index = bundle.getInt("index");
            operciones.remove(index);
            ListView llista = (ListView)findViewById(R.id.LlistaOperacions);
            llista.setAdapter( new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,operciones));
        }

        if(requestCode==102&&resultCode==TractamentOperacio.MODIFICAR)
        {
            Bundle bundle = data.getExtras();
            int index = bundle.getInt("index");
            getIntent().putExtra("index",index);
            setResult(TractamentOperacio.MODIFICAR,getIntent());
            finish();
        }

        if(requestCode==103&&resultCode==ConfirmarEsborrar.BORRAR)
        {
            operciones = new ArrayList<String>();
            getIntent().putStringArrayListExtra("historial",operciones);
            setResult(RESULT_OK,getIntent());
            finish();
        }

    }

}
