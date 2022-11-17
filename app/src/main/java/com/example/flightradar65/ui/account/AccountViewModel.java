package com.example.flightradar65.ui.account;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AccountViewModel extends ViewModel {

    private final MutableLiveData<String> mLogin = new MutableLiveData<>();

    public void setLogin(String login) {
        mLogin.setValue(login);
    }

    public LiveData<String> getLogin() {
        return mLogin;
    }
}