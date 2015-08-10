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
    private int team1Goals = 0, team1Points = 0, team1Score = 0;
    private int team2Goals = 0, team2Points = 0, team2Score = 0;
    private boolean team1Selected = false;
    private boolean team2Selected = false;
    private Menu myMenu = null;
    //County user chooses in team menu
    private int position1;
    private int position2;

    //Displays two option menus for team selection and button to finalize choices
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final CountyTeam[] teams = createTeams();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_menu);

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
    }

    //Displays the main goals and points screen
    private void setPointsActivity(CountyTeam[] teams) {
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

            Menu menu = getMenu();
            getMenuInflater().inflate(R.menu.menu_main, menu);
            showIntroToast();
            setUpLongClickListeners();

        } else {
            Toast noSelectionTeoast = Toast.makeText(this, "Must choose two teams before proceeding", Toast.LENGTH_SHORT);
            noSelectionTeoast.show();
        }
    }

    private void setUpLongClickListeners(){
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
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
                return true;

            default:
                return true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        myMenu = menu;
        return true;
    }

    private Menu getMenu(){
        return myMenu;
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

    //Displays toast after team selection giving instructions
    private void showIntroToast() {
        Toast infoToast = Toast.makeText(this, "Tap a number to increment it!\nHold a number to decrement it!", Toast.LENGTH_LONG);
        infoToast.show();
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
        CountyTeam carlow = new CountyTeam("Carlow", "Ceatharlach", "CAR", red, green);
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