package com.pjcribbin.gaelichurlingcounter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TeamListAdapter extends ArrayAdapter<countyTeam> {

    public TeamListAdapter(Context context, countyTeam[] teams) {
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

        englishNameTextView.setText(individualEnglishName);
        irishNameTextView.setText(individualIrishName);
        return customView;
    }

}
