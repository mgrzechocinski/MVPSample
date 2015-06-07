package net.grzechocinski.android.mvpsample.internal.dagger;

import android.app.Activity;
import dagger.Component;

@Component(
        dependencies = ApplicationComponent.class,
        modules = ActivityModule.class
)
@ActivityScope
public interface ActivityComponent {

    Activity activity();
}
