package net.grzechocinski.android.mvpsample.internal.dagger;

import java.util.concurrent.ExecutorService;
import net.grzechocinski.android.mvpsample.internal.mvp.presenter.PresentersRepository;
import net.grzechocinski.android.mvpsample.internal.util.RxUtils;
import net.grzechocinski.android.mvpsample.register.view.RegisterActivity;
import dagger.Component;
import de.greenrobot.event.EventBus;
import javax.inject.Singleton;

@Component(modules = ApplicationModule.class)
@Singleton
public interface ApplicationComponent {

    PresentersRepository getPresentersFactory();

    EventBus getEventBus();

    RxUtils getRxUtils();

    void inject(RegisterActivity superActivity);
}
