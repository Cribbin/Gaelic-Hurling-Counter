package com.pjcribbin.gaelichurlingcounter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    int team1Goals = 0, team1Points = 0, team1Score = 0;
    int team2Goals = 0, team2Points = 0, team2Score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        countyTeam[] teams = createTeams();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_menu);

        ListAdapter teamListAdapter = new TeamListAdapter(this, teams);

        ListView team1List = (ListView) findViewById(R.id.team_1_list);
        ListView team2List = (ListView) findViewById(R.id.team_2_list);

        team1List.setAdapter(teamListAdapter);
        team2List.setAdapter(teamListAdapter);

        team1List.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                        setContentView(R.layout.activity_main);
                        showIntroToast();
                    }
                }
        );
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

    public void incrementTeam1Goals(View v) {
        if (team1Goals < 99) {
            team1Goals++;
            team1Score += 3;
            updateTeam1();
        } else
            showMaxErrorToast("Goals");
    }

    public void incrementTeam1Points(View v) {
        if (team1Points < 99) {
            team1Points++;
            team1Score++;
            updateTeam1();
        } else
            showMaxErrorToast("Points");
    }

    public void incrementTeam2Goals(View v) {
        if (team2Goals < 99) {
            team2Goals++;
            team2Score += 3;
            updateTeam2();
        } else
            showMaxErrorToast("Goals");
    }

    public void incrementTeam2Points(View v) {
        if (team2Points < 99) {
            team2Points++;
            team2Score++;
            updateTeam2();
        } else
            showMaxErrorToast("Points");
    }

    public void updateTeam1() {
        TextView team1GoalsTextView = (TextView) findViewById(R.id.team_1_goals);
        TextView team1PointsTextView = (TextView) findViewById(R.id.team_1_points);
        TextView team1ScoreTextView = (TextView) findViewById(R.id.team_1_score);

        team1GoalsTextView.setText(String.valueOf(team1Goals));
        team1PointsTextView.setText(String.valueOf(team1Points));
        team1ScoreTextView.setText(String.valueOf(team1Score));
    }

    public void updateTeam2() {
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

    public countyTeam[] createTeams() {
        countyTeam[] teamArray = new countyTeam[32];

        countyTeam antrim = new countyTeam("Antrim", "Aontroim", "ANT");
        teamArray[0] = antrim;
        countyTeam armagh = new countyTeam("Armagh", "Ard Mhacha", "ARM");
        teamArray[1] = armagh;
        countyTeam carlow = new countyTeam("Carlow", "Ceatharlach", "CAR");
        teamArray[2] = carlow;
        countyTeam cavan = new countyTeam("Cavan", "An Cabhán", "CAV");
        teamArray[3] = cavan;
        countyTeam clare = new countyTeam("Clare", "An Clár", "CLA");
        teamArray[4] = clare;
        countyTeam cork = new countyTeam("Cork", "Corcaigh", "COR");
        teamArray[5] = cork;
        countyTeam derry = new countyTeam("Derry", "Doire", "DER");
        teamArray[6] = derry;
        countyTeam donegal = new countyTeam("Donegal", "Dún na nGall", "DON");
        teamArray[7] = donegal;
        countyTeam down = new countyTeam("Down", "An Dún", "DOW");
        teamArray[8] = down;
        countyTeam dublin = new countyTeam("Dublin", "Áth Cliath", "DUB");
        teamArray[9] = dublin;
        countyTeam fermanagh = new countyTeam("Fermanagh", "Fear Manach", "FER");
        teamArray[10] = fermanagh;
        countyTeam galway = new countyTeam("Galway", "Gaillimh", "GAL");
        teamArray[11] = galway;
        countyTeam kerry = new countyTeam("Kerry", "Ciarraí", "KER");
        teamArray[12] = kerry;
        countyTeam kildare = new countyTeam("Kildare", "Cill Dara", "KIL");
        teamArray[13] = kildare;
        countyTeam kilkenny = new countyTeam("Kilkenny", "Cill Chainnigh", "KIL");
        teamArray[14] = kilkenny;
        countyTeam laois = new countyTeam("Laois", "Laois", "LAO");
        teamArray[15] = laois;
        countyTeam leitrim = new countyTeam("Leitrim", "Liatroim", "LEI");
        teamArray[16] = leitrim;
        countyTeam limerick = new countyTeam("Limerick", "Luimneach", "LIM");
        teamArray[17] = limerick;
        countyTeam longford = new countyTeam("Longford", "An Longfort", "LON");
        teamArray[18] = longford;
        countyTeam louth = new countyTeam("Louth", "Lú", "LOU");
        teamArray[19] = louth;
        countyTeam mayo = new countyTeam("Mayo", "Maigh Eo", "MAY");
        teamArray[20] = mayo;
        countyTeam meath = new countyTeam("Meath", "An Mhí", "MEA");
        teamArray[21] = meath;
        countyTeam monaghan = new countyTeam("Monaghan", "Muineachán", "MON");
        teamArray[22] = monaghan;
        countyTeam offaly = new countyTeam("Offaly", "Uíbh Fhailí", "OFF");
        teamArray[23] = offaly;
        countyTeam roscommon = new countyTeam("Roscommon", "Ros Comáin", "ROS");
        teamArray[24] = roscommon;
        countyTeam sligo = new countyTeam("Sligo", "Sligeach", "SLI");
        teamArray[25] = sligo;
        countyTeam tipperary = new countyTeam("Tipperary", "Tiobraid Árann", "TIP");
        teamArray[26] = tipperary;
        countyTeam tyrone = new countyTeam("Tyrone", "Tír Eoghain", "TYR");
        teamArray[27] = tyrone;
        countyTeam waterford = new countyTeam("Waterford", "Port Láirge", "WAT");
        teamArray[28] = waterford;
        countyTeam westmeath = new countyTeam("Westmeath", "An Iarmhí", "WES");
        teamArray[29] = westmeath;
        countyTeam wexford = new countyTeam("Wexford", "Loch Garman", "WEX");
        teamArray[30] = wexford;
        countyTeam wicklow = new countyTeam("Wicklow", "Cill Mhantáin", "WIC");
        teamArray[31] = wicklow;

        return teamArray;
    }

    public void showIntroToast() {
        Toast infoToast = Toast.makeText(this, "Tap a goals or points number to increment it!", Toast.LENGTH_LONG);
        infoToast.show();
    }
}