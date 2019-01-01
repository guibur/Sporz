package com.ham.sporz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ham.sporz.model.Game;
import com.ham.sporz.model.enums.ActionType;
import com.ham.sporz.model.enums.Genome;
import com.ham.sporz.model.enums.Role;
import com.ham.sporz.model.enums.TurnType;
import com.ham.sporz.view.MutantSelectionActivity;
import com.ham.sporz.view.ShowAllPersActivity;
import com.ham.sporz.view.SimpleSelectionActivity;
import com.ham.sporz.viewmodel.MutantSelectionPlayerViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mainStartButton = (Button) findViewById(R.id.main_start_button);
        mainStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MutantSelectionActivity.class);
//                Intent intent = new Intent(MainActivity.this, SimpleSelectionActivity.class);
//                Intent intent = new Intent(MainActivity.this, ShowAllPersActivity.class);
                Game game = new Game();
                game.addPlayer("Martin");
                game.addPlayer("Pierre");
                game.addPlayer("Marie");
                game.getPlayer(0).setRoleGenome(Role.GENETICIST, Genome.HOST);
                game.getPlayer(0).mutate();
                game.getPlayer(2).setRoleGenome(Role.SPY, Genome.RESISTANT);
                game.getPlayer(2).paralyze();
                game.getPlayer(1).setRoleGenome(Role.MUTANT_BASE, Genome.NORMAL);
                game.getPlayer(1).kill();
                game.setNewTurn(TurnType.NIGHT, 0, ActionType.GENETICIST);
                intent.putExtra("currentGame", game);
                startActivity(intent);
            }
        });
    }
}
