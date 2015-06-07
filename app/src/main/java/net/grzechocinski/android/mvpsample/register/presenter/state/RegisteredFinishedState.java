package net.grzechocinski.android.mvpsample.register.presenter.state;

import android.widget.Toast;
import net.grzechocinski.android.mvpsample.register.presenter.state.generic.RegisterState;
import net.grzechocinski.android.mvpsample.register.view.RegisterActivity;
import net.grzechocinski.android.mvpsample.register.view.RegisterStep3View;

public class RegisteredFinishedState extends RegisterState {

    private Boolean result;

    public RegisteredFinishedState(Boolean result) {
        this.result = result;
    }

    @Override
    public void onUIAttached(RegisterActivity activity) {
        Toast.makeText(activity, "Result: " + result, Toast.LENGTH_SHORT).show();
        ((RegisterStep3View) activity.getCurrentView()).getBinding().sampleBtnRegister.setEnabled(true);
    }
}
