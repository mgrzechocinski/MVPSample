package net.grzechocinski.android.mvpsample.register.presenter.state;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.widget.Button;
import net.grzechocinski.android.mvpsample.R;
import net.grzechocinski.android.mvpsample.register.presenter.state.generic.RegisterState;
import net.grzechocinski.android.mvpsample.register.view.RegisterActivity;
import net.grzechocinski.android.mvpsample.register.view.RegisterStep3View;

public class RegisteredFinishedState extends RegisterState {

    private Boolean result;

    public RegisteredFinishedState(Boolean result) {
        this.result = result;
    }

    @Override
    protected void onStateApplied() {
        if(!presenter.isUiAttached()){
            presenter.createNotification("Registration finished", "Tap to continue", RegisterActivity.class);
        }
    }

    @Override
    public void onUIAttached(RegisterActivity activity) {
        Button btn = ((RegisterStep3View) activity.getCurrentView()).getBinding().sampleBtnRegister;
        btn.setEnabled(true);
        btn.setOnClickListener(v -> presenter.setState(result ? new RegistrationStep1State(stateContext) : new RegistrationStep4State()));
        btn.getBackground().setColorFilter(result ? Color.GREEN : Color.RED, PorterDuff.Mode.DARKEN);
        btn.setText(result ? R.string.success : R.string.try_again);
    }
}
