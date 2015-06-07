package net.grzechocinski.android.mvpsample.register.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import net.grzechocinski.android.mvpsample.R;
import net.grzechocinski.android.mvpsample.databinding.FragmentRegisterStep2Binding;
import net.grzechocinski.android.mvpsample.internal.mvp.view.SuperView;

public class RegisterStep2View extends SuperView {

    private FragmentRegisterStep2Binding binding;

    public RegisterStep2View(Context context) {
        super(context);
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_register_step_2, this, true);
    }

    public FragmentRegisterStep2Binding getBinding() {
        return binding;
    }
}
