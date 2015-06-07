package net.grzechocinski.android.mvpsample.internal.mvp.presenter;

import net.grzechocinski.android.mvpsample.register.presenter.RegisterPresenter;
import de.greenrobot.event.EventBus;
import java.util.HashMap;
import java.util.Map;

public class PresentersRepository {

    private final EventBus eventBus;

    private Map<String, Presenter> presenterMap = new HashMap<>();

    public PresentersRepository(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public <PRESENTER extends Presenter> PRESENTER getRegisterPresenter(PresenterFactory<PRESENTER> presenterFactory,
                                                                        NewPresenterCreatedCallback<PRESENTER> newlyCreated,
                                                                        ExistingPresenterReturnedCallback<PRESENTER> existingReturned) {
        String key = RegisterPresenter.class.getSimpleName();
        if (presenterMap.containsKey(key)) {
            PRESENTER existingPresenter = (PRESENTER) presenterMap.get(key);
            existingReturned.onExistingPresenterReturned(existingPresenter);
            return existingPresenter;
        }
        PRESENTER presenter = presenterFactory.createPresenter(eventBus);
        presenterMap.put(key, presenter);
        newlyCreated.onNewPresenterCreated(presenter);
        return presenter;
    }

    public interface PresenterFactory<PRESENTER> {

        PRESENTER createPresenter(EventBus eventBus);
    }

    public interface NewPresenterCreatedCallback<PRESENTER> {

        void onNewPresenterCreated(PRESENTER newPresenter);
    }

    public interface ExistingPresenterReturnedCallback<PRESENTER> {

        void onExistingPresenterReturned(PRESENTER existingPresenter);
    }
}
