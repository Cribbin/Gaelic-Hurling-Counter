package com.pjcribbin.gaelichurlingcounter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    int team1Goals = 0, team1Points = 0, team1Score = 0;
    int team2Goals = 0, team2Points = 0, team2Score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast infoToast = Toast.makeText(this, "Tap a goals or points number to increment it!", Toast.LENGTH_LONG);
        infoToast.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void incrementTeam1Goals(View v){
        if(team1Goals < 99) {
            team1Goals++;
            team1Score += 3;
            updateTeam1();
        }

        else
            showMaxErrorToast("Goals");
    }

    public void incrementTeam1Points(View v){
        if(team1Points < 99) {
            team1Points++;
            team1Score++;
            updateTeam1();
        }

        else
            showMaxErrorToast("Points");
    }

    public void incrementTeam2Goals(View v){
        if(team2Goals < 99) {
            team2Goals++;
            team2Score += 3;
            updateTeam2();
        }

        else
            showMaxErrorToast("Goals");
    }

    public void incrementTeam2Points(View v){
        if(team2Points < 99) {
            team2Points++;
            team2Score++;
            updateTeam2();
        }

        else
            showMaxErrorToast("Points");
    }

    public void updateTeam1(){
        TextView team1GoalsTextView = (TextView) findViewById(R.id.team_1_goals);
        TextView team1PointsTextView = (TextView) findViewById(R.id.team_1_points);
        TextView team1ScoreTextView = (TextView) findViewById(R.id.team_1_score);

        team1GoalsTextView.setText(String.valueOf(team1Goals));
        team1PointsTextView.setText(String.valueOf(team1Points));
        team1ScoreTextView.setText(String.valueOf(team1Score));
    }

    public void updateTeam2(){
        TextView team2GoalsTextView = (TextView) findViewById(R.id.team_2_goals);
        TextView team2PointsTextView = (TextView) findViewById(R.id.team_2_points);
        TextView team2ScoreTextView = (TextView) findViewById(R.id.team_2_score);

        team2GoalsTextView.setText(String.valueOf(team2Goals));
        team2PointsTextView.setText(String.valueOf(team2Points));
        team2ScoreTextView.setText(String.valueOf(team2Score));
    }

    public void showMaxErrorToast(String goalsOrPoints) {
        Toast maxToast = Toast.makeText(this, goalsOrPoints + " cannot exceed 99", Toast.LENGTH_SHORT);
        maxToast.show();
    }
}
