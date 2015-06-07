package net.grzechocinski.android.mvpsample.internal.mvp.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ViewAnimator;
import de.greenrobot.event.EventBus;
import net.grzechocinski.android.mvpsample.MVPSampleApp;
import net.grzechocinski.android.mvpsample.R;
import net.grzechocinski.android.mvpsample.internal.bus.RequestUIUpdateEvent;
import net.grzechocinski.android.mvpsample.internal.dagger.ActivityComponent;
import net.grzechocinski.android.mvpsample.internal.dagger.ActivityModule;
import net.grzechocinski.android.mvpsample.internal.dagger.ApplicationComponent;
import net.grzechocinski.android.mvpsample.internal.dagger.DaggerActivityComponent;
import net.grzechocinski.android.mvpsample.internal.mvp.presenter.Presenter;
import net.grzechocinski.android.mvpsample.internal.mvp.presenter.PresentersRepository;

public abstract class SuperActivity<PRESENTER extends Presenter> extends AppCompatActivity {

    protected EventBus eventBus;

    protected PRESENTER presenter;

    private ActivityComponent activityComponent;

    private SuperView currentView;

    private ViewAnimator animator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        animator = (ViewAnimator) findViewById(R.id.container);

        configureAnimator();

        injectDependencies(getApplicationComponent(), getActivityComponent());

        this.eventBus = getApplicationComponent().getEventBus();
        eventBus.register(this);

        initializePresenter(getApplicationComponent().getPresentersFactory());
        initializeInjector();
    }

    protected abstract void injectDependencies(ApplicationComponent applicationComponent, ActivityComponent activityComponent);

    private void configureAnimator() {
        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(200);
        animator.setInAnimation(animation);
    }

    protected abstract void initializePresenter(PresentersRepository presentersRepository);

    public ApplicationComponent getApplicationComponent() {
        return ((MVPSampleApp) getApplication()).component();
    }

    protected void initializeInjector() {
        activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build();
    }

    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }

    @SuppressWarnings("unused")
    public void onEvent(RequestUIUpdateEvent event) {
        presenter.onUIAttached(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        eventBus.unregister(this);
    }

    protected void changeFragment(SuperView superView) {
        if (currentView != null) {
            currentView.setManualTransition(true);
        }
        animator.removeAllViews();
        animator.addView(superView);
        animator.showNext();
        this.currentView = superView;
    }

    public <VIEW extends SuperView> VIEW getCurrentView() {
        return (VIEW) currentView;
    }

    public PRESENTER getPresenter() {
        return presenter;
    }

    @Override
    public void onBackPressed() {
        boolean handled = presenter.handleBackPressed();
        if (!handled) {
            super.onBackPressed();
        }
    }
}
