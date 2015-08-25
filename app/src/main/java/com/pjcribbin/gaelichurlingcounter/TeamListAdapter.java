package com.pjcribbin.gaelichurlingcounter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TeamListAdapter extends ArrayAdapter<CountyTeam> {

    public TeamListAdapter(Context context, CountyTeam[] teams) {
        super(context, R.layout.custom_row, teams);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater myInflater = LayoutInflater.from(getContext());
        View customView = myInflater.inflate(R.layout.custom_row, parent, false);

        String individualEnglishName = (getItem(position)).getEnglishName();
        TextView englishNameTextView = (TextView) customView.findViewById(R.id.english_name);

        String individualIrishName = (getItem(position)).getIrishName();
        TextView irishNameTextView = (TextView) customView.findViewById(R.id.irish_name);

        int primaryColour = (getItem(position)).getPrimaryColour();
        View colourPrimary1 = customView.findViewById(R.id.menu_colour_1);
        View colourPrimary2 = customView.findViewById(R.id.menu_colour_2);

        int secondaryColour = (getItem(position)).getSecondaryColour();
        View colourSecondary1 = customView.findViewById(R.id.menu_colour_3);
        View colourSecondary2 = customView.findViewById(R.id.menu_colour_4);

        int tertiaryColour = (getItem(position)).getTertiaryColour();
        View colourTertiary1 = customView.findViewById(R.id.menu_colour_5);
        View colourTertiary2 = customView.findViewById(R.id.menu_colour_6);

        englishNameTextView.setText(individualEnglishName);
        irishNameTextView.setText(individualIrishName);
        colourPrimary1.setBackgroundColor(primaryColour);
        colourPrimary2.setBackgroundColor(primaryColour);
        colourSecondary2.setBackgroundColor(secondaryColour);

        // If county has two colours
        if (tertiaryColour == 0x00000000) {
            colourSecondary1.setBackgroundColor(primaryColour);
            colourTertiary1.setBackgroundColor(secondaryColour);
            colourTertiary2.setBackgroundColor(secondaryColour);
        }

        // If county has three colours
        else {
            colourSecondary1.setBackgroundColor(secondaryColour);
            colourTertiary1.setBackgroundColor(tertiaryColour);
            colourTertiary2.setBackgroundColor(tertiaryColour);
        }
        return customView;
    }
}