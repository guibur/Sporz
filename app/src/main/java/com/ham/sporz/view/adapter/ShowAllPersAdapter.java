package com.ham.sporz.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.ham.sporz.R;
import com.ham.sporz.model.Player;

import java.util.ArrayList;

public class ShowAllPersAdapter extends RecyclerView.Adapter<ShowAllPersAdapter.MyViewHolder> {
    private ArrayList<Player> mPlayers = new ArrayList<Player>();

    public ShowAllPersAdapter() {
        mPlayers.add(new Player(0, "abc", "a"));
        mPlayers.add(new Player(1, "def", "d"));
        mPlayers.add(new Player(2, "ghi", "g"));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mPlayers.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mNameTextView;
        public TextView mAbbrevTextView;
        public ImageView mRoleImg;


        public MyViewHolder(final View viewIn) {
            super(viewIn);
            mNameTextView = (TextView) viewIn.findViewById(R.id.name);
            mAbbrevTextView = (TextView) viewIn.findViewById(R.id.abbrev);
            mRoleImg = (ImageView) viewIn.findViewById(R.id.role_img);
        }
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mNameTextView.setText(mPlayers.get(position).getName());
        holder.mAbbrevTextView.setText(mPlayers.get(position).getAbbrev());
    }


    @Override
    public ShowAllPersAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                              int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.show_all_pers, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }
}
