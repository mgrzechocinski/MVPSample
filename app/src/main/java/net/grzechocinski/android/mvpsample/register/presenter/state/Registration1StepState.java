package net.grzechocinski.android.mvpsample.register.presenter.state;

import android.text.Editable;
import android.text.TextUtils;
import net.grzechocinski.android.mvpsample.databinding.FragmentRegisterStep1Binding;
import net.grzechocinski.android.mvpsample.register.model.RegistrationData;
import net.grzechocinski.android.mvpsample.register.presenter.state.generic.RegisterState;
import net.grzechocinski.android.mvpsample.register.view.RegisterActivity;
import net.grzechocinski.android.mvpsample.register.view.RegisterStep1View;
import net.grzechocinski.android.mvpsample.internal.mvp.view.SuperView;
import net.grzechocinski.android.mvpsample.internal.mvp.presenter.Presenter;

public class Registration1StepState extends RegisterState implements Presenter.AsyncState{

    public Registration1StepState(RegistrationData registrationData) {
        super.stateContext = registrationData;
    }

    @Override
    public void onUIAttached(RegisterActivity activity) {
        FragmentRegisterStep1Binding binding = activity.showStep1();
        binding.setRegistrationData(stateContext);
        binding.sampleBtnNext.setOnClickListener(v -> validate(activity, binding));
    }

    private void validate(RegisterActivity activity, FragmentRegisterStep1Binding binding) {
        Editable userName = binding.etRegisterUsername.getText();
        if (TextUtils.isEmpty(userName)) {
            return;
        }
        stateContext.takeDataFromView(binding);
        activity.getPresenter().setState(new Registration2StepState());
    }

    @Override
    public void saveCurrentViewState(SuperView superView) {
        stateContext.takeDataFromView(((RegisterStep1View) superView).getBinding());
    }

    @Override
    public void performAsyncOperation() {

    }
}
