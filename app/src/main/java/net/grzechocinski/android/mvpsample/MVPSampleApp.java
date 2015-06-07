package net.grzechocinski.android.mvpsample;

import android.app.Application;
import net.grzechocinski.android.mvpsample.internal.dagger.ApplicationModule;
import net.grzechocinski.android.mvpsample.internal.dagger.ApplicationComponent;
import net.grzechocinski.android.mvpsample.internal.dagger.DaggerApplicationComponent;
import com.squareup.leakcanary.LeakCanary;
import timber.log.Timber;

public class MVPSampleApp extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
        Timber.plant(new Timber.DebugTree());
        component = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule()).build();
    }

    public ApplicationComponent component() {
        return component;
    }
}
