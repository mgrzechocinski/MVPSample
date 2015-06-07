package net.grzechocinski.android.mvpsample.register.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import net.grzechocinski.android.mvpsample.R;
import net.grzechocinski.android.mvpsample.databinding.FragmentRegisterStep1Binding;
import net.grzechocinski.android.mvpsample.internal.mvp.view.SuperView;

public class RegisterStep1View extends SuperView {

    private FragmentRegisterStep1Binding binding;

    public RegisterStep1View(Context context) {
        super(context);
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_register_step_1, this, true);
    }

    public FragmentRegisterStep1Binding getBinding() {
        return binding;
    }

}
