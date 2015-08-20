package com.pjcribbin.gaelichurlingcounter;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private boolean team1Selected = false;
    private boolean team2Selected = false;

    //County user chooses in team menu
    private int position1;
    private int position2;

    //Displays two option menus for team selection and button to finalize choices
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_menu);

        setMenus();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setContentView(R.layout.team_menu);

        setMenus();
    }

    private void setMenus() {
        final CountyTeam[] teams = createTeams();
        ListAdapter teamListAdapter = new TeamListAdapter(this, teams);

        //Team 1 menu
        ListView team1List = (ListView) findViewById(R.id.team_1_list);
        team1List.setAdapter(teamListAdapter);
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

        //Team 2 Menu
        ListView team2List = (ListView) findViewById(R.id.team_2_list);
        team2List.setAdapter(teamListAdapter);
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

        //Selection button
        Button chooseTeamsButton = (Button) findViewById(R.id.choose_teams_button);
        chooseTeamsButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        setPointsActivity(teams);
                    }
                }
        );

        // Carries over team selection on orientation change
        if (team1Selected) {
            TextView selectedTeam = (TextView) findViewById(R.id.team_1_choice);
            String selection = teams[position1].getEnglishName();
            selectedTeam.setText(selection);
        }

        if (team2Selected) {
            TextView selectedTeam = (TextView) findViewById(R.id.team_2_choice);
            String selection = teams[position2].getEnglishName();
            selectedTeam.setText(selection);
        }
    }

    //Displays the main goals and points screen
    private void setPointsActivity(CountyTeam[] teams) {
        if (team1Selected && team2Selected) {

            Intent intent = new Intent(this, ScoreActivity.class);
            intent.putExtra("t1English", teams[position1].getEnglishName());
            intent.putExtra("t1Irish", teams[position1].getIrishName());
            intent.putExtra("t1Primary", teams[position1].getPrimaryColour());
            intent.putExtra("t1Secondary", teams[position1].getSecondaryColour());

            intent.putExtra("t2English", teams[position2].getEnglishName());
            intent.putExtra("t2Irish", teams[position2].getIrishName());
            intent.putExtra("t2Primary", teams[position2].getPrimaryColour());
            intent.putExtra("t2Secondary", teams[position2].getSecondaryColour());

            startActivity(intent);

        } else {
            Toast noSelectionTeoast = Toast.makeText(this, "Must choose two teams before proceeding", Toast.LENGTH_SHORT);
            noSelectionTeoast.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    private CountyTeam[] createTeams() {
        CountyTeam[] teamArray = new CountyTeam[32];
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

        CountyTeam antrim = new CountyTeam("Antrim", "Aontroim", "ANT", yellow, white);
        teamArray[0] = antrim;
        CountyTeam armagh = new CountyTeam("Armagh", "Ard Mhacha", "ARM", orange, white);
        teamArray[1] = armagh;
        CountyTeam carlow = new CountyTeam("Carlow", "Ceatharlach", "CAR", red, yellow, green);
        teamArray[2] = carlow;
        CountyTeam cavan = new CountyTeam("Cavan", "An Cabhán", "CAV", blue, white);
        teamArray[3] = cavan;
        CountyTeam clare = new CountyTeam("Clare", "An Clár", "CLA", yellow, blue);
        teamArray[4] = clare;
        CountyTeam cork = new CountyTeam("Cork", "Corcaigh", "COR", red, white);
        teamArray[5] = cork;
        CountyTeam derry = new CountyTeam("Derry", "Doire", "DER", red, white);
        teamArray[6] = derry;
        CountyTeam donegal = new CountyTeam("Donegal", "Dún na nGall", "DON", green, yellow);
        teamArray[7] = donegal;
        CountyTeam down = new CountyTeam("Down", "An Dún", "DOW", red, black);
        teamArray[8] = down;
        CountyTeam dublin = new CountyTeam("Dublin", "Áth Cliath", "DUB", dBlue, lBlue);
        teamArray[9] = dublin;
        CountyTeam fermanagh = new CountyTeam("Fermanagh", "Fear Manach", "FER", green, white);
        teamArray[10] = fermanagh;
        CountyTeam galway = new CountyTeam("Galway", "Gaillimh", "GAL", maroon, white);
        teamArray[11] = galway;
        CountyTeam kerry = new CountyTeam("Kerry", "Ciarraí", "KER", green, yellow);
        teamArray[12] = kerry;
        CountyTeam kildare = new CountyTeam("Kildare", "Cill Dara", "KID", white, white);
        teamArray[13] = kildare;
        CountyTeam kilkenny = new CountyTeam("Kilkenny", "Cill Chainnigh", "KIK", black, amber);
        teamArray[14] = kilkenny;
        CountyTeam laois = new CountyTeam("Laois", "Laois", "LAO", blue, yellow);
        teamArray[15] = laois;
        CountyTeam leitrim = new CountyTeam("Leitrim", "Liatroim", "LEI", green, yellow);
        teamArray[16] = leitrim;
        CountyTeam limerick = new CountyTeam("Limerick", "Luimneach", "LIM", green, white);
        teamArray[17] = limerick;
        CountyTeam longford = new CountyTeam("Longford", "An Longfort", "LON", blue, yellow);
        teamArray[18] = longford;
        CountyTeam louth = new CountyTeam("Louth", "Lú", "LOU", red, white);
        teamArray[19] = louth;
        CountyTeam mayo = new CountyTeam("Mayo", "Maigh Eo", "MAY", green, red);
        teamArray[20] = mayo;
        CountyTeam meath = new CountyTeam("Meath", "An Mhí", "MEA", green, yellow);
        teamArray[21] = meath;
        CountyTeam monaghan = new CountyTeam("Monaghan", "Muineachán", "MON", white, blue);
        teamArray[22] = monaghan;
        CountyTeam offaly = new CountyTeam("Offaly", "Uíbh Fhailí", "OFF", green, yellow);
        teamArray[23] = offaly;
        CountyTeam roscommon = new CountyTeam("Roscommon", "Ros Comáin", "ROS", green, yellow);
        teamArray[24] = roscommon;
        CountyTeam sligo = new CountyTeam("Sligo", "Sligeach", "SLI", black, white);
        teamArray[25] = sligo;
        CountyTeam tipperary = new CountyTeam("Tipperary", "Tiobraid Árann", "TIP", blue, yellow);
        teamArray[26] = tipperary;
        CountyTeam tyrone = new CountyTeam("Tyrone", "Tír Eoghain", "TYR", white, red);
        teamArray[27] = tyrone;
        CountyTeam waterford = new CountyTeam("Waterford", "Port Láirge", "WAT", white, blue);
        teamArray[28] = waterford;
        CountyTeam westmeath = new CountyTeam("Westmeath", "An Iarmhí", "WES", maroon, white);
        teamArray[29] = westmeath;
        CountyTeam wexford = new CountyTeam("Wexford", "Loch Garman", "WEX", purple, yellow);
        teamArray[30] = wexford;
        CountyTeam wicklow = new CountyTeam("Wicklow", "Cill Mhantáin", "WIC", blue, yellow);
        teamArray[31] = wicklow;

        return teamArray;
    }
}