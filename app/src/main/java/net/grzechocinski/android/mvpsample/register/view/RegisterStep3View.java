package net.grzechocinski.android.mvpsample.register.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import net.grzechocinski.android.mvpsample.R;
import net.grzechocinski.android.mvpsample.databinding.FragmentRegisterStep3Binding;
import net.grzechocinski.android.mvpsample.internal.mvp.view.SuperView;

public class RegisterStep3View extends SuperView {

    private FragmentRegisterStep3Binding binding;

    public RegisterStep3View(Context context) {
        super(context);
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_register_step_3, this, true);
    }

    public FragmentRegisterStep3Binding getBinding() {
        return binding;
    }
}
