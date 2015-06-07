package net.grzechocinski.android.mvpsample.internal.mvp.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

public class SuperView extends FrameLayout {

    protected final LayoutInflater layoutInflater;

    boolean manualTransition;

    public SuperView(Context context) {
        super(context);
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (!manualTransition) {
            ((SuperActivity) getContext()).getPresenter().saveInstanceState(this);
        }
    }

    public void setManualTransition(boolean manualTransition) {
        this.manualTransition = manualTransition;
    }
}
