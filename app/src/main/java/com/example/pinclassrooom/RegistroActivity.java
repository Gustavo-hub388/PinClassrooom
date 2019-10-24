package com.example.pinclassrooom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.pinclassrooom.Registro.RegistroInterface;
import com.example.pinclassrooom.Registro.RegistroPresenter;
import com.google.android.material.textfield.TextInputEditText;

import mehdi.sakout.fancybuttons.FancyButton;

public class RegistroActivity extends AppCompatActivity implements RegistroInterface.View {

    private TextInputEditText txtNombreCompR, txtCorreoR, txtPass1R, txtPass2R;
    private FancyButton btnCrearCuenta;
    private MaterialDialog mProgress;
    private RegistroInterface.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        setViews();
    }

    private void setViews() {
        presenter = new RegistroPresenter(this);
        txtNombreCompR = findViewById(R.id.txtNombreCompletoR);
        txtCorreoR = findViewById(R.id.txtCorreoR);
        txtPass1R = findViewById(R.id.txtPassR);
        txtPass2R = findViewById(R.id.txtPass2R);
        btnCrearCuenta = findViewById(R.id.btnCrearCuenta);

        btnCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleRegistro();
            }
        });

        MaterialDialog.Builder builder = new MaterialDialog.Builder(this)
                .title("Cargando")
                .content("Espere por favor...")
                .cancelable(false)
                .progress(true,0);
        mProgress = builder.build();
    }

    public void IrInicio(View view){
        startActivity(new Intent(this,LoginActivity.class));
        finish();
    }

    @Override
    public void disableInputs() {
        setEnable(false);
    }

    private void setEnable(boolean b) {
        txtPass2R.setEnabled(b);
        txtPass1R.setEnabled(b);
        txtCorreoR.setEnabled(b);
        txtNombreCompR.setEnabled(b);
        btnCrearCuenta.setEnabled(b);
    }

    @Override
    public void enableInputs() {
        setEnable(true);
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
    public void handleRegistro() {
        if (valideteViews()){
            presenter.doRegistro(txtNombreCompR.getText().toString(),txtCorreoR.getText().toString().trim(), txtPass1R.getText().toString().trim());
        }
    }

    @Override
    public boolean valideteViews() {
        boolean retVal = true;
        if (TextUtils.isEmpty(txtNombreCompR.getText())){
            txtNombreCompR.setError("Este campo es obligatorio");
            retVal = false;
        }
        if (TextUtils.isEmpty(txtCorreoR.getText())){
            txtCorreoR.setError("Este campo es obligatorio");
            retVal = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(txtCorreoR.getText().toString().trim()).matches()){
            txtCorreoR.setError("No es un correo válido.");
            retVal = false;
        }
        if (TextUtils.isEmpty(txtPass1R.getText())){
            txtPass1R.setError("Este campo es obligatorio");
            retVal = false;
        } else if (txtPass1R.getText().toString().length() < 8){
            txtPass1R.setError("Debes poner almenos 8 caracteres.");
            retVal = false;
        }
        if (TextUtils.isEmpty(txtPass2R.getText())){
            txtPass2R.setError("Este campo es obligatorio");
            retVal = false;
        } else if (!txtPass1R.getText().toString().trim().equals(txtPass2R.getText().toString().trim())){
            retVal = false;
            txtPass2R.setError("Las contraseñas no coinciden.");
        }

        return retVal;
    }

    @Override
    public void onError(String error) {
        Toast.makeText(this,error, Toast.LENGTH_SHORT).show();
        hideProgress();
        enableInputs();
    }

    @Override
    public void onLogin() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
