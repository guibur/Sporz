package com.ham.sporz.view.adapter;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.view.MotionEvent;
import android.view.View;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.ham.sporz.R;
import com.ham.sporz.model.enums.Genome;
import com.ham.sporz.model.enums.Role;
import com.ham.sporz.viewmodel.enums.Background;
import com.ham.sporz.viewmodel.enums.Symbol;

public class CardBindingAdapters{

    @BindingAdapter("android:show")
    public static void  convertBoolToView(View view, boolean show){
        if (show){
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }

    @BindingAdapter("android:genome")
    public static void showGenomeImage(ImageView view, Genome genome){
        view.setVisibility(View.VISIBLE);
        switch (genome){
            case NORMAL:
                view.setVisibility(View.GONE);
                break;
            case HOST:
                view.setImageResource(R.drawable.host);
                break;
            case RESISTANT:
                view.setImageResource(R.drawable.resistant);
                break;
        }
    }

    @BindingAdapter("android:role")
    public static void showRoleImage(ImageView view, Role role){
        switch (role){
            case ASTRONAUT:
                view.setImageResource(R.drawable.astronaut);
                break;
            case MUTANT_BASE:
                view.setImageResource(R.drawable.sporz); // TODO: find image
                break;
            case DOCTOR:
                view.setImageResource(R.drawable.doctor);
                break;
            case PSYCHOLOGIST:
                view.setImageResource(R.drawable.psychologist);
                break;
            case GENETICIST:
                view.setImageResource(R.drawable.geneticist);
                break;
            case COMPUTER_SCIENTIST:
                view.setImageResource(R.drawable.comptuter_scientist);
                break;
            case SPY:
                view.setImageResource(R.drawable.astronaut); // TODO: find image
                break;
            case DETECTIVE:
                view.setImageResource(R.drawable.astronaut); // TODO: find image
                break;
            case HACKER:
                view.setImageResource(R.drawable.hacker);
                break;
            case TRAITOR:
                view.setImageResource(R.drawable.astronaut); // TODO: find image
                break;
        }
    }

    @BindingAdapter("android:card_background")
    public static void setCardBackgroundFromLiveData(View view, MutableLiveData<Background> bg_ld) {
        setCardBackground(view, bg_ld.getValue());
    }

    public static void setCardBackground(View view, Background bg) {
        switch (bg){
            case DARK:
                view.setBackgroundResource(R.drawable.card_shape_dark);
                break;
            case ACCENT:
                view.setBackgroundResource(R.drawable.card_shape_accent);
                break;
            case NORMAL:
            default:
                view.setBackgroundResource(R.drawable.card_shape_normal);
                break;
        }
    }

    @BindingAdapter("android:symbol")
    public static void setSymbolFromLiveData(ImageView view, MutableLiveData<Symbol> symbol_ld){
        setSymbol(view, symbol_ld.getValue());
    }

    public static void setSymbol(ImageView view, Symbol symbol){
        switch (symbol){
            case ROUND:
                view.setImageResource(R.drawable.to_select);
                break;
            case INSPECT:
                view.setImageResource(R.drawable.inspect); // TODO find img
                break;
            case DEAD:
                view.setImageResource(R.drawable.dead);
                break;
            case NONE:
                view.setImageDrawable(null);
                break;
        }
    }
}
