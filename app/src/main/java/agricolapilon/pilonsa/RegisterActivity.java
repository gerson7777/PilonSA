package agricolapilon.pilonsa;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
// variables
    EditText email,password;
    Button register;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //Instancia para autentificar
        auth = FirebaseAuth.getInstance();

        //castear los campos y botones
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        register = (Button) findViewById(R.id.register);

        // metodo oncliklistener donde se trabajara la logica
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userE = email.getText().toString();
                String passE = password.getText().toString();

                if (TextUtils.isEmpty(userE)){
                    Toast.makeText(getApplicationContext(),"Falta Correo",Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(passE)){
                    Toast.makeText(getApplicationContext(),"Falta Contraseña",Toast.LENGTH_SHORT).show();
                }
                auth.createUserWithEmailAndPassword(userE,passE)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(getApplicationContext(),"Creado",Toast.LENGTH_SHORT).show();


                                if (!task.isSuccessful()){
                                    Toast.makeText(getApplicationContext(),"Sucedio un Problema",Toast.LENGTH_SHORT).show();
                                }

                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        });
            }
        });
    }
}
