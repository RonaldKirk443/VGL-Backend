package com.uio443.vglbackend.model;


public class UserGames {

    private final long IGDB_ID;
    private String completionStatus;
    private double hoursPlayed;
    private int hiddenStatus;

    public UserGames(long IGDB_ID, String completionStatus, double hoursPlayed, int hiddenStatus) {
        this.IGDB_ID = IGDB_ID;
        this.completionStatus = completionStatus;
        this.hoursPlayed = hoursPlayed;
        this.hiddenStatus = hiddenStatus;
    }

    public long getIGDB_ID() {
        return IGDB_ID;
    }

    public String getCompletionStatus() {
        return completionStatus;
    }

    public void setCompletionStatus(String completionStatus) {
        this.completionStatus = completionStatus;
    }

    public double getHoursPlayed() {
        return hoursPlayed;
    }

    public void setHoursPlayed(double hoursPlayed) {
        this.hoursPlayed = hoursPlayed;
    }

    public int getHiddenStatus() {
        return hiddenStatus;
    }

    public void setHiddenStatus(int hiddenStatus) {
        this.hiddenStatus = hiddenStatus;
    }
}
