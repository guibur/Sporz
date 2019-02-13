package com.ham.sporz.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.ham.sporz.R;
import com.ham.sporz.databinding.BubbleLayoutBinding;
import com.ham.sporz.databinding.ComputerScientistLayoutBinding;
import com.ham.sporz.viewmodel.BubbleViewModel;
import com.ham.sporz.viewmodel.ComputerScientistViewModel;

public class ComputerScientistActivity extends AbstractMainActivity {
    private static String TAG = "BubbleActivity";

    ComputerScientistViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ComputerScientistLayoutBinding computerScientistLayoutBinding = DataBindingUtil.setContentView(this, R.layout.computer_scientist_layout);
        final Intent i = getIntent();
        mViewModel = ViewModelProviders.of(this).get(ComputerScientistViewModel.class);
        mViewModel.addIntent(i);
        computerScientistLayoutBinding.setViewModel(mViewModel);

        super.addObservers(mViewModel);
    }
}
