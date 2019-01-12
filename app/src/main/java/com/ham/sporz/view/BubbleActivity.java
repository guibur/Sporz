package com.ham.sporz.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.ham.sporz.R;
import com.ham.sporz.databinding.BubbleLayoutBinding;
import com.ham.sporz.viewmodel.BubbleViewModel;

public class BubbleActivity extends AbstractMainActivity {
    private static String TAG = "BubbleActivity";

    BubbleViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BubbleLayoutBinding bubbleLayoutBinding = DataBindingUtil.setContentView(this, R.layout.bubble_layout);
        final Intent i = getIntent();
        mViewModel = ViewModelProviders.of(this).get(BubbleViewModel.class);
        mViewModel.addIntent(i);
        bubbleLayoutBinding.setViewModel(mViewModel);

        super.addObservers(mViewModel);
    }
}
