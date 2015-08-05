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
        View colourLeft = customView.findViewById(R.id.primary_colour);

        int secondaryColour = (getItem(position)).getSecondaryColour();
        View colourRight = customView.findViewById(R.id.secondary_colour);

        englishNameTextView.setText(individualEnglishName);
        irishNameTextView.setText(individualIrishName);
        colourLeft.setBackgroundColor(primaryColour);
        colourRight.setBackgroundColor(secondaryColour);
        return customView;
    }

}