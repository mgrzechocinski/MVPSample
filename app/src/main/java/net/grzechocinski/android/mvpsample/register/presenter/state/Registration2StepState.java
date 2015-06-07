package net.grzechocinski.android.mvpsample.register.presenter.state;

import net.grzechocinski.android.mvpsample.databinding.FragmentRegisterStep2Binding;
import net.grzechocinski.android.mvpsample.register.presenter.state.generic.RegisterState;
import net.grzechocinski.android.mvpsample.register.view.RegisterActivity;
import net.grzechocinski.android.mvpsample.register.view.RegisterStep2View;
import net.grzechocinski.android.mvpsample.internal.mvp.view.SuperView;

public class Registration2StepState extends RegisterState {

    @Override
    public void onUIAttached(RegisterActivity activity) {
        FragmentRegisterStep2Binding binding = activity.showStep2();
        binding.setRegistrationData(stateContext);
        binding.sampleBtnNext.setOnClickListener(v -> validate(activity, binding));
    }

    private void validate(RegisterActivity activity, FragmentRegisterStep2Binding binding) {
        stateContext.takeDataFromView(binding);
        if (stateContext.passwordEmpty()) {
            return;
        }
        if (!stateContext.passwordMatches()) {
            return;
        }
        activity.getPresenter().setState(new Registration3StepState());
    }

    @Override
    protected void saveCurrentViewState(SuperView superView) {
        stateContext.takeDataFromView(((RegisterStep2View) superView).getBinding());
    }
}
