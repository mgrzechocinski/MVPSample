package net.grzechocinski.android.mvpsample.register.view;

import net.grzechocinski.android.mvpsample.internal.mvp.presenter.PresentersRepository;
import net.grzechocinski.android.mvpsample.databinding.FragmentRegisterStep1Binding;
import net.grzechocinski.android.mvpsample.databinding.FragmentRegisterStep2Binding;
import net.grzechocinski.android.mvpsample.databinding.FragmentRegisterStep3Binding;
import net.grzechocinski.android.mvpsample.internal.dagger.ActivityComponent;
import net.grzechocinski.android.mvpsample.internal.dagger.ApplicationComponent;
import net.grzechocinski.android.mvpsample.register.presenter.RegisterPresenter;
import net.grzechocinski.android.mvpsample.internal.mvp.view.SuperActivity;
import net.grzechocinski.android.mvpsample.register.model.RegistrationData;
import net.grzechocinski.android.mvpsample.register.presenter.state.RegistrationStep1State;

public class RegisterActivity extends SuperActivity<RegisterPresenter> {

    @Override
    protected void initializePresenter(PresentersRepository presentersRepository) {
        presentersRepository.getRegisterPresenter(RegisterPresenter::new, newPresenter -> {
            presenter = newPresenter;
            presenter.start(new RegistrationStep1State(new RegistrationData()));
        }, existingPresenter -> {
            presenter = existingPresenter;
            presenter.restoreUI(this);
        });
    }

    @Override
    protected void injectDependencies(ApplicationComponent applicationComponent, ActivityComponent activityComponent) {
        applicationComponent.inject(this);
    }

    public FragmentRegisterStep1Binding showStep1() {
        RegisterStep1View fragment = new RegisterStep1View(this);
        changeFragment(fragment);
        return fragment.getBinding();
    }

    public FragmentRegisterStep2Binding showStep2() {
        RegisterStep2View fragment = new RegisterStep2View(this);
        changeFragment(fragment);
        return fragment.getBinding();
    }

    public FragmentRegisterStep3Binding showStep3() {
        RegisterStep3View fragment = new RegisterStep3View(this);
        changeFragment(fragment);
        return fragment.getBinding();
    }
}
