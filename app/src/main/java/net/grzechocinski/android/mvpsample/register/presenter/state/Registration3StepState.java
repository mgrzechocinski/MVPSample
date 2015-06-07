package net.grzechocinski.android.mvpsample.register.presenter.state;

import net.grzechocinski.android.mvpsample.databinding.FragmentRegisterStep3Binding;
import net.grzechocinski.android.mvpsample.register.presenter.state.generic.RegisterState;
import net.grzechocinski.android.mvpsample.register.view.RegisterActivity;
import net.grzechocinski.android.mvpsample.register.view.RegisterStep3View;
import net.grzechocinski.android.mvpsample.internal.mvp.view.SuperView;

public class Registration3StepState extends RegisterState {

    @Override
    public void onUIAttached(RegisterActivity activity) {
        FragmentRegisterStep3Binding binding = activity.showStep3();
        binding.setRegistrationData(stateContext);
        binding.sampleBtnRegister.setOnClickListener(v -> presenter.setState(new Registration4StepState()));
    }

    @Override
    protected void saveCurrentViewState(SuperView superView) {
        stateContext.takeDataFromView(((RegisterStep3View) superView).getBinding());
    }

    @Override
    public boolean onBackPressed() {
        presenter.setState(new Registration1StepState(stateContext));
        return true;
    }
}
