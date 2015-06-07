package net.grzechocinski.android.mvpsample.internal.dagger;

import net.grzechocinski.android.mvpsample.internal.mvp.presenter.PresentersRepository;
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
    public PresentersRepository presentersFactory(EventBus eventBus){
        return new PresentersRepository(eventBus);
    }
}
