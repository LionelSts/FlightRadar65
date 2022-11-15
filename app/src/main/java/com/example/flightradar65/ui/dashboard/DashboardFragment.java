package com.example.flightradar65.ui.dashboard;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.flightradar65.databinding.FragmentDashboardBinding;
import com.example.flightradar65.ui.account.AccountViewModel;

public class DashboardFragment extends Fragment {
        private FragmentDashboardBinding binding;
        private String userLogin;

        public View onCreateView(@NonNull LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {
            Context context = requireActivity().getApplicationContext();
            AccountViewModel viewModel = new ViewModelProvider(requireActivity()).get(AccountViewModel.class);
            viewModel.getLogin().observe(getViewLifecycleOwner(), login -> userLogin = login);
            binding = FragmentDashboardBinding.inflate(inflater, container, false);
            View root = binding.getRoot();


            if (userLogin != null) {
                String testVar = String.valueOf(viewModel.getLogin());
                Toast.makeText(context, testVar, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, "error", Toast.LENGTH_LONG).show();
            }
            return root;
        }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}