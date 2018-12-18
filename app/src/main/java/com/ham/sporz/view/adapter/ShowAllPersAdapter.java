package com.ham.sporz.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.ham.sporz.R;
import com.ham.sporz.databinding.FullPersBinding;
import com.ham.sporz.model.Player;
import com.ham.sporz.viewmodel.FullPersViewModel;

import java.util.ArrayList;

public class ShowAllPersAdapter extends RecyclerView.Adapter<ShowAllPersAdapter.MyViewHolder> {
    private ArrayList<Player> mPlayers;

    public ShowAllPersAdapter(ArrayList<Player> listPlayer) {
        mPlayers = listPlayer;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mPlayers.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        FullPersBinding mBinding;

        public MyViewHolder(final FullPersBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mBinding.setPlayer(new FullPersViewModel(mPlayers.get(position)));
    }


    @Override
    public ShowAllPersAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                              int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        FullPersBinding binding = DataBindingUtil.inflate(
                layoutInflater, R.layout.full_pers, parent, false);
        // create a new view
        MyViewHolder vh = new MyViewHolder(binding);
        return vh;
    }
}
