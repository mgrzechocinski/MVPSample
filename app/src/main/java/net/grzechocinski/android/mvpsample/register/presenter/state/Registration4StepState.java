package net.grzechocinski.android.mvpsample.register.presenter.state;

import android.os.AsyncTask;
import net.grzechocinski.android.mvpsample.register.presenter.state.generic.RegisterAsyncState;
import net.grzechocinski.android.mvpsample.register.view.RegisterActivity;
import net.grzechocinski.android.mvpsample.register.view.RegisterStep3View;
import java.util.Random;

public class Registration4StepState extends RegisterAsyncState {

    @Override
    public void onUIAttached(RegisterActivity activity) {
        ((RegisterStep3View) activity.getCurrentView()).getBinding().sampleBtnRegister.setEnabled(false);
    }

    @Override
    public void performAsyncOperation() {
        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Void... params) {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return new Random().nextBoolean();
            }

            @Override
            protected void onPostExecute(Boolean result) {
                presenter.setState(new RegisteredFinishedState(result));
            }
        }.execute();
    }
}
