package com.example.movieapp;

import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class LoginFragment extends Fragment {

    private EditText edtPassword, edtUsername;

    //PASSWORD REGEX
    //8 characters
    //Uppercase
    //Lowercase
    //Number
    //Special character
    private static final String PASSWORD_REGEX =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button loginBtn = view.findViewById(R.id.btnLogin);
        TextView signupTxt = view.findViewById(R.id.txtSignup);
        TextView forgotTxt = view.findViewById(R.id.txtForgotPassword);

        edtUsername = view.findViewById(R.id.edtUsername);
        edtPassword = view.findViewById(R.id.edtPassword);

        // Password toggle
        setupPasswordToggle(edtPassword);

        // Login
        loginBtn.setOnClickListener(v -> {

            String username = edtUsername.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();

            if (username.isEmpty()) {
                edtUsername.setError("Username required");
                return;
            }

            if (password.isEmpty()) {
                edtPassword.setError("Password required");
                return;
            }

            if (!password.matches(PASSWORD_REGEX)) {
                edtPassword.setError(
                        "Password must contain:\n" +
                                "- 8 characters\n" +
                                "- Uppercase letter\n" +
                                "- Lowercase letter\n" +
                                "- Number\n" +
                                "- Special character"
                );
                return;
            }

            //SUCCESS → Go to Home
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.homeFragment);
        });

        // Signup
        signupTxt.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.signupFragment);
        });

        // Forgot Password
        forgotTxt.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.resetPasswordFragment);
        });
    }

    // Show / Hide password
    private void setupPasswordToggle(EditText passwordField) {
        passwordField.setOnTouchListener((v, event) -> {
            final int RIGHT = 2; // drawableEnd

            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (passwordField.getCompoundDrawables()[RIGHT] == null) return false;

                if (event.getRawX() >=
                        (passwordField.getRight()
                                - passwordField.getCompoundDrawables()[RIGHT].getBounds().width())) {

                    int selection = passwordField.getText().length();

                    if (passwordField.getInputType() ==
                            (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)) {

                        passwordField.setInputType(
                                InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        passwordField.setCompoundDrawablesWithIntrinsicBounds(
                                0, 0, android.R.drawable.ic_delete, 0);

                    } else {
                        passwordField.setInputType(
                                InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        passwordField.setCompoundDrawablesWithIntrinsicBounds(
                                0, 0, android.R.drawable.ic_menu_view, 0);
                    }

                    passwordField.setSelection(selection);
                    return true;
                }
            }

            return false;
        });
    }
}
