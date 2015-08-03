package com.pjcribbin.gaelichurlingcounter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    int team1Goals = 0, team1Points = 0, team1Score = 0;
    int team2Goals = 0, team2Points = 0, team2Score = 0;
    boolean team1Selected = false;
    boolean team2Selected = false;
    int position1;
    int position2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final countyTeam[] teams = createTeams();

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
                        TextView selectedTeam = (TextView) findViewById(R.id.team_1_choice);
                        String selection = teams[position].getEnglishName();
                        position1 = position;

                        selectedTeam.setText(selection);
                        team1Selected = true;
                    }
                }
        );

        team2List.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                        TextView selectedTeam = (TextView) findViewById(R.id.team_2_choice);
                        String selection = teams[position].getEnglishName();
                        position2 = position;

                        selectedTeam.setText(selection);
                        team2Selected = true;
                    }
                }
        );

        Button chooseTeamsButton = (Button) findViewById(R.id.choose_teams_button);

        chooseTeamsButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        setPointsActivity(teams);
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
        int green = 0xff009900;
        int red = 0xffff0000;
        int yellow = 0xffffff00;
        int white = 0xffffffff;
        int black = 0xff000000;
        int orange = 0xffff8800;
        int blue = 0xff0000ff;
        int dBlue = 0xff000080;
        int lBlue = 0xff88b8fd;
        int maroon = 0xff990000;
        int amber = 0xffffd700;
        int purple = 0xff660099;

        countyTeam antrim = new countyTeam("Antrim", "Aontroim", "ANT", yellow, white);
        teamArray[0] = antrim;
        countyTeam armagh = new countyTeam("Armagh", "Ard Mhacha", "ARM", orange, white);
        teamArray[1] = armagh;
        countyTeam carlow = new countyTeam("Carlow", "Ceatharlach", "CAR", red, green);
        teamArray[2] = carlow;
        countyTeam cavan = new countyTeam("Cavan", "An Cabhán", "CAV", blue, white);
        teamArray[3] = cavan;
        countyTeam clare = new countyTeam("Clare", "An Clár", "CLA", yellow, blue);
        teamArray[4] = clare;
        countyTeam cork = new countyTeam("Cork", "Corcaigh", "COR", red, white);
        teamArray[5] = cork;
        countyTeam derry = new countyTeam("Derry", "Doire", "DER", red, white);
        teamArray[6] = derry;
        countyTeam donegal = new countyTeam("Donegal", "Dún na nGall", "DON", green, yellow);
        teamArray[7] = donegal;
        countyTeam down = new countyTeam("Down", "An Dún", "DOW", red, black);
        teamArray[8] = down;
        countyTeam dublin = new countyTeam("Dublin", "Áth Cliath", "DUB", dBlue, lBlue);
        teamArray[9] = dublin;
        countyTeam fermanagh = new countyTeam("Fermanagh", "Fear Manach", "FER", green, white);
        teamArray[10] = fermanagh;
        countyTeam galway = new countyTeam("Galway", "Gaillimh", "GAL", maroon, white);
        teamArray[11] = galway;
        countyTeam kerry = new countyTeam("Kerry", "Ciarraí", "KER", green, yellow);
        teamArray[12] = kerry;
        countyTeam kildare = new countyTeam("Kildare", "Cill Dara", "KLD", white, white);
        teamArray[13] = kildare;
        countyTeam kilkenny = new countyTeam("Kilkenny", "Cill Chainnigh", "KLK", black, amber);
        teamArray[14] = kilkenny;
        countyTeam laois = new countyTeam("Laois", "Laois", "LAO", blue, yellow);
        teamArray[15] = laois;
        countyTeam leitrim = new countyTeam("Leitrim", "Liatroim", "LEI", green, yellow);
        teamArray[16] = leitrim;
        countyTeam limerick = new countyTeam("Limerick", "Luimneach", "LIM", green, white);
        teamArray[17] = limerick;
        countyTeam longford = new countyTeam("Longford", "An Longfort", "LON", blue, yellow);
        teamArray[18] = longford;
        countyTeam louth = new countyTeam("Louth", "Lú", "LOU", red, white);
        teamArray[19] = louth;
        countyTeam mayo = new countyTeam("Mayo", "Maigh Eo", "MAY", green, red);
        teamArray[20] = mayo;
        countyTeam meath = new countyTeam("Meath", "An Mhí", "MEA", green, yellow);
        teamArray[21] = meath;
        countyTeam monaghan = new countyTeam("Monaghan", "Muineachán", "MON", white, blue);
        teamArray[22] = monaghan;
        countyTeam offaly = new countyTeam("Offaly", "Uíbh Fhailí", "OFF", green, yellow);
        teamArray[23] = offaly;
        countyTeam roscommon = new countyTeam("Roscommon", "Ros Comáin", "ROS", green, yellow);
        teamArray[24] = roscommon;
        countyTeam sligo = new countyTeam("Sligo", "Sligeach", "SLI", black, white);
        teamArray[25] = sligo;
        countyTeam tipperary = new countyTeam("Tipperary", "Tiobraid Árann", "TIP", blue, yellow);
        teamArray[26] = tipperary;
        countyTeam tyrone = new countyTeam("Tyrone", "Tír Eoghain", "TYR", white, red);
        teamArray[27] = tyrone;
        countyTeam waterford = new countyTeam("Waterford", "Port Láirge", "WAT", white, blue);
        teamArray[28] = waterford;
        countyTeam westmeath = new countyTeam("Westmeath", "An Iarmhí", "WES", maroon, white);
        teamArray[29] = westmeath;
        countyTeam wexford = new countyTeam("Wexford", "Loch Garman", "WEX", purple, yellow);
        teamArray[30] = wexford;
        countyTeam wicklow = new countyTeam("Wicklow", "Cill Mhantáin", "WIC", blue, yellow);
        teamArray[31] = wicklow;

        return teamArray;
    }

    public void showIntroToast() {
        Toast infoToast = Toast.makeText(this, "Tap a goals or points number to increment it!", Toast.LENGTH_LONG);
        infoToast.show();
    }

    public void setPointsActivity(countyTeam[] teams) {
        if (team1Selected && team2Selected) {
            setContentView(R.layout.activity_main);

            TextView team1EnglishName = (TextView) findViewById(R.id.team_1_english_name);
            team1EnglishName.setText(teams[position1].getEnglishName());

            TextView team1IrishName = (TextView) findViewById(R.id.team_1_irish_name);
            team1IrishName.setText(teams[position1].getIrishName());

            TextView team2EnglishName = (TextView) findViewById(R.id.team_2_english_name);
            team2EnglishName.setText(teams[position2].getEnglishName());

            TextView team2IrishName = (TextView) findViewById(R.id.team_2_irish_name);
            team2IrishName.setText(teams[position2].getIrishName());

            View team1LeftColour = findViewById(R.id.left_colour_team_1);
            team1LeftColour.setBackgroundColor(teams[position1].getPrimaryColour());

            View team1RightColour = findViewById(R.id.right_colour_team_1);
            team1RightColour.setBackgroundColor(teams[position1].getSecondaryColour());

            View team2LeftColour = findViewById(R.id.left_colour_team_2);
            team2LeftColour.setBackgroundColor(teams[position2].getPrimaryColour());

            View team2RightColour = findViewById(R.id.right_colour_team_2);
            team2RightColour.setBackgroundColor(teams[position2].getSecondaryColour());

            showIntroToast();
        } else {
            Toast noSelectionTeoast = Toast.makeText(this, "Must choose two teams before proceeding", Toast.LENGTH_SHORT);
            noSelectionTeoast.show();
        }
    }
}