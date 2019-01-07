package com.ham.sporz.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.ham.sporz.R;
import com.ham.sporz.databinding.ShowResultBinding;
import com.ham.sporz.viewmodel.ShowResultViewModel;

public class ShowResultActivity extends AbstractMainActivity {
    private static String TAG = "ShowResultActivity";

    ShowResultViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ShowResultBinding showResultBinding = DataBindingUtil.setContentView(this, R.layout.show_result);
        final Intent i = getIntent();
        mViewModel = ViewModelProviders.of(this).get(ShowResultViewModel.class);
        mViewModel.addIntent(i);
        showResultBinding.setViewModel(mViewModel);

        super.addObservers(mViewModel);
    }
}
