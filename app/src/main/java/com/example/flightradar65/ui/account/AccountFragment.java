package com.example.flightradar65.ui.account;

import static androidx.fragment.app.FragmentManager.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
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
        EditText login = view.findViewById(R.id.editTextUsername);
        EditText password = view.findViewById(R.id.editTextPassword);
        //Toast.makeText(context, loginButton.getText(), Toast.LENGTH_LONG).show();

        loginButton.setOnClickListener(view1 -> {
            if (!login.getText().toString().equals("") && !password.getText().toString().equals("")) {
                accountViewModel.setLogin(String.valueOf(login.getText()));
                //Toast.makeText(context, "Login", Toast.LENGTH_LONG).show();


                mAuth.signInWithEmailAndPassword(login.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {

                            @SuppressLint("RestrictedApi")
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(context, "Login successfully.",
                                            Toast.LENGTH_SHORT).show();
                                    updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(context, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    updateUI(null);
                                }
                            }
                        });
            } else {
                Toast.makeText(context, "error", Toast.LENGTH_LONG).show();
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
        if(currentUser != null){
        }
    }

}