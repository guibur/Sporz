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
import com.ham.sporz.view.adapter.SimplePersAdapter;
import com.ham.sporz.viewmodel.MultipleSelectionViewModel;

public class MultipleSelectionActivity extends AppCompatActivity {
    private static String TAG = "MultipleSelectionActivity";

    RecyclerView mRecyclerView;
    MultipleSelectionViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutRecyclerBinding layoutBinding = DataBindingUtil.setContentView(this, R.layout.layout_recycler);
        final Intent i = getIntent();
        mViewModel = ViewModelProviders.of(this).get(MultipleSelectionViewModel.class);
        mViewModel.addIntent(i);
        layoutBinding.setViewModel(mViewModel); // Bind for continue and return buttons.

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.setAdapter(new SimplePersAdapter(this, mViewModel));

        final Observer<Boolean> finishedObserver = new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable final Boolean isFinished) {
                finish();
            }
        };
        mViewModel.getIsFinished().observe(this, finishedObserver);

        final Observer showGMObserver = new Observer() {
            @Override
            public void onChanged(@Nullable Object o) {
                Log.e(TAG, "message received.");
                Intent intent = new Intent(MultipleSelectionActivity.this, ShowAllPersActivity.class);
                intent.putExtra("currentGame", mViewModel.getCurrentGame());
                startActivity(intent);
            }
        };
        mViewModel.dispAllPersActivity().observe(this, showGMObserver);

        final Observer showSelectionDialog = new Observer() {
            @Override
            public void onChanged(@Nullable Object o) {
                ActionSelectionDialogFragment dialogBox = new ActionSelectionDialogFragment();
                dialogBox.attachViewModel(mViewModel.getActionSelectionVM());
                dialogBox.show(getSupportFragmentManager(), "AAAA");
            }
        };

        mViewModel.getShowActionSelectionDialog().observe(this, showSelectionDialog);
    }
}
