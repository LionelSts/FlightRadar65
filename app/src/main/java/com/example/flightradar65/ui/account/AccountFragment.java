package com.example.flightradar65.ui.account;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.flightradar65.R;

public class AccountFragment extends Fragment {
    AccountViewModel accountViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);
        Context context = requireActivity().getApplicationContext();

        Button loginButton = view.findViewById(R.id.loginButton);
        EditText login = view.findViewById(R.id.editTextUsername);
        EditText password = view.findViewById(R.id.editTextPassword);
        Toast.makeText(context, loginButton.getText(), Toast.LENGTH_LONG).show();
        loginButton.setOnClickListener(view1 -> {
            if (!login.getText().toString().equals("") && !password.getText().toString().equals("")) {
                accountViewModel.setLogin(String.valueOf(login.getText()));
                Toast.makeText(context, "Login", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, "error", Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }

}