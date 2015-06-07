package net.grzechocinski.android.mvpsample.register.model;

import android.text.TextUtils;
import net.grzechocinski.android.mvpsample.databinding.FragmentRegisterStep1Binding;
import net.grzechocinski.android.mvpsample.databinding.FragmentRegisterStep2Binding;
import net.grzechocinski.android.mvpsample.databinding.FragmentRegisterStep3Binding;

public class RegistrationData {

    private String username;

    private String password;

    private String passwordConfirmation;

    private int passwordLength;

    public RegistrationData() {
    }

    public void takeDataFromView(FragmentRegisterStep1Binding step1Binding) {
        username = step1Binding.etRegisterUsername.getText().toString();
    }

    public void takeDataFromView(FragmentRegisterStep2Binding step2Binding) {
        password = step2Binding.etRegisterPassword.getText().toString();
        passwordConfirmation = step2Binding.etRegisterPasswordConfirmation.getText().toString();
        passwordLength = password.length();
    }

    public void takeDataFromView(FragmentRegisterStep3Binding step3Binding) {
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public String getPasswordLength() {
        return passwordLength + "";
    }

    public boolean passwordMatches() {
        return password != null && passwordConfirmation != null && password.equals(passwordConfirmation);
    }

    public boolean passwordEmpty() {
        return TextUtils.isEmpty(password);
    }
}
