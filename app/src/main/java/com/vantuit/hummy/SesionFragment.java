package com.vantuit.hummy;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.Executor;


public class SesionFragment extends Fragment {

    View root;
    private FirebaseAuth auth;

    EditText etCorreo, etPassword;
    Button iniciaSesion;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_sesion, container, false);
        auth = FirebaseAuth.getInstance();
        etCorreo = root.findViewById(R.id.etCorreo);
        etPassword = root.findViewById(R.id.etPassword);
        iniciaSesion = root.findViewById(R.id.bIniciaSesion);
        // On Click del método del boton inicia Sesion, el cual inicia sesion con correo y contraseña
        iniciaSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtiene el correo y contraseña de los Edit Text
                String email = etCorreo.getText().toString();
                String password = etPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getActivity(),"Ingrese el correo",Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getActivity(),"Ingrese la contraseña",Toast.LENGTH_LONG).show();
                    return;
                }
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener( getActivity(), new OnCompleteListener<AuthResult>() {
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(getActivity(), "Inicio de sesion EXITOSO", Toast.LENGTH_SHORT).show();
                                    FirebaseUser user = auth.getCurrentUser();
                                } else
                                    // Si algo sale mal muestra mensage de error
                                    Toast.makeText(getActivity(), "El correo o contraseña no coinciden", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        return root;

    }
}