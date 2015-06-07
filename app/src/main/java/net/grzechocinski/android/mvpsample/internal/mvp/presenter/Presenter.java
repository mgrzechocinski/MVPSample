package net.grzechocinski.android.mvpsample.internal.mvp.presenter;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import net.grzechocinski.android.mvpsample.R;
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

    private boolean uiAttached;

    @Deprecated
    /**
     * @deprecated Presenter should be moved to background service instead of holding reference to application context
     */
    private Context applicationContext;

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

        if (currentState.isViewChanger()) {
            lastViewChanger = currentState;
        }
        currentState.setPresenter(this);
        currentState.setStateContext(stateContext);
        currentState.onStateAppliedTemplate();
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
        applicationContext = activity.getApplicationContext();
        uiAttached = true;
        getCurrentState().onUIAttached(activity);
    }

    public boolean isUiAttached(){
        return uiAttached;
    }

    public void onUIDetached(Context applicationContext) {
        uiAttached = false;
        getCurrentState().onUIDetached(applicationContext);
    }

    public void createNotification(String title, String text, Class<? extends Activity> activityClass) {
        PendingIntent pendingIntent = preparePendingIntent(applicationContext, activityClass);
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(applicationContext)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle(title)
                        .setContentText(text)
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent);

        NotificationManager mNotificationManager =
                (NotificationManager) applicationContext.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(12212, mBuilder.build());
    }

    private PendingIntent preparePendingIntent(Context applicationContext, Class<? extends Activity> activityClass) {
        Intent resultIntent = new Intent(applicationContext, activityClass);

        return PendingIntent.getActivity(
                applicationContext,
                0,
                resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
    }

    public static class State<ACTIVITY extends SuperActivity, CONTEXT, PRESENTER extends Presenter> {

        protected CONTEXT stateContext;

        protected PRESENTER presenter;

        final void onStateAppliedTemplate() {
            Timber.i("Entered state %s", this.getClass().getSimpleName());
            onStateApplied();
        }

        protected void onStateApplied() {
        }

        public void onUIAttached(ACTIVITY activity) {
        }

        public void onUIDetached(Context applicationContext) {
        }

        protected void saveCurrentViewState(SuperView superView) {
        }

        public boolean onBackPressed() {
            return false;
        }

        protected boolean isViewChanger(){
            return false;
        }

        final void setStateContext(CONTEXT stateContext) {
            this.stateContext = stateContext;
        }

        final void setPresenter(PRESENTER presenter) {
            this.presenter = presenter;
        }
    }
}
