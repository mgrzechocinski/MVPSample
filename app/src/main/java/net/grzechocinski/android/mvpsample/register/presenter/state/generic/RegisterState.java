package net.grzechocinski.android.mvpsample.register.presenter.state.generic;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import net.grzechocinski.android.mvpsample.R;
import net.grzechocinski.android.mvpsample.register.presenter.RegisterPresenter;
import net.grzechocinski.android.mvpsample.register.model.RegistrationData;
import net.grzechocinski.android.mvpsample.register.view.RegisterActivity;
import net.grzechocinski.android.mvpsample.internal.mvp.presenter.Presenter;

public abstract class RegisterState extends Presenter.State<RegisterActivity, RegistrationData, RegisterPresenter> {
}
