package net.grzechocinski.android.mvpsample.internal.dagger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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

    public ExecutorService provideExecutorService(){
        return Executors.newSingleThreadExecutor();
    }
}
