package com.ham.sporz.view.adapter;

import android.view.MotionEvent;
import android.view.View;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.ham.sporz.R;
import com.ham.sporz.model.enums.Genome;
import com.ham.sporz.model.enums.Role;

public class CardBindingAdapters implements View.OnTouchListener{


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

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                v.setBackgroundResource(R.drawable.card_shape);
                break;
            case MotionEvent.ACTION_UP:

                //set color back to default
                v.setBackgroundResource(R.color.colorAccent_2);
                break;
        }
        return true;
    }
}
