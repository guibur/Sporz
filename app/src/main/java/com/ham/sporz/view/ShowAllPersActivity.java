package com.ham.sporz.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ham.sporz.R;
import com.ham.sporz.databinding.LayoutRecyclerBinding;
import com.ham.sporz.view.adapter.FullPersAdapter;
import com.ham.sporz.viewmodel.ShowAllPersViewModel;

public class ShowAllPersActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    ShowAllPersViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutRecyclerBinding layoutBinding = DataBindingUtil.setContentView(this, R.layout.layout_recycler);
        final Intent i = getIntent();
        mViewModel = ViewModelProviders.of(this).get(ShowAllPersViewModel.class);
        mViewModel.addIntent(i);
        layoutBinding.setViewModel(mViewModel);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.setAdapter(new FullPersAdapter(mViewModel.getPlayerList()));

        final Observer<Boolean> finishedObserver = new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable final Boolean isFinished) {
                finish();
            }
        };
        mViewModel.getIsFinished().observe(this, finishedObserver);
    }
}
