package com.example.flightradar65.ui.account;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.flightradar65.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AccountFragment extends Fragment {
    AccountViewModel accountViewModel;
    private FirebaseAuth mAuth;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_account, container, false);
        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);
        Context context = requireActivity().getApplicationContext();
        mAuth = FirebaseAuth.getInstance();
        Button loginButton = view.findViewById(R.id.loginButton);
        Button disconnectButton = view.findViewById(R.id.disconnectButton);
        EditText login = view.findViewById(R.id.editTextUsername);
        EditText password = view.findViewById(R.id.editTextPassword);
        //Toast.makeText(context, loginButton.getText(), Toast.LENGTH_LONG).show();
        view.findViewById(R.id.disconnectButton).setVisibility(View.GONE);


        loginButton.setOnClickListener(view1 -> {
            if (!login.getText().toString().equals("") && !password.getText().toString().equals("")) {
                accountViewModel.setLogin(String.valueOf(login.getText()));


                mAuth.createUserWithEmailAndPassword(login.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(requireActivity(), task -> {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();
                                updateUI(user);
                                Toast.makeText(context, "Account created.", Toast.LENGTH_SHORT).show();
                            } else {
                                mAuth.signInWithEmailAndPassword(login.getText().toString(), password.getText().toString())
                                        .addOnCompleteListener(requireActivity(), task1 -> {
                                            if (task1.isSuccessful()) {
                                                // Sign in success, update UI with the signed-in user's information
                                                FirebaseUser user = mAuth.getCurrentUser();
                                                Toast.makeText(context, "Login successfully.",
                                                        Toast.LENGTH_SHORT).show();
                                                updateUI(user);
                                                view.findViewById(R.id.loginButton).setVisibility(View.GONE);
                                                view.findViewById(R.id.editTextUsername).setVisibility(View.GONE);
                                                view.findViewById(R.id.editTextPassword).setVisibility(View.GONE);
                                                view.findViewById(R.id.disconnectButton).setVisibility(View.VISIBLE);
                                                TextView a = view.findViewById(R.id.loginTitle);
                                                a.setText("Hello " + login.getText().toString() + " !");
                                                a.setTextSize(15);

                                            } else {
                                                // If sign in fails, display a message to the user.
                                                Toast.makeText(context, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                                updateUI(null);
                                            }
                                        });

                            }
                        });


            } else {
                Toast.makeText(context, "error", Toast.LENGTH_LONG).show();
            }
        });

        disconnectButton.setOnClickListener(view1 -> {
            FirebaseUser currentUser = mAuth.getCurrentUser();
            if(currentUser != null){
                Toast.makeText(context, "Disconnect Successfully !", Toast.LENGTH_SHORT).show();
                FirebaseAuth.getInstance().signOut();
                view.findViewById(R.id.loginButton).setVisibility(View.VISIBLE);
                view.findViewById(R.id.editTextUsername).setVisibility(View.VISIBLE);
                view.findViewById(R.id.editTextPassword).setVisibility(View.VISIBLE);
                view.findViewById(R.id.disconnectButton).setVisibility(View.GONE);
                TextView a = view.findViewById(R.id.loginTitle);
                a.setText(R.string.log_in_register);
                a.setTextSize(30);
            }
            else {
                Toast.makeText(context, "You're not connected !", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void updateUI(FirebaseUser user) {
    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

}