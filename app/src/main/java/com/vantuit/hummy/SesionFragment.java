package com.vantuit.hummy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
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
    GoogleSignInClient mGoogleSignInClient;
    private static int  RC_SIGN_IN = 100;

    EditText etCorreo, etPassword;
    Button iniciaSesion;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_sesion, container, false);
        auth = FirebaseAuth.getInstance();
        creaSolicitud(); //Crea la solicitud para iniciar secion con google
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


    public void onStart() {//EN TEORIA ES UN METODO QUE SE EJECUTA AL INICIO DE LA APP Y REVISA EL LOGUEO
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });
    }

    //------------ inicio de sesion con google
    public void creaSolicitud() //Configuramos el inicio de sesion de google
    {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);

        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getActivity());
        //Opcional
        SignInButton signInButton = root.findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InicioGoogle();
            }
        });
    }
    //----------------------------------------------------------------------------------------------------------------------------
    //INICIO DE SESION CON GOOGLE
    public void InicioGoogle(){
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);


            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getActivity().getApplicationContext());
            if (acct != null) {
                String personName = acct.getDisplayName();
                String personEmail = acct.getEmail();
                String personId = acct.getId();
                Uri personPhoto = acct.getPhotoUrl();
                Toast.makeText(getActivity(), "Bienvenido de Nuevo "+personName, Toast.LENGTH_SHORT).show();

            }
            //finish();
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Toast.makeText(getActivity(), "codigo de error=\"" + e, Toast.LENGTH_SHORT).show();
        }
    }//------------------------------------------------------------------------
    public void terminaRegisto()
    {
        FragmentManager fm = getFragmentManager();
        SesionFragment fragment = new SesionFragment() ;
        fm.beginTransaction().replace(R.id.fragment_registro, fragment).commit();
    }
}