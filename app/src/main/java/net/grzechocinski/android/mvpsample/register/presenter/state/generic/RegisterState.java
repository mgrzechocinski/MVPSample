package net.grzechocinski.android.mvpsample.register.presenter.state.generic;

import net.grzechocinski.android.mvpsample.register.presenter.RegisterPresenter;
import net.grzechocinski.android.mvpsample.register.model.RegistrationData;
import net.grzechocinski.android.mvpsample.register.view.RegisterActivity;
import net.grzechocinski.android.mvpsample.internal.mvp.presenter.Presenter;

public abstract class RegisterState extends Presenter.State<RegisterActivity, RegistrationData, RegisterPresenter> {

}
