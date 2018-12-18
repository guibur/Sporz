package com.ham.sporz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ham.sporz.model.Game;
import com.ham.sporz.model.enums.Genome;
import com.ham.sporz.model.enums.Role;
import com.ham.sporz.view.ShowAllPersActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mainStartButton = (Button) findViewById(R.id.main_start_button);
        mainStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShowAllPersActivity.class);
                Game game = new Game();
                game.addPlayer("Martin Dupont", "Mt");
                game.addPlayer("Pierre Dupont", "P");
                game.addPlayer("Marie Michel", "Mi");
                game.getPlayer(0).setRoleGenome(Role.GENETICIST, Genome.HOST);
                game.getPlayer(0).mutate();
                game.getPlayer(2).setRoleGenome(Role.SPY, Genome.RESISTANT);
                game.getPlayer(2).paralyze();
                game.getPlayer(1).setRoleGenome(Role.MUTANT_BASE, Genome.NORMAL);
                game.getPlayer(1).kill();
                intent.putExtra("currentGame", game);
                Log.e("MyLog", "created intent");
                startActivity(intent);
            }
        });
    }
}
