package com.pjcribbin.gaelichurlingcounter;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

public class ScoreActivity extends AppCompatActivity implements Serializable {
    private int team1Goals = 0, team1Points = 0, team1Score = 0;
    private int team2Goals = 0, team2Points = 0, team2Score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTeamNamesAndColours();
        showIntroToast();
        setUpLongClickListeners();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setContentView(R.layout.score_screen);

        setTeamNamesAndColours();
        setUpLongClickListeners();
        updateTeam1();
        updateTeam2();
    }

    public void setTeamNamesAndColours() {
        Intent intent = getIntent();

        setContentView(R.layout.score_screen);

        TextView team1EnglishName = (TextView) findViewById(R.id.team_1_english_name);
        team1EnglishName.setText(intent.getStringExtra("t1English"));

        TextView team1IrishName = (TextView) findViewById(R.id.team_1_irish_name);
        team1IrishName.setText(intent.getStringExtra("t1Irish"));

        TextView team2EnglishName = (TextView) findViewById(R.id.team_2_english_name);
        team2EnglishName.setText(intent.getStringExtra("t2English"));

        TextView team2IrishName = (TextView) findViewById(R.id.team_2_irish_name);
        team2IrishName.setText(intent.getStringExtra("t2Irish"));

        View team1Colour1 = findViewById(R.id.colour_1_team_1);
        team1Colour1.setBackgroundColor(intent.getIntExtra("t1Primary", 0));

        View team1Colour2 = findViewById(R.id.colour_2_team_1);
        team1Colour2.setBackgroundColor(intent.getIntExtra("t1Primary", 0));

        View team1Colour4 = findViewById(R.id.colour_4_team_1);
        team1Colour4.setBackgroundColor(intent.getIntExtra("t1Secondary", 0));

        // If team has two colours
        if (intent.getIntExtra("t1Tertiary", 0) == 0x00000000) {
            View team1Colour3 = findViewById(R.id.colour_3_team_1);
            team1Colour3.setBackgroundColor(intent.getIntExtra("t1Primary", 0));

            View team1Colour5 = findViewById(R.id.colour_5_team_1);
            team1Colour5.setBackgroundColor(intent.getIntExtra("t1Secondary", 0));

            View team1Colour6 = findViewById(R.id.colour_6_team_1);
            team1Colour6.setBackgroundColor(intent.getIntExtra("t1Secondary", 0));
        }

        // If team has three colours
        else {
            View team1Colour3 = findViewById(R.id.colour_3_team_1);
            team1Colour3.setBackgroundColor(intent.getIntExtra("t1Secondary", 0));

            View team1Colour5 = findViewById(R.id.colour_5_team_1);
            team1Colour5.setBackgroundColor(intent.getIntExtra("t1Tertiary", 0));

            View team1Colour6 = findViewById(R.id.colour_6_team_1);
            team1Colour6.setBackgroundColor(intent.getIntExtra("t1Tertiary", 0));
        }

        View team2Colour1 = findViewById(R.id.colour_1_team_2);
        team2Colour1.setBackgroundColor(intent.getIntExtra("t2Primary", 0));

        View team2Colour2 = findViewById(R.id.colour_2_team_2);
        team2Colour2.setBackgroundColor(intent.getIntExtra("t2Primary", 0));

        View team2Colour4 = findViewById(R.id.colour_4_team_2);
        team2Colour4.setBackgroundColor(intent.getIntExtra("t2Secondary", 0));

        // If team has two colours
        if (intent.getIntExtra("t2Tertiary", 0) == 0x00000000) {
            View team2Colour3 = findViewById(R.id.colour_3_team_2);
            team2Colour3.setBackgroundColor(intent.getIntExtra("t2Primary", 0));

            View team2Colour5 = findViewById(R.id.colour_5_team_2);
            team2Colour5.setBackgroundColor(intent.getIntExtra("t2Secondary", 0));

            View team2Colour6 = findViewById(R.id.colour_6_team_2);
            team2Colour6.setBackgroundColor(intent.getIntExtra("t2Secondary", 0));
        }

        // If team has three colours
        else {
            View team2Colour3 = findViewById(R.id.colour_3_team_2);
            team2Colour3.setBackgroundColor(intent.getIntExtra("t2Secondary", 0));

            View team2Colour5 = findViewById(R.id.colour_5_team_2);
            team2Colour5.setBackgroundColor(intent.getIntExtra("t2Tertiary", 0));

            View team2Colour6 = findViewById(R.id.colour_6_team_2);
            team2Colour6.setBackgroundColor(intent.getIntExtra("t2Tertiary", 0));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_reset:
                team1Goals = 0;
                team1Points = 0;
                team1Score = 0;
                team2Goals = 0;
                team2Points = 0;
                team2Score = 0;

                updateTeam1();
                updateTeam2();

                Toast scoreResetToast = Toast.makeText(this, "Scores reset", Toast.LENGTH_SHORT);
                scoreResetToast.show();
                break;

            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                break;

            default:
                break;
        }
        return true;
    }

    private void setUpLongClickListeners() {
        TextView team1GoalsTV = (TextView) findViewById(R.id.team_1_goals);
        team1GoalsTV.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                decrementTeam1Goals();
                return true;
            }
        });

        TextView team1PointsTV = (TextView) findViewById(R.id.team_1_points);
        team1PointsTV.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                decrementTeam1Points();
                return true;
            }
        });

        TextView team2GoalsTV = (TextView) findViewById(R.id.team_2_goals);
        team2GoalsTV.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                decrementTeam2Goals();
                return true;
            }
        });

        TextView team2PointsTV = (TextView) findViewById(R.id.team_2_points);
        team2PointsTV.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                decrementTeam2Points();
                return true;
            }
        });
    }

    public void incrementTeam1Goals(View v) {
        if (team1Goals < 99) {
            team1Goals++;
            team1Score += 3;
            updateTeam1();
        } else
            showMaxErrorToast("Goals");
    }

    private void decrementTeam1Goals() {
        if (team1Goals > 0) {
            team1Goals--;
            team1Score -= 3;
            updateTeam1();
        } else
            showMinErrorToast("Goals");
    }

    public void incrementTeam1Points(View v) {
        if (team1Points < 99) {
            team1Points++;
            team1Score++;
            updateTeam1();
        } else
            showMaxErrorToast("Points");
    }

    private void decrementTeam1Points() {
        if (team1Points > 0) {
            team1Points--;
            team1Score--;
            updateTeam1();
        } else
            showMinErrorToast("Points");
    }

    public void incrementTeam2Goals(View v) {
        if (team2Goals < 99) {
            team2Goals++;
            team2Score += 3;
            updateTeam2();
        } else
            showMaxErrorToast("Goals");
    }

    private void decrementTeam2Goals() {
        if (team2Goals > 0) {
            team2Goals--;
            team2Score -= 3;
            updateTeam2();
        } else
            showMinErrorToast("Goals");
    }

    public void incrementTeam2Points(View v) {
        if (team2Points < 99) {
            team2Points++;
            team2Score++;
            updateTeam2();
        } else
            showMaxErrorToast("Points");
    }

    private void decrementTeam2Points() {
        if (team2Points > 0) {
            team2Points--;
            team2Score--;
            updateTeam2();
        } else
            showMinErrorToast("Points");
    }

    private void updateTeam1() {
        TextView team1GoalsTextView = (TextView) findViewById(R.id.team_1_goals);
        TextView team1PointsTextView = (TextView) findViewById(R.id.team_1_points);
        TextView team1ScoreTextView = (TextView) findViewById(R.id.team_1_score);

        team1GoalsTextView.setText(String.valueOf(team1Goals));
        team1PointsTextView.setText(String.valueOf(team1Points));
        team1ScoreTextView.setText(String.valueOf(team1Score));
    }

    private void updateTeam2() {
        TextView team2GoalsTextView = (TextView) findViewById(R.id.team_2_goals);
        TextView team2PointsTextView = (TextView) findViewById(R.id.team_2_points);
        TextView team2ScoreTextView = (TextView) findViewById(R.id.team_2_score);

        team2GoalsTextView.setText(String.valueOf(team2Goals));
        team2PointsTextView.setText(String.valueOf(team2Points));
        team2ScoreTextView.setText(String.valueOf(team2Score));
    }

    //Displays toast after team selection giving instructions
    private void showIntroToast() {
        Toast infoToast = Toast.makeText(this, "Tap a number to increment it!\nHold a number to decrement it!", Toast.LENGTH_LONG);
        infoToast.show();
    }

    //Displays toast if max of 99 points/goals is reached
    private void showMaxErrorToast(String goalsOrPoints) {
        Toast maxToast = Toast.makeText(this, goalsOrPoints + " cannot exceed 99", Toast.LENGTH_SHORT);
        maxToast.show();
    }

    //Displays toast if min of 0 points/goals is reached
    private void showMinErrorToast(String goalsOrPoints) {
        Toast maxToast = Toast.makeText(this, goalsOrPoints + " at 0", Toast.LENGTH_SHORT);
        maxToast.show();
    }
}
