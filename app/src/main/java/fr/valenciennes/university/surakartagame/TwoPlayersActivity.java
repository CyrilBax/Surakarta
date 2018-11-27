package fr.valenciennes.university.surakartagame;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;

public class TwoPlayersActivity extends AppCompatActivity {

    private Chronometer chronometer;
    private MenuItem myMenuItem;
    private long pauseOffset;
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_players);

        View view = getLayoutInflater().inflate(R.layout.title, null);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);

        TextView Title = (TextView) view.findViewById(R.id.actionbar_title);
        Title.setText("Let's Play !");

        getSupportActionBar().setCustomView(view, params);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        chronometer = findViewById(R.id.chronometer);
        chronometer.setFormat("%s");
        chronometer.setBase(SystemClock.elapsedRealtime());

        startChronometer();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_game, menu);
        myMenuItem = menu.findItem(R.id.action_game_menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_game_menu:
                myMenuItem.setIcon(android.R.drawable.ic_media_play);
                pauseChronometer();

                CharSequence menuChoices[] = new CharSequence[]{"Resume", "Restart", "Quit"};

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Game Menu")
                        .setCancelable(false);
                builder.setItems(menuChoices, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                myMenuItem.setIcon(android.R.drawable.ic_media_pause);
                                startChronometer();
                                break;
                            case 1:
                                myMenuItem.setIcon(android.R.drawable.ic_media_pause);
                                resetChronometer();
                                startChronometer();
                                break;
                            case 2:
                                Intent rulesActivity = new Intent(TwoPlayersActivity.this, MainActivity.class);
                                startActivity(rulesActivity);
                                break;
                            default:
                                break;
                        }
                    }
                });
                builder.show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void startChronometer() {
        if (!running) {
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            running = true;
        }
    }

    public void pauseChronometer() {
        if (running) {
            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
            running = false;
        }
    }

    public void resetChronometer() {
        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseOffset = 0;
    }

}
