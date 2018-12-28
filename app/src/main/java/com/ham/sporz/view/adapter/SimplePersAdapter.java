package com.ham.sporz.view.adapter;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ham.sporz.R;
import com.ham.sporz.databinding.SimplePersBinding;
import com.ham.sporz.model.Player;
import com.ham.sporz.viewmodel.SelectionPlayerViewModel;
import com.ham.sporz.viewmodel.adapter.SimplePersViewModel;
import com.ham.sporz.viewmodel.enums.Background;
import com.ham.sporz.viewmodel.enums.Symbol;

import java.util.ArrayList;

public class SimplePersAdapter extends RecyclerView.Adapter<SimplePersAdapter.MyViewHolder> {
    private AppCompatActivity mContext;
    private ArrayList<SimplePersViewModel> mPlayers;
    private SelectionPlayerViewModel mParentViewModel;

    public SimplePersAdapter(AppCompatActivity context, SelectionPlayerViewModel viewModel) {
        mContext = context;
        mParentViewModel = viewModel;
        mPlayers = viewModel.getPersList();
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mPlayers.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        SimplePersBinding mBinding;
        View backgroundView;
        ImageView symbolView;

        public MyViewHolder(final SimplePersBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            backgroundView = binding.getRoot().findViewById(R.id.card);
            symbolView = binding.getRoot().findViewById(R.id.symbol);
        }
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.mBinding.setPlayer(mPlayers.get(position));
        holder.mBinding.setSelection(mParentViewModel);

        final Observer<Background> changeBackgroundObserver = new Observer<Background>() {
            @Override
            public void onChanged(@Nullable final Background background) {
                CardBindingAdapters.setCardBackground(holder.backgroundView, background);
            }
        };
        mPlayers.get(position).getBackground().observe(mContext, changeBackgroundObserver);

        final Observer<Symbol> changeSymbolObserver = new Observer<Symbol>() {
            @Override
            public void onChanged(@Nullable final Symbol symbol) {
                CardBindingAdapters.setSymbol(holder.symbolView, symbol);
            }
        };
        mPlayers.get(position).getSymbol().observe(mContext, changeSymbolObserver);
    }


    @Override
    public SimplePersAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        SimplePersBinding binding = DataBindingUtil.inflate(
                layoutInflater, R.layout.simple_pers, parent, false);
        // create a new view
        MyViewHolder vh = new MyViewHolder(binding);
        return vh;
    }
}
