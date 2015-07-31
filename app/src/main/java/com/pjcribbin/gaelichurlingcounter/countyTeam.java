package com.pjcribbin.gaelichurlingcounter;

public class countyTeam {
    private String englishName;
    private String irishName;
    private String countyCode;
    private String primaryColour;
    private String secondaryColour;

    countyTeam(String englishName, String irishName, String countyCode, String primaryColour, String secondaryColour) {
        this.englishName = englishName;
        this.irishName = irishName;
        this.countyCode = countyCode;
        this.primaryColour = primaryColour;
        this.secondaryColour = secondaryColour;
    }

    countyTeam(String englishName, String irishName, String countyCode) {
        this.englishName = englishName;
        this.irishName = irishName;
        this.countyCode = countyCode;
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

    public String getPrimaryColour() {
        return primaryColour;
    }

    public String getSecondaryColour() {
        return secondaryColour;
    }
}