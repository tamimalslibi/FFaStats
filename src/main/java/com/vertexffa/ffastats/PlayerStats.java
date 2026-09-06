package com.vertexffa.ffastats;

public class PlayerStats {

    private int kills = 0;
    private int deaths = 0;
    private int currentStreak = 0;
    private int bestStreak = 0;

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public void addKill() {
        kills++;
        currentStreak++;
        if (currentStreak > bestStreak) {
            bestStreak = currentStreak;
        }
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public void addDeath() {
        deaths++;
        currentStreak = 0;
    }

    public int getCurrentStreak() {
        return currentStreak;
    }

    public void setCurrentStreak(int currentStreak) {
        this.currentStreak = currentStreak;
    }

    public int getBestStreak() {
        return bestStreak;
    }

    public void setBestStreak(int bestStreak) {
        this.bestStreak = bestStreak;
    }

    public double getKD() {
        if (deaths == 0) {
            return kills;
        }
        double ratio = (double) kills / (double) deaths;
        return Math.round(ratio * 100.0) / 100.0;
    }
}
