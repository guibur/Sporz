package com.ham.sporz.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.view.View;

import com.ham.sporz.model.enums.Genome;
import com.ham.sporz.model.Player;
import com.ham.sporz.model.enums.Role;

public class FullPersViewModel extends ViewModel {
    private Player mPlayer;

    public FullPersViewModel(Player player) {
        mPlayer = player;
    }

    public int showDead(){
        if (mPlayer.isAlive()){
            return View.GONE;
        }else{
            return View.VISIBLE;
        }
    }

    public int showParalyzed(){
        if (mPlayer.isParalyzed()){
            return View.VISIBLE;
        }
        return View.GONE;
    }

    public int showInfected(){
        if (mPlayer.isInfected()){
            return View.VISIBLE;
        }
        return View.GONE;
    }

    public int showSporz(){
        if (mPlayer.isMutant()){
            return View.VISIBLE;
        }
        return View.GONE;
    }

    public String getName(){
        return mPlayer.getName();
    }

    public String getAbbrev(){
        return mPlayer.getAbbrev();
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

    public Background getBackground(boolean pressed){
        if (mPlayer.isAlive()){
            if (mPlayer.isMutant()){
                return pressed ? Background.DARK_PRESSED : Background.DARK;
            }else{
                return pressed ? Background.PRESSED : Background.NORMAL;
            }
        } else {
            if (mPlayer.isMutant()){
                return Background.DARK_INACTIVE;
            } else {
                return Background.INACTIVE;
            }
        }
    }
}
