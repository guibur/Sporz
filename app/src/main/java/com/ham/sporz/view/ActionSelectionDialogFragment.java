package com.ham.sporz.view;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;

import com.ham.sporz.R;
import com.ham.sporz.viewmodel.MultipleSelectionViewModel;

public class ActionSelectionDialogFragment extends DialogFragment {
    MultipleSelectionViewModel mViewModel;

    public void attachViewModel(MultipleSelectionViewModel viewModel){
        mViewModel = viewModel;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.action_selection_dialog, null))
                .setPositiveButton("V", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dismiss();
                    }
                })
                .setNegativeButton("X", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
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

        return resultDialog;
    }
}
