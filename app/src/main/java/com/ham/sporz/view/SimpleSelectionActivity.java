package com.ham.sporz.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.ham.sporz.R;
import com.ham.sporz.databinding.LayoutRecyclerBinding;
import com.ham.sporz.databinding.ShowResultBinding;
import com.ham.sporz.view.adapter.SimplePersAdapter;
import com.ham.sporz.viewmodel.AbstractSelectionPlayerViewModel;
import com.ham.sporz.viewmodel.ShowResultViewModel;
import com.ham.sporz.viewmodel.SimpleSelectionViewModel;

public class SimpleSelectionActivity extends AbstractMainActivity {
    private static String TAG = "SimpleSelectionActivity";

    RecyclerView mRecyclerView;
    AbstractSelectionPlayerViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        LayoutRecyclerBinding layoutBinding = DataBindingUtil.setContentView(this, R.layout.layout_recycler);
        final Intent i = getIntent();
        mViewModel = ViewModelProviders.of(this).get(SimpleSelectionViewModel.class);
        mViewModel.addIntent(i);
        layoutBinding.setViewModel(mViewModel); // Bind for continue and return buttons.

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.setAdapter(new SimplePersAdapter(this, mViewModel));

        super.addObservers(mViewModel);
    }
}
