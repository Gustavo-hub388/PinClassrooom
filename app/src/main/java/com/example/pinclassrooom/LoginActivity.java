package com.example.pinclassrooom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.pinclassrooom.Login.LoginInterface;
import com.example.pinclassrooom.Login.LoginPresenter;
import com.google.android.material.textfield.TextInputEditText;

import mehdi.sakout.fancybuttons.FancyButton;

public class LoginActivity extends AppCompatActivity implements LoginInterface.View {

    private TextInputEditText txtEmailLogin, txtPasswordLogin;
    private FancyButton btnIniciarSesion;
    private MaterialDialog mProgress;
    private LoginInterface.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setViews();
        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleLogin();
            }
        });
    }

    public void IrRecupPass(View view){
        startActivity(new Intent(this, RecoverPasswordActivity.class));
    }

    public void IrRegistro(View view){
        startActivity(new Intent(this,RegistroActivity.class));
    }

    private void setViews() {
        presenter =new LoginPresenter(this);
        txtEmailLogin = findViewById(R.id.txtCorreoLogin);
        txtPasswordLogin = findViewById(R.id.txtPasswordLogin);
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        MaterialDialog.Builder builder = new MaterialDialog.Builder(this)
                .title("Cargando")
                .content("Espere por favor...")
                .cancelable(false)
                .progress(true,0);
        mProgress = builder.build();
    }

    @Override
    public void disableInputs() {
        setInputs(false);
    }

    private void setInputs(boolean b) {
        txtEmailLogin.setEnabled(b);
        txtPasswordLogin.setEnabled(b);
        btnIniciarSesion.setEnabled(b);
    }

    @Override
    public void enableInputs() {
        setInputs(true);
    }

    @Override
    public void showProgress() {
        mProgress.show();
    }

    @Override
    public void hideProgress() {
        mProgress.dismiss();
    }

    @Override
    public void handleLogin() {
        if(!isValidEmailLogin()){
            Toast.makeText(this, "No es un correo válido",Toast.LENGTH_SHORT).show();
        } else if (!isValidPasswordLogin()){
            Toast.makeText(this, "No es una contraseña válida",Toast.LENGTH_SHORT).show();
        } else {
            presenter.toLogin(txtEmailLogin.getText().toString().trim(), txtPasswordLogin.getText().toString().trim());
        }
    }

    @Override
    public boolean isValidEmailLogin() {
        return Patterns.EMAIL_ADDRESS.matcher(txtEmailLogin.getText().toString()).matches();
    }

    @Override
    public boolean isValidPasswordLogin() {
        if (TextUtils.isEmpty(txtPasswordLogin.getText().toString())){
            //Toast.makeText(this, "No es una contraseña válida",Toast.LENGTH_SHORT).show();
            txtPasswordLogin.setError("Ingrese la contraseña");
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onLogin() {
        Toast.makeText(this, "Has iniciado correctamente",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,MenuSlideActivity.class));
        finish();
    }

    @Override
    public void onError(String error) {
        Toast.makeText(this, error,Toast.LENGTH_SHORT).show();
        hideProgress();
        enableInputs();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
