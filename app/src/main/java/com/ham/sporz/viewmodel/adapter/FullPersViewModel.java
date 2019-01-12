package com.ham.sporz.viewmodel.adapter;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.ham.sporz.model.enums.Genome;
import com.ham.sporz.model.Player;
import com.ham.sporz.model.enums.Role;
import com.ham.sporz.viewmodel.enums.Background;

public class FullPersViewModel extends ViewModel implements CardViewModel {
    private Player mPlayer;
    private MutableLiveData<Background> mBackground = new MutableLiveData<>();

    public FullPersViewModel(Player player) {
        mPlayer = player;
    }

    public boolean showDead(){
        return mPlayer.isDead();
    }

    public boolean showParalyzed(){
        return mPlayer.isParalyzed();
    }

    public boolean showInfected(){
        return mPlayer.isInfected();
    }

    public boolean showSporz(){
        return mPlayer.isMutant();
    }

    public String getName(){
        return mPlayer.getName();
    }

    public int getRoleTextId(){
        return mPlayer.getRole().getStringId();
    }

    public Role getRole(){
        return mPlayer.getRole();
    }

    public Genome getGenome(){
        return mPlayer.getGenome();
    }

    public boolean isClickable(){
        return false;
    }

    public MutableLiveData<Background> getBackground() {
        updateBackground();
        return mBackground;
    }

    private void updateBackground(){
        if (mPlayer.isMutant()){
            mBackground.setValue(Background.DARK);
        }else{
            mBackground.setValue(Background.NORMAL);
        }
    }

    @Override
    public boolean showInactiveFilter() {
        return mPlayer.isDead();
    }

}
