package fr.valenciennes.university.surakartagame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mBridge1 = null;
    private Button mBridge2 = null;
    private Button mBridge3 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBridge1 = (Button) findViewById(R.id.how_to_play);
        mBridge1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rulesActivity = new Intent(MainActivity.this, RulesActivity.class);
                startActivity(rulesActivity);
            }
        });

        mBridge2 = (Button) findViewById(R.id.one_player);
        mBridge2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent onePlayerActivity = new Intent(MainActivity.this, OnePlayerActivity.class);
                startActivity(onePlayerActivity);
            }
        });

        mBridge3 = (Button) findViewById(R.id.two_players);
        mBridge3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent twoPlayersActivity = new Intent(MainActivity.this, TwoPlayersActivity.class);
                startActivity(twoPlayersActivity);
            }
        });
    }
}
