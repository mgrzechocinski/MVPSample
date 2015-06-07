package net.grzechocinski.android.mvpsample.register.presenter.state;

import android.content.Context;
import android.widget.Button;
import java.util.Random;
import net.grzechocinski.android.mvpsample.R;
import net.grzechocinski.android.mvpsample.register.presenter.state.generic.RegisterState;
import net.grzechocinski.android.mvpsample.register.view.RegisterActivity;
import net.grzechocinski.android.mvpsample.register.view.RegisterStep3View;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RegistrationStep4State extends RegisterState {

    @Override
    public void onUIAttached(RegisterActivity activity) {
        Button btn = ((RegisterStep3View) activity.getCurrentView()).getBinding().sampleBtnRegister;
        btn.getBackground().clearColorFilter();
        btn.setEnabled(false);
        btn.setText(R.string.registering);
    }

    @Override
    protected void onStateApplied() {
        Observable.create(
                new Observable.OnSubscribe<Boolean>() {
                    @Override
                    public void call(Subscriber<? super Boolean> subscriber) {
                        try {
                            //long running operation
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        subscriber.onNext(new Random().nextBoolean());
                        subscriber.onCompleted();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> presenter.setState(new RegisteredFinishedState(result)));
    }

    @Override
    public void onUIDetached(Context applicationContext) {
        presenter.createNotification("Registration in progress", "No worries, registration is in progress...", RegisterActivity.class);
    }

}
