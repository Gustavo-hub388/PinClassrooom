package com.example.pinclassrooom.Login;

public interface LoginInterface {
    interface View {
        void disableInputs();
        void enableInputs();

        void showProgress();
        void hideProgress();

        void handleLogin();

        boolean isValidEmailLogin();
        boolean isValidPasswordLogin();

        void onLogin();
        void onError(String error);
    }

    interface Presenter {
        void onDestroy();

        void toLogin(String emailLogin, String passwordLogin);

    }

    interface Model {
        void doLogin (String emailLogin, String passwordLogin);

    }

    interface TaskListener{
        void onSucess();
        void onError(String error);

    }
}
