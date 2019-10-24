package com.example.pinclassrooom.Registro;

public class RegistroPresenter implements RegistroInterface.Presenter, RegistroInterface.TaskListener {

    private RegistroInterface.View view;
    private RegistroInterface.Model model;

    public RegistroPresenter (RegistroInterface.View view){
        this.view = view;
        model = new RegistroModel(this);
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void doRegistro(String nombreCompleto, String EmailR, String PasswordR) {
        if (view!=null){
            view.disableInputs();
            view.showProgress();
        }
        model.onRegistro(nombreCompleto, EmailR, PasswordR);
    }

    @Override
    public void onSucess() {
        if (view!=null){
            view.enableInputs();
            view.hideProgress();
            view.onLogin();
        }
    }

    @Override
    public void onError(String error) {
        if (view!=null){
            view.enableInputs();
            view.onError(error);
        }
    }
}
