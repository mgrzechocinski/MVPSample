package net.grzechocinski.android.mvpsample.internal.dagger;

import net.grzechocinski.android.mvpsample.internal.mvp.presenter.PresentersFactory;
import dagger.Module;
import dagger.Provides;
import de.greenrobot.event.EventBus;
import javax.inject.Singleton;

@Module
public class ApplicationModule {

    @Provides
    @Singleton
    public EventBus eventBus(){
        return EventBus.getDefault();
    }

    @Provides
    @Singleton
    public PresentersFactory presentersFactory(EventBus eventBus){
        return new PresentersFactory(eventBus);
    }
}
