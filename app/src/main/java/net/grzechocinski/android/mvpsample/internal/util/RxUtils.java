package net.grzechocinski.android.mvpsample.internal.util;

import android.text.TextUtils;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.android.widget.OnTextChangeEvent;

public class RxUtils {

    @Singleton
    @Inject
    public RxUtils() {
    }

    public boolean allNotEmpty(OnTextChangeEvent... event) {
        for (OnTextChangeEvent onTextChangeEvent : event) {
            if(TextUtils.isEmpty(onTextChangeEvent.text())){
                return false;
            }
        }
        return true;
    }
}
