package com.ham.sporz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
                startActivity(intent);
            }
        });
//        setContentView(R.layout.test_layout);
//        ImageView img_sporz = (ImageView) findViewById(R.id.img_sporz);
//        img_sporz.setColorFilter(0xFF880000, PorterDuff.Mode.MULTIPLY);
    }
}
