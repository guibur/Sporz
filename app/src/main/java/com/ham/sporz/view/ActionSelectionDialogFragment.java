package com.ham.sporz.view;

import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.ham.sporz.R;
import com.ham.sporz.databinding.ActionSelectionDialogBinding;
import com.ham.sporz.view.adapter.CardBindingAdapters;
import com.ham.sporz.viewmodel.ActionSelectionDialogViewModel;
import com.ham.sporz.viewmodel.enums.Background;

import static com.ham.sporz.databinding.ActionSelectionDialogBinding.bind;
import static com.ham.sporz.databinding.ActionSelectionDialogBinding.inflate;

public class ActionSelectionDialogFragment extends DialogFragment {
    ActionSelectionDialogViewModel mViewModel;
    View mMainButton;
    View mKillButton;
    View mParalyseButton;
    View mInfectButton;

    public void attachViewModel(ActionSelectionDialogViewModel viewModel){
        mViewModel = viewModel;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        ActionSelectionDialogBinding binding = DataBindingUtil.inflate(
                getActivity().getLayoutInflater(),
                R.layout.action_selection_dialog,
                null, false);
        binding.setViewModel(mViewModel);
        builder.setView(binding.getRoot())
                .setPositiveButton("V", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mViewModel.onValidate();
                        dismiss();
                    }
                })
                .setNegativeButton("X", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mViewModel.onCancel();
                        dismiss();
                    }
                });

        // Create the AlertDialog object and return it
        AlertDialog resultDialog = builder.create();
        resultDialog.show();
        resultDialog.getButton(Dialog.BUTTON_POSITIVE)
                .setBackgroundResource(R.drawable.continue_button);
        resultDialog.getButton(Dialog.BUTTON_NEGATIVE)
                .setBackgroundResource(R.drawable.return_button);
        resultDialog.getButton(Dialog.BUTTON_NEGATIVE)
                .setElevation(0);
        
        mMainButton = binding.getRoot().findViewById(R.id.main_button_frame);
        mKillButton = binding.getRoot().findViewById(R.id.kill_button_frame);
        mParalyseButton = binding.getRoot().findViewById(R.id.paralyse_button_frame);
        mInfectButton = binding.getRoot().findViewById(R.id.infect_button_frame);

        final Observer<Background> changeMainBackgroundObserver = new Observer<Background>() {
            @Override
            public void onChanged(@Nullable final Background background) {
                CardBindingAdapters.setCardBackground(mMainButton, background);
            }
        };
        mViewModel.getMainBackground().observe(this, changeMainBackgroundObserver);

        final Observer<Background> changeKillBackgroundObserver = new Observer<Background>() {
            @Override
            public void onChanged(@Nullable final Background background) {
                CardBindingAdapters.setCardBackground(mKillButton, background);
            }
        };
        mViewModel.getKillBackground().observe(this, changeKillBackgroundObserver);

        final Observer<Background> changeParalyseBackgroundObserver = new Observer<Background>() {
            @Override
            public void onChanged(@Nullable final Background background) {
                CardBindingAdapters.setCardBackground(mParalyseButton, background);
            }
        };
        mViewModel.getParalyseBackground().observe(this, changeParalyseBackgroundObserver);

        final Observer<Background> changeInfectBackgroundObserver = new Observer<Background>() {
            @Override
            public void onChanged(@Nullable final Background background) {
                CardBindingAdapters.setCardBackground(mInfectButton, background);
            }
        };
        mViewModel.getInfectBackground().observe(this, changeInfectBackgroundObserver);

        return resultDialog;
    }
}