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
import com.ham.sporz.view.BubbleActivity;
import com.ham.sporz.view.DoctorSelectionActivity;
import com.ham.sporz.view.MutantSelectionActivity;
import com.ham.sporz.view.ShowAllPersActivity;
import com.ham.sporz.view.ShowResultActivity;
import com.ham.sporz.view.SimpleSelectionActivity;
import com.ham.sporz.viewmodel.BubbleViewModel;
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
//                Intent intent = new Intent(MainActivity.this, ShowResultActivity.class);
//                Intent intent = new Intent(MainActivity.this, MutantSelectionActivity.class);
//                Intent intent = new Intent(MainActivity.this, DoctorSelectionActivity.class);
//                Intent intent = new Intent(MainActivity.this, SimpleSelectionActivity.class);
                Intent intent = new Intent(MainActivity.this, BubbleActivity.class);
//                Intent intent = new Intent(MainActivity.this, ShowAllPersActivity.class);
                Game game = new Game();
                game.addPlayer("Martin");
                game.addPlayer("Pierre");
                game.addPlayer("Marie");
                game.addPlayer("Michel");
                game.addPlayer("Julie");
                game.addPlayer("Julie");
                game.addPlayer("Julie");
                game.addPlayer("Julie");
                game.addPlayer("Julie");
                game.addPlayer("Julie");
                game.addPlayer("Julie");
                game.addPlayer("Julie");
                game.addPlayer("Julie");
                game.addPlayer("Julie");
                game.addPlayer("Julie");
                game.addPlayer("Julie");
                game.addPlayer("Julie");
                game.addPlayer("Julie");
                game.addPlayer("Julie");
                game.addPlayer("Julie");
                game.addPlayer("Julie");
                game.getPlayer(0).setRoleGenome(Role.SPY, Genome.HOST);
                game.getPlayer(0).mutate();
                game.getPlayer(1).setRoleGenome(Role.DOCTOR, Genome.NORMAL);
                game.getPlayer(1).kill();
                game.getPlayer(2).setRoleGenome(Role.DOCTOR, Genome.RESISTANT);
                game.getPlayer(2).paralyze();
                game.getPlayer(3).setRoleGenome(Role.GENETICIST, Genome.NORMAL);
                game.getPlayer(3).infect();
                game.getPlayer(4).setRoleGenome(Role.MUTANT_BASE, Genome.HOST);
                game.getPlayer(4).infect();
                game.getPlayer(5).setRoleGenome(Role.MUTANT_BASE, Genome.HOST);
                game.getPlayer(5).infect();
                game.getPlayer(6).setRoleGenome(Role.MUTANT_BASE, Genome.HOST);
                game.getPlayer(6).infect();
                game.getPlayer(7).setRoleGenome(Role.MUTANT_BASE, Genome.HOST);
                game.getPlayer(7).infect();
                game.getPlayer(8).setRoleGenome(Role.MUTANT_BASE, Genome.HOST);
                game.getPlayer(9).setRoleGenome(Role.MUTANT_BASE, Genome.HOST);
                game.getPlayer(10).setRoleGenome(Role.MUTANT_BASE, Genome.HOST);
                game.getPlayer(11).setRoleGenome(Role.MUTANT_BASE, Genome.HOST);
                game.getPlayer(12).setRoleGenome(Role.MUTANT_BASE, Genome.HOST);
                game.getPlayer(13).setRoleGenome(Role.MUTANT_BASE, Genome.HOST);
                game.getPlayer(14).setRoleGenome(Role.MUTANT_BASE, Genome.HOST);
                game.getPlayer(15).setRoleGenome(Role.MUTANT_BASE, Genome.HOST);
                game.getPlayer(16).setRoleGenome(Role.MUTANT_BASE, Genome.HOST);
                game.getPlayer(17).setRoleGenome(Role.MUTANT_BASE, Genome.HOST);
                game.getPlayer(18).setRoleGenome(Role.MUTANT_BASE, Genome.HOST);
                game.getPlayer(19).setRoleGenome(Role.MUTANT_BASE, Genome.HOST);
                game.setNewTurn(TurnType.NIGHT, 0, ActionType.GENETICIST);
                intent.putExtra("currentGame", game);
                startActivity(intent);
            }
        });
    }
}
