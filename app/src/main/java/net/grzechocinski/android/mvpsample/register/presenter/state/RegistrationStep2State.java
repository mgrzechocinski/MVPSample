package net.grzechocinski.android.mvpsample.register.presenter.state;

import static rx.Observable.combineLatest;
import static rx.android.view.ViewActions.setEnabled;

import android.text.TextUtils;
import android.widget.Toast;
import net.grzechocinski.android.mvpsample.databinding.FragmentRegisterStep2Binding;
import net.grzechocinski.android.mvpsample.register.presenter.state.generic.RegisterState;
import net.grzechocinski.android.mvpsample.register.view.RegisterActivity;
import net.grzechocinski.android.mvpsample.register.view.RegisterStep2View;
import net.grzechocinski.android.mvpsample.internal.mvp.view.SuperView;
import rx.Observable;
import rx.android.view.ViewActions;
import rx.android.widget.OnTextChangeEvent;
import rx.android.widget.WidgetObservable;
import rx.functions.Action1;

public class RegistrationStep2State extends RegisterState {

    @Override
    public void onUIAttached(RegisterActivity activity) {
        FragmentRegisterStep2Binding binding = activity.showStep2();
        binding.setRegistrationData(stateContext);

        Observable<OnTextChangeEvent> passwordCharEntered = WidgetObservable.text(binding.etRegisterPassword, true);
        Observable<OnTextChangeEvent> passwordConfCharEntered = WidgetObservable.text(binding.etRegisterPasswordConfirmation, true);
        passwordCharEntered
                .flatMap(inputEvent -> combineLatest(passwordCharEntered, passwordConfCharEntered, this::allNotEmpty))
                .subscribe(binding.sampleBtnNext::setEnabled);

        binding.sampleBtnNext.setOnClickListener(v -> validate(activity, binding));
    }

    private void validate(RegisterActivity activity, FragmentRegisterStep2Binding binding) {
        stateContext.takeDataFromView(binding);

        if (stateContext.passwordEmpty()) {
            return;
        }
        if (!stateContext.passwordMatches()) {
            Toast.makeText(activity, "Passwords don't match", Toast.LENGTH_SHORT).show();
            return;
        }
        activity.getPresenter().setState(new RegistrationStep3State());
    }

    private boolean allNotEmpty(OnTextChangeEvent... event) {
        for (OnTextChangeEvent onTextChangeEvent : event) {
            if(TextUtils.isEmpty(onTextChangeEvent.text())){
                return false;
            }
        }
        return true;
    }

    @Override
    protected void saveCurrentViewState(SuperView superView) {
        stateContext.takeDataFromView(((RegisterStep2View) superView).getBinding());
    }
}
