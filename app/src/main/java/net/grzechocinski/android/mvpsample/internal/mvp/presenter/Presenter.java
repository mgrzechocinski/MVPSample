package net.grzechocinski.android.mvpsample.internal.mvp.presenter;

import net.grzechocinski.android.mvpsample.internal.bus.RequestUIUpdateEvent;
import net.grzechocinski.android.mvpsample.internal.mvp.view.SuperActivity;
import net.grzechocinski.android.mvpsample.register.view.RegisterActivity;
import net.grzechocinski.android.mvpsample.internal.mvp.view.SuperView;
import de.greenrobot.event.EventBus;
import timber.log.Timber;

public abstract class Presenter<T extends Presenter.State> {

    private EventBus eventBus;

    private T currentState;

    private T lastViewChanger;

    public Presenter(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void start(T state) {
        Timber.i("Kicking off state machine by moving into state: %s", state.getClass().getSimpleName());
        currentState = state;
        setState(state);
    }

    public void setState(T nextState) {
        Object stateContext = currentState.stateContext;

        currentState = nextState;

        if (!(currentState instanceof AsyncState)){
            lastViewChanger = currentState;
        }
        currentState.setPresenter(this);
        currentState.setStateContext(stateContext);
        currentState.onStateApplied();
        notifyUI();
    }

    public boolean handleBackPressed() {
        return currentState.onBackPressed();
    }

    public void restoreUI(RegisterActivity activity) {
        lastViewChanger.onUIAttached(activity);
        getCurrentState().onUIAttached(activity);
    }

    public void saveInstanceState(SuperView superView) {
        getCurrentState().saveCurrentViewState(superView);
    }

    public void notifyUI() {
        eventBus.post(new RequestUIUpdateEvent());
    }

    private T getCurrentState() {
        return currentState;
    }

    public void onUIAttached(SuperActivity activity) {
        getCurrentState().onUIAttached(activity);
    }

    public static class State<ACTIVITY extends SuperActivity, CONTEXT, PRESENTER extends Presenter> {

        protected CONTEXT stateContext;

        protected PRESENTER presenter;

        final void onStateApplied() {
            Timber.i("Entered state %s", this.getClass().getSimpleName());
            if(this instanceof AsyncState){
                ((AsyncState) this).performAsyncOperation();
            }
        }

        public void onUIAttached(ACTIVITY activity) {
        }

        protected void saveCurrentViewState(SuperView superView) {
        }

        public boolean onBackPressed() {
            return false;
        }

        final void setStateContext(CONTEXT stateContext) {
            this.stateContext = stateContext;
        }

        final void setPresenter(PRESENTER presenter) {
            this.presenter = presenter;
        }
    }

    public interface AsyncState{
        void performAsyncOperation();
    }
}
