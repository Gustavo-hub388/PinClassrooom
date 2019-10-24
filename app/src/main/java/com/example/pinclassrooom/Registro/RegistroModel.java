package com.example.pinclassrooom.Registro;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistroModel implements RegistroInterface.Model {

    private RegistroInterface.TaskListener listener;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private FirebaseUser user;

    public RegistroModel(RegistroInterface.TaskListener listener){
        this.listener = listener;
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        user = mAuth.getCurrentUser();
        reference = database.getReference("usuarios").child(user.getUid());
    }

    @Override
    public void onRegistro(final String nombreCompleto, final String EmailR, final String PasswordR) {
        mAuth.createUserWithEmailAndPassword(EmailR,PasswordR).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    reference.child("nombre").setValue(nombreCompleto).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            reference.child("email").setValue(EmailR).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    reference.child("password").setValue(PasswordR).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){
                                                listener.onSucess();
                                                user.sendEmailVerification();
                                                listener.onError("Guardado correctamente.");
                                                listener.onError("Se ha enviado un enlace al correo ingresado, para verificar la dirección de correo electrónico.");
                                            } else {
                                                if (task.getException() != null) {
                                                    listener.onError(task.getException().getMessage());
                                                }
                                            }
                                        }
                                    });
                                }
                            });
                        }
                    });

                } else if (task.getException() != null) {
                    listener.onError(task.getException().getMessage());
                }
            }
        });
    }
}


