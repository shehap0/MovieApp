package com.example.movieapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class ResetPasswordFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_reset_password, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btnSendReset = view.findViewById(R.id.btnSendReset);
        TextView txtBackToLogin = view.findViewById(R.id.txtBackToLogin);

        // Showing Reset Message
        btnSendReset.setOnClickListener(v ->
                Toast.makeText(requireContext(), "Reset link sent!", Toast.LENGTH_SHORT).show()
        );

        // Go back to LoginFragment
        txtBackToLogin.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.loginFragment)
        );
    }
}
