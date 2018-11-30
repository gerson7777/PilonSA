package agricolapilon.pilonsa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import agricolapilon.pilonsa.model.Pilon;


public class PrincipalActivity  extends AppCompatActivity {
    private List<Pilon> listPilon = new ArrayList<Pilon>();
    ArrayAdapter<Pilon> arrayAdapterPilon;
    EditText lineP, proyP,fechaSiembP,granoSemP,fechaTransP,ubicP,fechapolinizP,plantasTransplaP,plantasPolinizadasP,fechaCosechaP, mazorcasCosechadaP,comentP;
    ListView listV_pilones;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Pilon pilonSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        lineP = (EditText) findViewById(R.id.txt_linea);
        proyP = (EditText)findViewById(R.id.txt_Proyecto);
        fechaSiembP = (EditText) findViewById(R.id.txt_FechaSiem);
        granoSemP = (EditText) findViewById(R.id.txt_granosSemb);
        fechaTransP = (EditText) findViewById(R.id.txt_fechaTranspla);
        ubicP =(EditText)findViewById(R.id.txt_Ubic);
        fechapolinizP =(EditText) findViewById(R.id.txt_FechaPolinizado);
        plantasTransplaP=(EditText) findViewById(R.id.txt_plantasTrans);
        plantasPolinizadasP =(EditText) findViewById(R.id.txt_plantasPolinizadas);
        fechaCosechaP =(EditText) findViewById(R.id.txt_fechaCosecha);
        mazorcasCosechadaP =(EditText) findViewById(R.id.txt_mazorcasCosechadas);
        comentP=(EditText) findViewById(R.id.txt_comentario);
        listV_pilones= (ListView) findViewById(R.id.lv_datosPersonas);
        inicializarFirebase();
        listarDatos();
        listV_pilones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pilonSelected = (Pilon) parent.getItemAtPosition(position);
                lineP.setText(pilonSelected.getLinea());
                proyP.setText(pilonSelected.getProyecto());
                fechaSiembP.setText(pilonSelected.getFechaSiembra());
                granoSemP.setText(pilonSelected.getGranosSembrados());
                fechaTransP.setText(pilonSelected.getFechaTransplante());
                plantasTransplaP.setText(pilonSelected.getPlantasTransplantadas());
                ubicP.setText(pilonSelected.getUbicacion());
                fechapolinizP.setText(pilonSelected.getFechaPolinizado());
                plantasPolinizadasP.setText(pilonSelected.getPlantasPolinizadas());
                fechaCosechaP.setText(pilonSelected.getFechaCosecha());
                mazorcasCosechadaP.setText(pilonSelected.getMazorcasCosechadas());
                comentP.setText(pilonSelected.getComentario());
            }
        });
    }
    private void listarDatos() {
        databaseReference.child("Pilon").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listPilon.clear();
                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()) {
                    Pilon p = objSnaptshot.getValue(Pilon.class);
                    listPilon.add(p);
                    arrayAdapterPilon = new ArrayAdapter<Pilon>(PrincipalActivity.this, android.R.layout.simple_list_item_1, listPilon);
                    listV_pilones.setAdapter(arrayAdapterPilon);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference =firebaseDatabase.getReference();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String linea = lineP.getText().toString();
        String proyecto = proyP.getText().toString();
        String fechaSiembra = fechaSiembP.getText().toString();
        String granosSembrados = granoSemP.getText().toString();
        String fechaTransplante = fechaTransP.getText().toString();
        String plantasTransplantadas = plantasTransplaP.getText().toString();
        String ubicacion = ubicP.getText().toString();
        String fechaPolinizado = fechapolinizP.getText().toString();
        String plantasPolinizadas = plantasPolinizadasP.getText().toString();
        String fechaCosecha = fechaCosechaP.getText().toString();
        String mazorcasCosechadas = mazorcasCosechadaP.getText().toString();
        String comentario = comentP.getText().toString();
        switch (item.getItemId()){
            case R.id.icon_add:{
                if (linea.equals("")||proyecto.equals("")||fechaSiembra.equals("")||granosSembrados.equals("")
                        ||fechaTransplante.equals("")||plantasTransplantadas.equals("")||ubicacion.equals("")
                        ||fechaPolinizado.equals("")||plantasPolinizadas.equals("")||fechaCosecha.equals("")
                        ||mazorcasCosechadas.equals("")||comentario.equals(""))
                {
                    validacion();
                }else {
                    Pilon p = new Pilon();
                    p.setUid(UUID.randomUUID().toString());
                    p.setLinea(linea);
                    p.setProyecto(proyecto);
                    p.setFechaSiembra(fechaSiembra);
                    p.setGranosSembrados(granosSembrados);
                    p.setFechaTransplante(fechaTransplante);
                    p.setPlantasTransplantadas(plantasTransplantadas);
                    p.setUbicacion(ubicacion);
                    p.setFechaPolinizado(fechaPolinizado);
                    p.setPlantasPolinizadas(plantasPolinizadas);
                    p.setFechaCosecha(fechaCosecha);
                    p.setMazorcasCosechadas(mazorcasCosechadas);
                    p.setComentario(comentario);
                    databaseReference.child("Pilon").child(p.getUid()).setValue(p);
                    Toast.makeText(this,"Agregar",Toast.LENGTH_LONG).show();
                    limpiarCajas();
                }
                break;
            }
            case R.id.icon_save:{
                Pilon p = new Pilon();
                p.setUid(pilonSelected.getUid());
                p.setLinea(lineP.getText().toString().trim());
                p.setProyecto(proyP.getText().toString().trim());
                p.setFechaSiembra(fechaSiembP.getText().toString().trim());
                p.setGranosSembrados(granoSemP.getText().toString().trim());
                p.setFechaTransplante(fechaTransP.getText().toString().trim());
                p.setPlantasTransplantadas(plantasTransplaP.getText().toString().trim());
                p.setUbicacion(ubicP.getText().toString().trim());
                p.setFechaPolinizado(fechapolinizP.getText().toString().trim());
                p.setPlantasPolinizadas(plantasPolinizadasP.getText().toString().trim());
                p.setFechaCosecha(fechaCosechaP.getText().toString().trim());
                p.setMazorcasCosechadas(mazorcasCosechadaP.getText().toString().trim());
                p.setComentario(comentP.getText().toString().trim());
                databaseReference.child("Pilon").child(p.getUid()).setValue(p);
                Toast.makeText(this,"Actualizado",Toast.LENGTH_LONG).show();
                limpiarCajas();
                break;
            }

            default:
                break;
        }
        return true;
    }
    private void limpiarCajas() {
        lineP.setText("");
        proyP.setText("");
        fechaSiembP.setText("");
        granoSemP.setText("");
        fechaTransP.setText("");
        plantasTransplaP.setText("");
        ubicP.setText("");
        fechapolinizP.setText("");
        plantasPolinizadasP.setText("");
        fechaCosechaP.setText("");
        mazorcasCosechadaP.setText("");
        comentP.setText("");
    }

    private void validacion()
    {
        String linea = lineP.getText().toString();
        String proyecto = proyP.getText().toString();
        String fechaSiembra = fechaSiembP.getText().toString();
        String granosSembrados = granoSemP.getText().toString();
        String fechaTransplante = fechaTransP.getText().toString();
        String plantasTransplantadas = plantasTransplaP.getText().toString();
        String ubicacion = ubicP.getText().toString();
        String fechaPolinizado = fechapolinizP.getText().toString();
        String plantasPolinizadas = plantasPolinizadasP.getText().toString();
        String fechaCosecha = fechaCosechaP.getText().toString();
        String mazorcasCosechadas = mazorcasCosechadaP.getText().toString();
        String comentario = comentP.getText().toString();

        if (linea.equals("")){
            lineP.setError("Required");
        }
        else if (proyecto.equals("")){
            proyP.setError("Required");
        }
        else if (fechaSiembra.equals("")){
            fechaSiembP.setError("Required");
        }
        else if (granosSembrados.equals("")){
            granoSemP.setError("Required");
        }
        ///
        else if (fechaTransplante.equals("")){
            fechaTransP.setError("Required");
        }
        else if (plantasTransplantadas.equals("")){
            plantasTransplaP.setError("Required");
        }
        else if (ubicacion.equals("")){
            ubicP.setError("Required");
        }
        ////
        else if (fechaPolinizado.equals("")){
            fechapolinizP.setError("Required");
        }
        else if (plantasPolinizadas.equals("")){
            plantasPolinizadasP.setError("Required");
        }
        else if (fechaCosecha.equals("")){
            fechaCosechaP.setError("Required");
        }
        ///
        else if (mazorcasCosechadas.equals("")){
            mazorcasCosechadaP.setError("Required");
        }
        else if (comentario.equals("")){
            comentP.setError("Required");
        }

    }
}



