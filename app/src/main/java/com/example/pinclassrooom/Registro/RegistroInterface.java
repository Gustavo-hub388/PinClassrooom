package com.example.pinclassrooom.Registro;

public interface RegistroInterface {
    interface View{
        void disableInputs();
        void enableInputs();

        void showProgress();
        void hideProgress();

        void handleRegistro();
        boolean valideteViews();

        void onError(String error);

        void onLogin();
    }

    interface Model{
        void onRegistro (String nombreCompleto, String EmailR, String PasswordR);
    }

    interface Presenter{
        void onDestroy();
        void doRegistro (String nombreCompleto, String EmailR, String PasswordR);

    }

    interface TaskListener{
        void onSucess();
        void onError(String error);
    }
}
