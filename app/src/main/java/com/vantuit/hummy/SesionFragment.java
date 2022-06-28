package com.vantuit.hummy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
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
    ImageView google_img;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;


    EditText etCorreo, etPassword;
    Button iniciaSesion;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_sesion, container, false);

        google_img = root.findViewById(R.id.iniciarGoogle);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        gsc = GoogleSignIn.getClient(getActivity(),gso);

        google_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignIn();
            }
        });

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

    private void SignIn() {

        Intent intent  = gsc.getSignInIntent();
        startActivityForResult(intent,100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                HomeActivity();
            } catch (ApiException e) {
                Toast.makeText(getActivity(), "Error "+e, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void HomeActivity() {
        /*
        Intent intent = new Intent(getActivity().getApplicationContext(),HomeActivity().class);
        startActivity(intent);*/
        Toast.makeText(getActivity(), "Sesion Iniciada con éxito\nAquí va el intent a la activity HOME", Toast.LENGTH_SHORT).show();
    }
}