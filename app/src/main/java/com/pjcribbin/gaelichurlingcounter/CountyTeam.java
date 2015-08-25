package com.pjcribbin.gaelichurlingcounter;

public class CountyTeam {
    private String englishName;
    private String irishName;
    private String countyCode;
    private int primaryColour;
    private int secondaryColour;
    private int tertiaryColour;

    CountyTeam(String englishName, String irishName, String countyCode, int primaryColour, int secondaryColour) {
        this.englishName = englishName;
        this.irishName = irishName;
        this.countyCode = countyCode;
        this.primaryColour = primaryColour;
        this.secondaryColour = secondaryColour;
    }

    CountyTeam(String englishName, String irishName, String countyCode, int primaryColour, int secondaryColour, int tertiaryColour) {
        this.englishName = englishName;
        this.irishName = irishName;
        this.countyCode = countyCode;
        this.primaryColour = primaryColour;
        this.secondaryColour = secondaryColour;
        this.tertiaryColour = tertiaryColour;
    }

    public String getEnglishName() {
        return englishName;
    }

    public String getIrishName() {
        return irishName;
    }

    public String getCountyCode() {
        return countyCode;
    }

    public int getPrimaryColour() {
        return primaryColour;
    }

    public int getSecondaryColour() {
        return secondaryColour;
    }

    public int getTertiaryColour() {
        if (tertiaryColour != 0) {
            return tertiaryColour;
        } return 0x00000000;
    }
}
