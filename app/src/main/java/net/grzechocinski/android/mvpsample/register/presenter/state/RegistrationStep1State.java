package net.grzechocinski.android.mvpsample.register.presenter.state;

import android.text.Editable;
import android.text.TextUtils;
import net.grzechocinski.android.mvpsample.databinding.FragmentRegisterStep1Binding;
import net.grzechocinski.android.mvpsample.internal.mvp.presenter.Presenter;
import net.grzechocinski.android.mvpsample.internal.mvp.view.SuperView;
import net.grzechocinski.android.mvpsample.register.model.RegistrationData;
import net.grzechocinski.android.mvpsample.register.presenter.state.generic.RegisterState;
import net.grzechocinski.android.mvpsample.register.view.RegisterActivity;
import net.grzechocinski.android.mvpsample.register.view.RegisterStep1View;
import rx.android.widget.WidgetObservable;

public class RegistrationStep1State extends RegisterState implements Presenter.AsyncState {

    public RegistrationStep1State(RegistrationData registrationData) {
        super.stateContext = registrationData;
    }

    @Override
    public void onUIAttached(RegisterActivity activity) {
        FragmentRegisterStep1Binding binding = activity.showStep1();
        binding.setRegistrationData(stateContext);

        WidgetObservable.text(binding.etRegisterUsername, true)
                .map(onTextChangeEvent -> !TextUtils.isEmpty(onTextChangeEvent.text()))
                .subscribe(binding.sampleBtnNext::setEnabled);

        binding.sampleBtnNext.setOnClickListener(v -> validate(activity, binding));
    }

    private void validate(RegisterActivity activity, FragmentRegisterStep1Binding binding) {
        stateContext.takeDataFromView(binding);
        activity.getPresenter().setState(new RegistrationStep2State());
    }

    @Override
    public void saveCurrentViewState(SuperView superView) {
        stateContext.takeDataFromView(((RegisterStep1View) superView).getBinding());
    }

    @Override
    public void performAsyncOperation() {

    }
}
