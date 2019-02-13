package com.ham.sporz.view;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ham.sporz.conductor.ActivityType;
import com.ham.sporz.viewmodel.AbstractMainViewModel;

public abstract class AbstractMainActivity extends AppCompatActivity {
    private static String TAG = "AbstractMainActivity";

    AbstractMainViewModel mViewModel;

    protected void addObservers(AbstractMainViewModel viewModel) {
        mViewModel = viewModel;

        final Observer finishedObserver = new Observer() {
            @Override
            public void onChanged(@Nullable Object o) {
                finish();
            }
        };
        mViewModel.getIsFinished().observe(this, finishedObserver);

        final Observer showGMObserver = new Observer() {
            @Override
            public void onChanged(@Nullable Object o) {
                Intent intent = new Intent(AbstractMainActivity.this, ShowAllPersActivity.class);
                intent.putExtra("currentGame", mViewModel.getCurrentGame());
                startActivity(intent);
            }
        };
        mViewModel.dispAllPersActivity().observe(this, showGMObserver);

        final Observer<ActivityType> nextActivityObserver = new Observer<ActivityType>() {
            @Override
            public void onChanged(@Nullable ActivityType nextActivity) {
                Intent intent = null;
                switch (nextActivity){
                    case SIMPLE_SELECTION:
                        intent = new Intent(AbstractMainActivity.this, SimpleSelectionActivity.class);
                        break;
                    case MUTANT_SELECTION:
                        intent = new Intent(AbstractMainActivity.this, MutantSelectionActivity.class);
                        break;
                    case DOCTOR_SELECTION:
                        intent = new Intent(AbstractMainActivity.this, DoctorSelectionActivity.class);
                        break;
                    case SHOW_RESULT:
                        intent = new Intent(AbstractMainActivity.this, ShowResultActivity.class);
                        break;
                    case BUBBLE:
                        intent = new Intent(AbstractMainActivity.this, BubbleActivity.class);
                        break;
                    case COMPUTER_SCIENTIST:
                        intent = new Intent(AbstractMainActivity.this, ComputerScientistActivity.class);
                        break;
                    case VOTE:
                    default:
                        return;
                }
                intent.putExtra("currentGame", mViewModel.getNextGame());
                startActivity(intent);
            }
        };
        mViewModel.getNextActivity().observe(this, nextActivityObserver);
    }
}
