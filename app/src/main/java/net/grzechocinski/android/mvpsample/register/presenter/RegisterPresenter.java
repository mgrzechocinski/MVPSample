package net.grzechocinski.android.mvpsample.register.presenter;

import net.grzechocinski.android.mvpsample.register.presenter.state.generic.RegisterState;
import net.grzechocinski.android.mvpsample.internal.mvp.presenter.Presenter;
import de.greenrobot.event.EventBus;

public class RegisterPresenter extends Presenter<RegisterState> {

    public RegisterPresenter(EventBus eventBus) {
        super(eventBus);
    }


}
